import game.Game;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) throws IOException, InterruptedException {

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Game game = new Game(4, 4, primaryStage);
        game.start();
    }
}
