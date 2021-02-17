package com.miguel.app.trisarray.pojo;

public class Square {
    private boolean open;
    private boolean player;
    private boolean pc;

    private static int count;
    private static int[] winner;


    public Square(boolean open, boolean player, boolean pc, int n) {
        this.open = open;
        this.player = player;
        this.pc = pc;
        this.count = 0;
        this.winner = new int[n];
    }

    public Square() {
        this(false, false, false, 9);
    }

    public boolean isOpen() {
        return open;
    }

    private void setOpen() {
        this.open = true;
        addCount();
    }

    public static int getCount() {
        return count;
    }

    private void addCount() {
        count++;
    }

    public boolean isPlayer() {
        return player;
    }

    public void setPlayer(int pos) {
        this.player = true;
        setOpen();
        setWinner(pos, 1);
    }

    public boolean isPc() {
        return pc;
    }

    public void setPc(int pos) {
        this.pc = true;
        setOpen();
        setWinner(pos, 2);
    }

    public static int[] getWinner() {
        return winner;
    }

    public static void setWinner(int pos, int player) {
        Square.winner[pos] = player;
    }
}
