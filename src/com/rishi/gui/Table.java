package com.rishi.gui;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.rishi.chess.*;

public class Table {
    private static Table INSTANCE = null;
    private JFrame gameFrame;

    private BoardPanel boardPanel;
    private Set<Integer> tileToHighlight;
    private Set<Integer> tilesToAddBorder;
    private Set<Integer> destTileToHighlight;

    private final Game game;
    private final Game.Mode gameMode;

    // `private` in order to disallow creating a new table once game started.
    private Table(Game.Mode mode) {
        // Game
        gameMode = mode;
        game = new Game(gameMode);

        // GUI
        boardPanel = new BoardPanel();
        tileToHighlight = new HashSet<>();
        tilesToAddBorder = new HashSet<>();
        destTileToHighlight = new HashSet<>();
        gameFrame = GUIUtils.initGameFrame(gameMode, game, boardPanel);
    }

    public void show() {
        this.getBoardPanel().drawBoard();
    }

    /**
     * Returns the single instance of the Table being used in the current play.
     */
    public static Table get(Game.Mode mode) {
        if(INSTANCE != null) {
            return INSTANCE;
        }
        INSTANCE = new Table(mode);
        return INSTANCE;
    }

    class BoardPanel extends JPanel {
        final List<TilePanel> boardTiles;
        private Piece selectedPiece;
        protected Piece pieceInMotion;
        boolean inTransition = false;
        TilePanel destination = null;

        BoardPanel() {
            super(new GridLayout(game.chessBoard.SIZE_BOARD, game.chessBoard.SIZE_BOARD));
            this.boardTiles = new ArrayList<>();
            for (int i = 0; i < game.chessBoard.SIZE_BOARD*game.chessBoard.SIZE_BOARD; i++) {
                final TilePanel tilePanel = new TilePanel(this, i);
                this.boardTiles.add(tilePanel);
                add(tilePanel);
            }
            setPreferredSize(GUIUtils.BOARD_PANEL_DIMENSION);
            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            setBackground(Color.LIGHT_GRAY);
            validate();
        }

        void drawBoard() {
            removeAll();
            highlightTiles();
            for (final TilePanel boardTile : boardTiles) {
                boardTile.drawTile(game.chessBoard);
                add(boardTile);
            }
            validate();
            repaint();
        }

        private void highlightTiles() {
            for(Integer tileID : tileToHighlight) {
                boardTiles.get(tileID).highlightTileColor();
            }
            for(Integer tileID : destTileToHighlight) {
                boardTiles.get(tileID).highlightTileColor();
            }
        }
    }

    private class TilePanel extends JPanel {
        private final int tileId;
        private Piece piece;
        private final Cell cell;
        TilePanel(final BoardPanel boardPanel,
                  final int tileId) {
            super(new GridBagLayout());
            this.tileId = tileId;
            cell = getCellFromTileID(tileId);
            piece = cell.piece;
            setPreferredSize(GUIUtils.TILE_PANEL_DIMENSION);
            highlightTileBorder(game.chessBoard);

            addMouseMotionListener(new MouseInputAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    if(game.isHumanActivePlayer()) {
                        boardPanel.inTransition = true;
                    } else {
                        GUIUtils.playWarningSound(); // not your turn to play or not your piece to move
                    }
                }
            });

            addMouseListener(new MouseInputAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                }

                @Override
                public void mouseExited(final MouseEvent e) {
                    if(boardPanel.inTransition) {
                        boardPanel.destination = null;
                    } else {
                        tilesToAddBorder.clear();
                        tileToHighlight.clear();
                        destTileToHighlight.clear();
                    }
               }

                @Override
                public void mouseEntered(final MouseEvent e) {
                    if(boardPanel.inTransition) {
                        boardPanel.destination = TilePanel.this;
                        tilesToAddBorder.clear();
                        tilesToAddBorder.add(tileId);
                        //this.cell.piece = boardPanel.pieceInMotion;
                        boardPanel.drawBoard();
                    } else {
                        tileToHighlight.add(tileId);
                        boardPanel.drawBoard();
                    }
                }

