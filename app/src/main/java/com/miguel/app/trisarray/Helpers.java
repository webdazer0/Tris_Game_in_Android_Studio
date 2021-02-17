package com.miguel.app.trisarray;

public class Helpers {

    private static int[] line;

    public static boolean calculateWinner(int[] arrayTris) {
        int[][] lines = new int[][]{
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},
                {0, 3, 6},
                {1, 4, 7},
                {2, 5, 8},
                {0, 4, 8},
                {2, 4, 6}
        };

        for (int i = 0; i < lines.length; i++) {
            int a = lines[i][0];
            int b = lines[i][1];
            int c = lines[i][2];

            if (arrayTris[a] != 0 && arrayTris[a] == arrayTris[b] && arrayTris[a] == arrayTris[c]) {
                setLine(new int[]{a, b, c});
                return true;
            }
        }

        return false;

    }

    public static int[] getLine() {
        return line;
    }

    public static void setLine(int[] line) {
        Helpers.line = line;
    }
}
