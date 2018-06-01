package ru.spbstu.vereversi.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import ru.spbstu.vereversi.Reversi;
import ru.spbstu.vereversi.game.Field;
import ru.spbstu.vereversi.game.Game;

import static ru.spbstu.vereversi.game.Chip.CHIP_GHOST;
import static ru.spbstu.vereversi.game.Chip.CHIP_PACMAN;
import static ru.spbstu.vereversi.game.Field.SIZE;

public class ControllerGame {
    private static final int CHIP_SIZE = 40;
    private Field field;
    private Game game;

    @FXML
    GridPane gamePane;

    @FXML
    Label ghostScore, pacmanScore;

    @FXML
    ImageView ghostPlayer, pacmanPlayer;

    @FXML
    public void initialize() {
        game = Reversi.get().getGame();
        field = game.getField();

        updateScreen();
        updatePlayer();
        gamePane.setOnMouseClicked(event -> {
            int x = (int) event.getX() / CHIP_SIZE;
            int y = (int) event.getY() / CHIP_SIZE;

            System.out.println(x + " " + y);

            x = x >= SIZE ? SIZE - 1 : x;
            y = y >= SIZE ? SIZE - 1 : y;

            System.out.println(x + " " + y);

            switch (game.click(x, y)) {
                case WRONG:
                    break;
                case SWITCH_PLAYER:
                    updatePlayer();
                    updateScreen();
                    break;
                case SAME_PLAYER:
                    updateScreen();
                    break;
                case FINISHED:
                    updateScreen();
                    finishGame();
                    break;
            }
        });
    }

    @FXML
    private void start(ActionEvent e) {
        Reversi.get().start();
    }

    private void updatePlayer() {
        if (game.isPacmanMove()) {
            pacmanPlayer.setEffect(new Glow(2));
            ghostPlayer.setEffect(new Glow(0));
        } else {
            ghostPlayer.setEffect(new Glow(2));
            pacmanPlayer.setEffect(new Glow(0));
        }
    }

    private void updateScreen() {
        ghostScore.setText(String.valueOf(game.getGhostScore()));
        pacmanScore.setText(String.valueOf(game.getPacmanScore()));

        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                setChip(i, j, field.get(i, j));
    }

    private void finishGame() {
        String winner;
        if (game.getPacmanScore() > game.getGhostScore()) {
            pacmanPlayer.setEffect(new Glow(10));
            ghostPlayer.setEffect(new Glow(0));
            winner = "Pacman";
        } else {
            ghostPlayer.setEffect(new Glow(10));
            pacmanPlayer.setEffect(new Glow(0));
            winner = "Ghost";
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION, winner + " wins!!!");
        alert.show();
    }

    private void setChip(int x, int y, int chip) {
        if (chip == CHIP_GHOST)
            addImage("ghost.jpg", x, y);
        else if (chip == CHIP_PACMAN)
            addImage("pacman.jpg", x, y);
        else
            clear(x, y);
    }

    private void addImage(String url, int x, int y) {
        ImageView imageView = new ImageView(new Image(url, CHIP_SIZE, CHIP_SIZE, false, true));
        gamePane.add(imageView, x, y);
    }

    private void clear(int x, int y) {
        gamePane.add(new ImageView(), x, y);
    }
}
