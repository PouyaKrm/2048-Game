package game;

import game.gameSaves.GameSave;
import game.gameSaves.GameSaveManagement;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;

public class Game {

    private final Environment env;
    private final int row, col;
    private GridPane gridPane = new GridPane();
    private Stage primaryStage;
    private VBox primaryLayout;

    private GameSaveManagement gameSaveManagement = new GameSaveManagement("test");

    public Game(int row, int column, Stage primaryStage) {
        env = new Environment(row, column);
        this.primaryStage = primaryStage;
        this.row = row;
        this.col = column;
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setMinWidth(400);
        gridPane.setMinHeight(400);
        gridPane.setAlignment(Pos.CENTER);

        primaryLayout = new VBox();
        primaryLayout.setSpacing(30);
        Label label = new Label("2048 game.Game");
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-font: 30px sans-serif;" +
                "-fx-font-weight: 100;");
        createUpdateGrid();
        primaryLayout.getChildren().addAll(label, gridPane);
        primaryLayout.setAlignment(Pos.CENTER);

        try {
            gameSaveManagement.getLast5Records().forEach(g -> System.out.println(g));
        } catch (IOException| ParseException e) {
            e.printStackTrace();
        }

        primaryStage.setOnCloseRequest(e -> {

            try {
                gameSaveManagement.saveGame(new GameSave(env.getMaxValue()));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }





    private void createUpdateGrid() {
        Nut[][] nuts = env.getNuts();

        if (gridPane.getChildren().size() > 0) {
            gridPane.getChildren().remove(0, 16);
        }


        String textStyle = "-fx-font: 30px sans-serif;" +
                "-fx-font-weight: 100;";


        for (int i = 0; i < env.getRows(); i++) {
            for (int j = 0; j < env.getCols(); j++) {
                Nut nut = nuts[i][j];
                Region rectangle = new Region();
                rectangle.setMinHeight(80);
                rectangle.setMinWidth(80);
                rectangle.setBackground(new
                        Background(new BackgroundFill(Paint.valueOf(nut.getColor()), new CornerRadii(8), null)));
                Label text = new Label(nut.getValueAsString());
                StackPane stackPane = new StackPane();
                stackPane.getChildren().addAll(rectangle, text);
//                text.setId("nut");
                gridPane.add(stackPane, j, i);
            }
        }

    }

    public void start() {

        Scene scene = new Scene(primaryLayout);
        primaryStage.setMaximized(true);

        scene.setOnKeyPressed(e -> {
            moveByKeyCode(e.getCode());
            createUpdateGrid();
        });


        primaryStage.setScene(scene);

        primaryStage.show();

    }


    private void moveByKeyCode(KeyCode keyCode) {


        if (env.hasLost()) {
            Alert.display("game.Game over", "You Lost!");
            return;
        } else if (env.hasWon()) {
            Alert.display("Winner", "You won the game");
        }

        switch (keyCode) {
            case A:
                env.moveLeft();
                break;
            case D:
                env.moveRight();
                break;
            case S:
                env.moveDown();
                break;
            case W:
                env.moveUp();
                break;
            case Q:
                break;
            default:
                break;
        }

    }

}
