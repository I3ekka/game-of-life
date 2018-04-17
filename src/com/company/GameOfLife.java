package com.company;

public class GameOfLife {
    boolean [] [] feld = {
            {false, false, false, false, false},
            {false, false, true, false, false},
            {false, false, true, false, false},
            {false, false, true, false, false},
            {false, false, false, false, false},
    };

    public static void main(String[] args) {
        GameOfLife myGame = new GameOfLife();
        myGame.print();
        System.out.println();
        for (int i = 0; i < 10; i++) {
            myGame.nextGeneration();
            myGame.print();
            System.out.println();
        }
    }

    private void print() {
        for (int i = 0; i < feld.length; i++) {
            for(int j = 0; j < feld[i].length; j++) {
                if (feld[i][j] == true) {
                    System.out.print("o ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    private void nextGeneration() {
        boolean [] [] fieldOfNextGeneration = new boolean[feld.length][feld[0].length];
        for (int i = 0; i < feld.length; i++) {
            for (int j = 0; j < feld[i].length; j++) {
                fieldOfNextGeneration[i][j] = determineNewCellStatus(i, j);
            }
        }
        feld = fieldOfNextGeneration;
    }

    private boolean determineNewCellStatus(int i, int j) {
        if (isCellAtBorder(i, j)) {
            return false;
        } else if (feld[i][j]) {
            if (isCellDying(i, j)) {
                return false;
            } else if (isCellStillAlive(i, j)) {
                return true;
            }
        } else if (isCellRisingFromDead(i, j)) {
            return true;

        }
        return false;
    }

    private boolean isCellRisingFromDead(int CellI, int CellJ) {
        int livingNeighbor = countLivingNeighbour(CellI, CellJ);
        return livingNeighbor == 3;
    }

    private boolean isCellDying(int CellI, int CellJ) {
        int livingNeighbor = countLivingNeighbour(CellI, CellJ);
        return !(livingNeighbor == 2 || livingNeighbor == 3);
    }

    private boolean isCellStillAlive(int CellI, int CellJ) {
        int livingNeighbor = countLivingNeighbour(CellI, CellJ);
        return livingNeighbor == 2 || livingNeighbor == 3;
    }

    private int countLivingNeighbour(int CellI, int CellJ) {
        int livingNeighbor = 0;
        for (int i = CellI-1; i <= CellI +1; i++) {
            for (int j = CellJ-1; j <= CellJ + 1; j++) {
                if (feld[i][j] && (i != CellI || j != CellJ)) {
                    livingNeighbor++;
                }
            }
        }
        return livingNeighbor;
    }

    private boolean isCellAtBorder(int i, int j) {
        if (i == 0) {
            return true;
        }
        if (j == 0) {
            return true;
        }
        if (i == feld.length-1) {
            return true;
        }
        if (j == feld[i].length-1) {
            return true;
        }
        return false;
    }
}
