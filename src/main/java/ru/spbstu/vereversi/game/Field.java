package ru.spbstu.vereversi.game;

import static ru.spbstu.vereversi.game.Chip.CHIP_PACMAN;
import static ru.spbstu.vereversi.game.Chip.CHIP_GHOST;

public class Field {
    public static final int SIZE = 8;

    private int[][] field = new int[SIZE][SIZE];

    Field() {
        field[SIZE / 2 - 1][SIZE / 2 - 1] = CHIP_GHOST;
        field[SIZE / 2][SIZE / 2] = CHIP_GHOST;

        field[SIZE / 2][SIZE / 2 - 1] = CHIP_PACMAN;
        field[SIZE / 2 - 1][SIZE / 2] = CHIP_PACMAN;
    }

    public int get(int x, int y) {
        return this.field[x][y];
    }

    void set(int x, int y, int chip) {
        field[x][y] = chip;
    }

    void reverse(int x, int y) {
        field[x][y] = Chip.reversed(field[x][y]);
    }

    int count(int chip) {
        int sum = 0;
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (field[i][j] == chip)
                    sum++;
        return sum;
    }

    public boolean hasNeighbourOpposite(int x, int y, int chip) {
        if (x > 0 && field[x - 1][y] == Chip.reversed(chip))
            return true;

        if (x < SIZE - 1 && field[x + 1][y] == Chip.reversed(chip))
            return true;

        if (y > 0 && field[x][y - 1] == Chip.reversed(chip))
            return true;

        if (y < SIZE - 1 && field[x][y + 1] == Chip.reversed(chip))
            return true;

        return false;
    }

    boolean inside(int x, int y) {
        return x >= 0 && y > 0 && x < SIZE && y < SIZE;
    }
}