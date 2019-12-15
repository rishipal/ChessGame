package com.rishi.gui;
import javax.imageio.ImageIO;
import javax.swing.*;
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
import java.util.stream.Collectors;

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

    private Color lightTileColor = Color.decode("#FFFACD");
    private Color darkTileColor = Color.decode("#593E1A");

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
            setPreferredSize(TILE_PANEL_DIMENSION);
            highlightTileBorder(chessBoard);
            addMouseMotionListener(new MouseMotionListener() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    System.out.print(e.getSource());
                }

                @Override
                public void mouseMoved(MouseEvent e) {

                }
            });
            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(final MouseEvent event) {
                    //tileToHighlight.clear();
                    //destTileToHighlight.clear();
                    //boardPanel.selectedPiece = chessBoard.getPiece(tileId);
                    //ATTN: Don't do this computation here. You should cache all results earlier; only display here.
                    //accumulateLegalPathTilesToHighlight();
                    //boardPanel.drawBoard();
                }

                @Override
                public void mouseExited(final MouseEvent e) {
                    tileToHighlight.clear();
                    destTileToHighlight.clear();
                    //if(boardPanel.selectedPiece != null) {
                    //    chessBoard.setPiece(tileId, null);
                    //}
                    //boardPanel.drawBoard();
                }

                @Override
                public void mouseEntered(final MouseEvent e) {
                    //Cell c = getCellFromTileID(tileId);
                    //if(c.occupied == false) {
                    //    return;
                    //}
                    //ATTN: Why do I need this check here?
                    //if(tileToHighlight == null) {
                    //    tileToHighlight = new HashSet<>();
                    //}
                    //tileToHighlight.clear();
                    tileToHighlight.add(tileId);
                    boardPanel.drawBoard();
                }

                @Override
                public void mouseReleased(final MouseEvent e) {
                    //piece = boardPanel.selectedPiece;
                    //piece.setNewCordinates(getCordinateFromTileID());
                    //cell.setPiece(piece);
                    //boardPanel.drawBoard();
                    //boardPanel.selectedPiece = null;
                    System.out.println("Mouse Released");
                }

                // Fo speed, mousePressed instead of mouseClicked. mouseClicked looks for multiple button clicks, so it will
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
            //ATTN: Move this to testing directory.
            // Using the Java 8 stream API along with Lambda
            //List<Move> verifiedLegalMoves = legalMoves.stream().filter((e) ->
            //        e.destination.getCordinate().isWithinBounds(chessBoard.SIZE_BOARD)).collect(Collectors.toList());
            //assert(verifiedLegalMoves.size() == legalMoves.size());

            if(legalMoves == null) {
                return;
            }
            //if(tileToHighlight == null) {
            //    tileToHighlight = new HashSet<>();
            //}
            //if(destTileToHighlight == null) {
            //    destTileToHighlight = new HashSet<>();
            //}
            //tileToHighlight.clear();
            //destTileToHighlight.clear();

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