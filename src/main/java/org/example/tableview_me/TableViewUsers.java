package org.example.tableview_me;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TableViewUsers extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Load the main menu page (main-menu.fxml)
        Parent root = FXMLLoader.load(getClass().getResource("first-page.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
