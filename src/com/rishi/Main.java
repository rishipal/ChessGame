package com.rishi;

import com.rishi.chess.Game;
import com.rishi.gui.*;

import java.applet.Applet;

// Game driver code
public class Main extends Applet {
    public static void main(String[] args) {
    Table.get(Game.Mode.EASY).show();
    }
}
