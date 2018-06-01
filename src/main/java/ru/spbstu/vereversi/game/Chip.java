package ru.spbstu.vereversi.game;

public class Chip {
    public static final int CHIP_GHOST = 1;
    public static final int CHIP_NEUTRAL = 0;
    public static final int CHIP_PACMAN = -1;

    public static int reversed(int chip) {
        if (chip == CHIP_GHOST)
            return CHIP_PACMAN;
        else if (chip == CHIP_PACMAN)
            return CHIP_GHOST;
        else
            return CHIP_NEUTRAL;
    }
}
