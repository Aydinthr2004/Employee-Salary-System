module org.example.tableview_me {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.tableview_me to javafx.fxml;
    exports org.example.tableview_me;
}