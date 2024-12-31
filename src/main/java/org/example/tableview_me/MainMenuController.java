package org.example.tableview_me;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    @FXML
    public void handleAddEmployee(ActionEvent event) throws IOException {
        // Load the add employee page (hello-view.fxml)
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));

        // Create a new stage for the add employee page
        Stage addEmployeeStage = new Stage();
        addEmployeeStage.setScene(new Scene(root));
        addEmployeeStage.show();
    }

    @FXML
    public void handleReports(ActionEvent event) throws IOException {
        // Load the reports page (reports.fxml)
        Parent root = FXMLLoader.load(getClass().getResource("reports.fxml"));

        // Create a new stage for the reports page
        Stage reportsStage = new Stage();
        reportsStage.setScene(new Scene(root));
        reportsStage.show();
    }
}
