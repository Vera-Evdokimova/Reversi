package ru.spbstu.vereversi.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ru.spbstu.vereversi.Reversi;

public class ControllerStart {
    @FXML
    private void playAGame(ActionEvent e) {
        Reversi.get().game();
    }

    @FXML
    private void showRules(ActionEvent e) {
        Reversi.get().rules();
    }

    @FXML
    private void exit(ActionEvent e) {
        System.exit(0);
    }
}
