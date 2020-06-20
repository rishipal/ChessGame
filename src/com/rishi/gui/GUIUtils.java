package com.rishi.gui;

import com.rishi.chess.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static javax.swing.JFrame.setDefaultLookAndFeelDecorated;

abstract class GUIUtils {
    static final Dimension OUTER_FRAME_DIMENSION = new Dimension(600, 600);
    static final Dimension BOARD_PANEL_DIMENSION = new Dimension(400, 350);
    static final Dimension TILE_PANEL_DIMENSION = new Dimension(10, 10);
    static final Color lightTileColor = Color.decode("#FFFACD");
    static final Color darkTileColor = Color.decode("#593E1A");

    enum HighlightColors {
        PATH(Color.YELLOW),
        DEST(Color.GREEN),
        KILLING(Color.RED);

        Color highlightColor;

        HighlightColors(Color c) {
            this.highlightColor = c;
        }
    }

    static JFrame initGameFrame(Game.Mode gameMode, Game game, Table.BoardPanel boardPanel) {
        JFrame gameFrame = new JFrame();


        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(GUIUtils.CreateFileMenu(gameFrame, gameMode));
        jMenuBar.add(GUIUtils.CreateModeMenu(game));
        gameFrame.setJMenuBar(jMenuBar);

        gameFrame.setTitle("Chess Game");
        gameFrame.setLayout(new BorderLayout());
        gameFrame.setSize(GUIUtils.OUTER_FRAME_DIMENSION);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.add(boardPanel, BorderLayout.CENTER);
        setDefaultLookAndFeelDecorated(true);
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameFrame.setSize(GUIUtils.OUTER_FRAME_DIMENSION);
        GUIUtils.center(gameFrame);
        gameFrame.setVisible(true);
        return gameFrame;
    }

    static void center(final JFrame frame) {
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        final int w = frame.getSize().width;
        final int h = frame.getSize().height;
        final int x = (dim.width - w) / 2;
        final int y = (dim.height - h) / 2;
        frame.setLocation(x, y);
    }

    static void playWarningSound() {
        Toolkit.getDefaultToolkit().beep();
    }

    static JMenu CreateFileMenu(JFrame gameFrame, Game.Mode gameMode) {
        JMenu filesMenu = new JMenu("File");

        final JMenuItem resetGame = new JMenuItem("Reset", KeyEvent.VK_O);
        resetGame.addActionListener((e) -> {
            gameFrame.dispose();
            Table.get(gameMode).show();
        });
        filesMenu.add(resetGame);

        final JMenuItem exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_X);
        exitMenuItem.addActionListener((e) -> {
            gameFrame.dispose();
            System.exit(0);
        });
        filesMenu.add(exitMenuItem);
        return filesMenu;
    }

    static JMenu CreateModeMenu(Game game) {
        JMenu filesMenu = new JMenu("Mode");

        final JMenuItem randomMode = new JMenuItem("Random", KeyEvent.VK_O);
        randomMode.addActionListener((e) -> {
            game.switchMode(Game.Mode.RANDOM);
        });
        filesMenu.add(randomMode);

        final JMenuItem easyMode = new JMenuItem("Easy", KeyEvent.VK_X);
        easyMode.addActionListener((e) -> {
            game.switchMode(Game.Mode.EASY);
        });
        filesMenu.add(easyMode);
        return filesMenu;
    }

}