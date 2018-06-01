package ru.spbstu.vereversi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.spbstu.vereversi.game.Game;

import java.io.IOException;

public class Reversi extends Application {
    private static Reversi reversi;

    public static Reversi get() {
        return reversi;
    }

    public static void main(String[] args) {
        launch(args);
    }

    private Stage stage;
    private Game game;

    @Override
    public void start(Stage primaryStage) {
        reversi = this;
        stage = primaryStage;
        primaryStage.setTitle("Reversi");

        start();
        primaryStage.show();
    }

    public Game getGame() {
        return game;
    }

    public void start() {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("start_window.fxml"));
            stage.setScene(new Scene(root, 640, 480));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(404);
        }
    }

    public void game() {
        try {
            game = new Game();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("game_window.fxml"));
            stage.setScene(new Scene(root, 640, 480));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(404);
        }
    }

    public void rules() {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("rules_window.fxml"));
            stage.setScene(new Scene(root, 640, 480));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(404);
        }
    }
}
