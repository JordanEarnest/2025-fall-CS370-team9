package com.recipez.util;

public class Log {

    public static void error(String message) {
        System.err.println("[ERROR] " + message);
    }
    public static void warning(String message) {
        System.err.println("[WARNING] " + message);
    }
    public static void info(String message) {
        System.out.println("[INFO] " + message);
    }
}
