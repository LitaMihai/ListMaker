module application.listmaker {
    requires javafx.controls;
    requires javafx.fxml;


    opens application.listmaker to javafx.fxml;
    exports application.listmaker;
}