package application.listmaker;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpSceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToLogin(ActionEvent event) throws IOException {
        this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        this.root = FXMLLoader.load(this.getClass().getResource("LoginScene.fxml"));
        this.scene = new Scene(this.root);

        String css = this.getClass().getResource("LoginScene.css").toExternalForm();
        scene.getStylesheets().add(css);

        this.stage.setScene(this.scene);

        this.stage.show();
    }
}
