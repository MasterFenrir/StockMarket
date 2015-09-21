package org.hanze.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hanze.control.MainController;

/**
 * Main class to start the application
 */
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/MainView.fxml"));
        Parent root = loader.load();

        primaryStage.setOnCloseRequest((e) -> {
            MainController cont = loader.<MainController>getController();
            cont.stopStockMakers();
        });

        primaryStage.setMaximized(true);
        primaryStage.setTitle("Stock Market");
        primaryStage.setScene(new Scene(root, 900, 700));
        primaryStage.show();
    }
}
