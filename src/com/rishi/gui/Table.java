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

import com.rishi.chess.Cell;
import com.rishi.chess.ChessBoard;
import com.rishi.chess.Move;
import com.rishi.chess.Piece;
import com.rishi.chess.Cordinate;

import static javax.swing.JFrame.setDefaultLookAndFeelDecorated;

public class Table {
    private JFrame gameFrame;

    private static final Dimension OUTER_FRAME_DIMENSION = new Dimension(600, 600);
    private static final Dimension BOARD_PANEL_DIMENSION = new Dimension(400, 350);
    private static final Dimension TILE_PANEL_DIMENSION = new Dimension(10, 10);
    private ChessBoard chessBoard;
    private BoardPanel boardPanel;
    private Set<Integer> tileToHighlight;
    private Set<Integer> destTileToHighlight;

    private static final Color lightTileColor = Color.decode("#FFFACD");
    private static final Color darkTileColor = Color.decode("#593E1A");

    public Table() {
        chessBoard = new ChessBoard();
        boardPanel = new BoardPanel();
        tileToHighlight = new HashSet<>();
        destTileToHighlight = new HashSet<>();
        gameFrame = new JFrame();
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(CreateFileMenu());
        gameFrame.setJMenuBar(jMenuBar);
        gameFrame.setTitle("Rishi Chess Game");
        this.gameFrame.setLayout(new BorderLayout());
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gameFrame.add(this.boardPanel, BorderLayout.CENTER);
        setDefaultLookAndFeelDecorated(true);
        this.gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
        center(this.gameFrame);
        gameFrame.setVisible(true);
    }

    public void show() {
        this.getBoardPanel().drawBoard();
    }


    public static Table get() {
        return new Table();
    }

    private ChessBoard getChessBoard() {
        return this.chessBoard;
    }

    private BoardPanel getBoardPanel() {
        return this.boardPanel;
    }

    private JMenu CreateFileMenu() {
        JMenu filesMenu = new JMenu("File");

        final JMenuItem resetGame = new JMenuItem("Reset", KeyEvent.VK_O);
        // ATTN: Using Anonymous class here for action listener
        resetGame.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(final ActionEvent e) {
                                            gameFrame.dispose();
                                            Table.get().show();
                                        }
                                    }
        );
        filesMenu.add(resetGame);

        final JMenuItem exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_X);
        // ATTN: Using Lambda here for action listener
        exitMenuItem.addActionListener((e) -> {
                gameFrame.dispose();
                System.exit(0);
            });
        filesMenu.add(exitMenuItem);
        return filesMenu;
    }


    private class BoardPanel extends JPanel {
        final List<TilePanel> boardTiles;
        private Piece selectedPiece;
        protected Piece pieceInMotion;
        boolean inTransition = false;
        TilePanel destination = null;


        BoardPanel() {
            super(new GridLayout(chessBoard.SIZE_BOARD, chessBoard.SIZE_BOARD));
            this.boardTiles = new ArrayList<>();
            for (int i = 0; i < chessBoard.SIZE_BOARD*chessBoard.SIZE_BOARD; i++) {
                final TilePanel tilePanel = new TilePanel(this, i);
                this.boardTiles.add(tilePanel);
                add(tilePanel);
            }
            setPreferredSize(BOARD_PANEL_DIMENSION);
            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            setBackground(Color.LIGHT_GRAY);
            validate();
        }

        void drawBoard() {
            removeAll();
            highlightTiles();
            for (final TilePanel boardTile : boardTiles) {
                boardTile.drawTile(chessBoard);
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
            setPreferredSize(TILE_PANEL_DIMENSION);
            highlightTileBorder(chessBoard);

            addMouseMotionListener(new MouseInputAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    TilePanel source = (TilePanel) e.getSource();
                    boardPanel.inTransition = true;
                    //boardPanel.destination = null;

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
                    }
                    tileToHighlight.clear();
                    destTileToHighlight.clear();
                }

                @Override
                public void mouseEntered(final MouseEvent e) {
                    if(boardPanel.inTransition) {
                        boardPanel.destination = TilePanel.this;
                    }
                    tileToHighlight.add(tileId);
                    boardPanel.drawBoard();
                }

                // This function surprisingly is in reference to the tile from which the dragging originated, not the
                // destination tile.
                @Override
                public void mouseReleased(final MouseEvent e) {
                    Cell source = TilePanel.this.cell;
                    if(boardPanel.inTransition && boardPanel.destination != null) {
                        if(boardPanel.pieceInMotion == null) {
                            boardPanel.pieceInMotion = TilePanel.this.piece;

                            Cell destination = boardPanel.destination.cell;
                            chessBoard.makeMove(source, destination);

                            destination.setPiece(source.piece);
                            destination.piece.setNewCordinates(destination.getCordinate());
                            source.piece = null;
                            source.occupied = false;
                        }
                    }
                    boardPanel.inTransition = false;
                    boardPanel.pieceInMotion = null;
                    boardPanel.destination = null;
                    boardPanel.drawBoard();
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
            int row = tileId/chessBoard.SIZE_BOARD;
            int col = tileId - row * chessBoard.SIZE_BOARD;
            return chessBoard.getChessBoard()[row][col];
        }

        private Cordinate getCordinateFromTileID() {
            Cell c = this.getCellFromTileID(tileId);
            return c.getCordinate();
        }

        void accumulateLegalPathTilesToHighlight() {
            //ArrayList<Move> legalMoves = chessBoard.getPiece(tileId).generateLegalMovesForPiece();
            ArrayList<Move> legalMoves = cell.getLegalMoves();
            if(legalMoves == null || legalMoves.isEmpty()) {
                return;
            }

            if(legalMoves == null) {
                return;
            }

            for(Move m : legalMoves) {
                ArrayList<Cell> path = m.path;
                if(path.size() != 0) {
                    Cell dest = path.get(path.size()-1);
                    destTileToHighlight.add(dest.getTileIDFromCell());
                }
                for(Cell c : path) {
                    int tileNum = c.getTileIDFromCell();
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
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }

        private void highlightTileColor() {
            if(tileToHighlight != null && tileToHighlight.contains(this.tileId)) {
                setBackground(Color.CYAN);
            }
            if(destTileToHighlight != null && destTileToHighlight.contains((this.tileId))){
                setBackground(Color.GREEN);
            }
        }

        private void assignTileColor(int boardSize) {
            int row = tileId/boardSize;
            if(row %2 == 0) {
                setBackground(this.tileId % 2 == 0 ? lightTileColor : darkTileColor);
            } else {
                setBackground(this.tileId % 2 == 0 ? darkTileColor : lightTileColor);
            }
            highlightTileColor();
        }
    }

    private static void center(final JFrame frame) {
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        final int w = frame.getSize().width;
        final int h = frame.getSize().height;
        final int x = (dim.width - w) / 2;
        final int y = (dim.height - h) / 2;
        frame.setLocation(x, y);
    }
}