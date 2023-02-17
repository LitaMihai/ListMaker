package application.listmaker;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.IOException;

public class LoginScene {
    private Scene scene;
    private Parent root;
    private static Stage stage;

    public LoginScene(Stage stage) throws IOException {
        this.stage = stage;

        this.root = FXMLLoader.load(this.getClass().getResource("LoginScene.fxml"));

        stage.setTitle("ListMaker");
        stage.initStyle(StageStyle.UNDECORATED);

        this.scene = new Scene(this.root);

        String css = this.getClass().getResource("LoginScene.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(this.scene);

        stage.iconifiedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue){
                restoreStage(stage);
            }
        });

        stage.show();
    }

    private void restoreStage(Stage stage) {
        DoubleProperty height = new SimpleDoubleProperty();
        height.bind(stage.heightProperty());

        Duration duration = Duration.millis(300);
        Timeline timeline = new Timeline(
                new KeyFrame(duration, new KeyValue(height, 200)),
                new KeyFrame(duration, new KeyValue(stage.opacityProperty(), 1))
        );
        height.unbind();

        timeline.setOnFinished(event1 -> stage.setIconified(false));

        // Play the timeline
        timeline.play();
    }
}
