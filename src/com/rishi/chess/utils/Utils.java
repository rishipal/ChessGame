package com.rishi.chess.utils;

import java.util.Arrays;
import java.util.List;

public abstract class Utils {
    public static void print(String message) {
        System.out.println(message);
    }
    public enum Direction {
        UP,
        DOWN,
        RIGHT,
        LEFT,
        NORTHEAST,
        NORTHWEST,
        SOUTHEAST,
        SOUTHWEST;

        public List<Direction> getAllDirectionsAsList() {
            Direction[] arr = this.values();
            return Arrays.asList(arr);
        }

        public List<Direction> get4WayDirectionsAsList() {
            Direction[] arr = new Direction[]{UP, DOWN, LEFT, RIGHT};
            return Arrays.asList(arr);
        }
    }
}
