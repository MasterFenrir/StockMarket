package org.hanze.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     * Fallback method for starting a JavaFX application
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/MainView.fxml"));

        primaryStage.setTitle("Stock Market");
        primaryStage.setScene(new Scene(root, 900, 700));
        primaryStage.show();
    }
}
