package ru.spbstu.vereversi.game;

import static ru.spbstu.vereversi.game.Chip.*;
import static ru.spbstu.vereversi.game.Field.SIZE;
import static ru.spbstu.vereversi.game.Game.Result.*;

public class Game {
    public enum Result {
        WRONG,
        SWITCH_PLAYER,
        SAME_PLAYER,
        FINISHED
    }

    private Field field = new Field();
    private boolean isPacmanMove = true;

    public Result click(int x, int y) {
        if (field.get(x, y) != CHIP_NEUTRAL)
            return WRONG;


        if (tryReverse(x, y)) {
            field.set(x, y, currentPlayerChip());

            if (hasStep(!isPacmanMove)) {
                isPacmanMove = !isPacmanMove;
                return SWITCH_PLAYER;
            } else if (hasStep(isPacmanMove))
                return SAME_PLAYER;
            else
                return FINISHED;
        } else
            return WRONG;
    }

    private boolean tryReverse(int x, int y) {
        boolean res = false;
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++)
                if (findLineAndReverse(x, y, i, j))
                    res = true;
        return res;
    }

    private boolean hasStep(boolean isPacmanMove) {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (field.get(i, j) == CHIP_NEUTRAL && hasAnyLine(isPacmanMove, i, j))
                    return true;
        return false;
    }

    private boolean hasAnyLine(boolean isPacmanMove, int x, int y) {
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++)
                if (findLine(isPacmanMove, x, y, i, j) > 0)
                    return true;
        return false;
    }

    private boolean findLineAndReverse(int x, int y, int dx, int dy) {
        int l = findLine(isPacmanMove, x, y, dx, dy);
        if (l > 0) {
            reverseLine(x, y, dx, dy, l);
            return true;
        }
        return false;
    }

    private int findLine(boolean isPacmanMove, int x, int y, int dx, int dy) {
        if (dx == 0 && dy == 0)
            return 0;

        int currentChip = isPacmanMove ? CHIP_PACMAN : CHIP_GHOST;
        int oppositeChip = Chip.reversed(currentChip);

        boolean tick = false;
        for (int i = 1; field.inside(x + i * dx, y + i * dy); i++) {
            int chip = field.get(x + i * dx, y + i * dy);

            if (chip == oppositeChip)
                tick = true;
            else if (chip == currentChip && tick)
                return i - 1;
            else
                break;
        }
        return 0;
    }

    private void reverseLine(int x, int y, int dx, int dy, int length) {
        for (int i = 1; i <= length; i++)
            field.reverse(x + i * dx, y + i * dy);
    }

    public boolean isPacmanMove() {
        return isPacmanMove;
    }

    public int getPacmanScore() {
        return field.count(playerChip(true));
    }

    public int getGhostScore() {
        return field.count(playerChip(false));
    }

    private int currentPlayerChip() {
        return playerChip(isPacmanMove);
    }

    private int playerChip(boolean isPacman) {
        return isPacman ? CHIP_PACMAN : CHIP_GHOST;
    }

    public Field getField() {
        return field;
    }
}
