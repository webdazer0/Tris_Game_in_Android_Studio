package com.miguel.app.trisarray.pojo;

import com.miguel.app.trisarray.Helpers;

import java.util.ArrayList;
import java.util.List;

public class Tris {

    private Square[] tris;
    private List<Integer> temp;
    private static int[] line;

    private int totalOpen = 0;
    private int n; // Dimensione Tris...ovviamente 9

    public Tris() {
        this.n = 9;
        buildMap();
    }

    private void buildMap() {
        tris = new Square[n];
        temp = new ArrayList<>();
        line = new int[3];

        for (int i = 0; i < tris.length; i++) {
            tris[i] = new Square();
            temp.add(i);
        }
    }

    public boolean checkStatus(int pos) {
        // Controllo che la cassella sia vuota
        return !(tris[pos].isOpen());
    }

    public Square[] getMap() {
        return tris;
    }

    public static boolean inProgress() {

        if(isWinner()) { return false; }

        if (isFull()) { return false; }
        return true;

    }
    // Prima del metodo "play", va eseguito il metodo "checkStatus" per controllare che la casella sia vuota
    public void play(int pos) {
        tris[pos].setPlayer(pos);
        temp.remove(new Integer(pos));

        boolean inProgress = Tris.inProgress();

        if(inProgress) {

            int posPC = random(0, temp.size());
            tris[temp.get(posPC)].setPc(temp.get(posPC));
            temp.remove(posPC);
        }

    }

    private int random(int min, int max) {
        int result = (int) (Math.floor((Math.random() * (max - min)) + min));
        return result;
    }

    // Metodo isFull ritona true nel Campo TRIS non ci sono casselle vuote
    public static boolean isFull() {
        if (Square.getCount() >= 9) {
            return true;
        }
        return false;
    }

    // Metodo isWinner ritona true se trova una "combinazione vincente"
    // Helpers.calculateWinner(array) è un metodo statico esterno che controlla le giocate sia del PLAYER e/o PC
    // Square.getWinner() è un array usato internamente
    public static boolean isWinner() {
        boolean result = Helpers.calculateWinner(Square.getWinner());

        if(result) { setLine(); }

        return result;
    }

    public static int[] getLine() {
        return line;
    }

    private static void setLine() {
        line = Helpers.getLine();
    }
}