                // This function surprisingly is run in reference to the tile from which the dragging originated, not the
                // cell at which the mouse got released.
                @Override
                public void mouseReleased(final MouseEvent e) {
                    Cell source = TilePanel.this.cell;
                    if(boardPanel.inTransition == true && !cell.isActivePlayerCell(game.getActivePlayer())) {
                    //    playWarningSound(); // not your turn to play or not your piece to move
                    }
                    if(cell.isActivePlayerCell(game.getActivePlayer()) && boardPanel.inTransition &&
                            boardPanel.destination != null && boardPanel.destination != TilePanel.this) {
                        if(boardPanel.pieceInMotion == null) {
                            boardPanel.pieceInMotion = source.piece;
                            Cell destination = boardPanel.destination.cell;
                            if(source.getLegalDestinations().contains(destination)) {
                                //game.makeHumanMove(source, destination);
                                game.makeActivePlayerMove(source, destination);
                            } else {
                                GUIUtils.playWarningSound(); // illegal destination
                            }
                            tilesToAddBorder.clear();
                            tileToHighlight.clear();
                            destTileToHighlight.clear();
                        }
                    }
                    boardPanel.inTransition = false;
                    boardPanel.pieceInMotion = null;
                    boardPanel.destination = null;
                    boardPanel.drawBoard();
                    if(game.status == Game.Status.OVER) {
                        //gameFrame.dispose();
                        JOptionPane.showMessageDialog(boardPanel, "","Game over", JOptionPane.INFORMATION_MESSAGE);
                    }
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    game.makeActivePlayerMove(null, null);
                                    boardPanel.drawBoard();
                                    // your code here
                                }
                            },
                            1000
                    );

                }

                // For speed, mousePressed instead of mouseClicked. mouseClicked looks for multiple button clicks, so it will
                // coalesce some events.
                @Override
                public void mousePressed(final MouseEvent e) {
                    accumulateLegalPathTilesToHighlight();
                    boardPanel.drawBoard();
                }
            });
            validate();
        }

        private Cell getCellFromTileID(int tileId) {
            int row = tileId/game.chessBoard.SIZE_BOARD;
            int col = tileId - row * game.chessBoard.SIZE_BOARD;
            return game.chessBoard.getChessBoard()[row][col];
        }

        private int getTileIDFromCell(Cell cell) {
            return this.getTileIDFromCordinate(cell.getCordinate(), game.chessBoard.SIZE_BOARD);
        }

        private int getTileIDFromCordinate(Cordinate cordinate, int boardSize) {
            return cordinate.row * boardSize + cordinate.col;
        }

        private void accumulateLegalPathTilesToHighlight() {
            if(cell.occupied) {
                tilesToAddBorder.add(getTileIDFromCell(cell));
            }
            ArrayList<Move> legalMoves = cell.getLegalMoves();
            if(legalMoves.isEmpty()) {
                tileToHighlight.add(getTileIDFromCell(cell));
            }
            for(Move m : legalMoves) {
                ArrayList<Cell> path = m.path;
                if(path.size() != 0) {
                    Cell dest = path.get(path.size()-1);
                    destTileToHighlight.add(getTileIDFromCell(dest));
                }
                for(Cell c : path) {
                    int tileNum = getTileIDFromCell(c);
                    tileToHighlight.add(tileNum);
                }
            }
        }

        void drawTile(final ChessBoard board) {
            assignTileColor(board.SIZE_BOARD);
            assignTilePieceIcon(board);
            highlightTileBorder(board);
            validate();
            repaint();
        }

        private void assignTilePieceIcon(final ChessBoard board) {
            this.removeAll();
            if(board.getPiece(this.tileId) != null) {
                try{
                    String relativePath = board.getPiece(this.tileId).pieceIconPath;
                    URL url = getClass().getResource(relativePath);
                    File file = new File(url.getPath());
                    final BufferedImage image = ImageIO.read(file);
                    add(new JLabel(new ImageIcon(image)));
                } catch(final IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void highlightTileBorder(final ChessBoard board) {
            if(tilesToAddBorder != null && tilesToAddBorder.contains(this.tileId)) {
                setBorder(BorderFactory.createLineBorder(Color.ORANGE, 5));
            } else {
                setBorder(BorderFactory.createLineBorder(Color.BLACK));
            }
        }

        private void highlightTileColor() {
            if(tileToHighlight != null && tileToHighlight.contains(this.tileId)) {
                setBackground(GUIUtils.HighlightColors.PATH.highlightColor);
            }
            if(destTileToHighlight != null && destTileToHighlight.contains(this.tileId)){
                setBackground(GUIUtils.HighlightColors.DEST.highlightColor);
            }
            if(destTileToHighlight != null && destTileToHighlight.contains(this.tileId) && this.cell.occupied) {
                setBackground(GUIUtils.HighlightColors.KILLING.highlightColor);
            }
        }

        private void assignTileColor(int boardSize) {
            int row = tileId/boardSize;
            if(row %2 == 0) {
                setBackground(this.tileId % 2 == 0 ? GUIUtils.lightTileColor : GUIUtils.darkTileColor);
            } else {
                setBackground(this.tileId % 2 == 0 ? GUIUtils.darkTileColor : GUIUtils.lightTileColor);
            }
            highlightTileColor();
        }
    }

    private Table.BoardPanel getBoardPanel() {
        return this.boardPanel;
    }


}