package application.listmaker;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.EventListener;
import java.util.ResourceBundle;

public class LoginSceneController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button minimizeButton;

    @FXML
    private Button closeButton;

    @FXML
    private HBox topBar;

    private double x;
    private double y;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        try {
            // Setting the minimizeButton image
            Image minimizeButtonImage = new Image(new FileInputStream("src\\main\\resources\\icons\\minimizeButtonDarkMode.png"));
            ImageView minimizeButtonImageView = new ImageView(minimizeButtonImage);
            minimizeButtonImageView.setFitWidth(20);
            minimizeButtonImageView.setFitHeight(25);
            this.minimizeButton.setGraphic(minimizeButtonImageView);

            // Setting the closeButton image
            Image closeButtonImage = new Image(new FileInputStream("src\\main\\resources\\icons\\closeButtonDarkMode.png"));
            ImageView closeButtonImageView = new ImageView(closeButtonImage);
            closeButtonImageView.setFitWidth(25);
            closeButtonImageView.setFitHeight(25);
            this.closeButton.setGraphic(closeButtonImageView);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void topBarOnMousePressed(MouseEvent event){ // Get the position where you click on the topBar
        x = event.getSceneX();
        y = event.getSceneY();
    }

    public void topBarOnMouseDragged(MouseEvent event){ // Move the stage with your mouse
        this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    public void minimizeStage(ActionEvent event){
            this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            DoubleProperty height = new SimpleDoubleProperty();
            height.bind(stage.heightProperty());

            Duration duration = Duration.millis(300);
            Timeline timeline = new Timeline(
                    new KeyFrame(duration, new KeyValue(height, 0)),
                    new KeyFrame(duration, new KeyValue(stage.opacityProperty(), 0))
            );
            height.unbind();

            timeline.setOnFinished(event1 -> stage.setIconified(true));

            timeline.play();
    }

    public void closeStage(ActionEvent event){
        this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        this.stage.close();
    }

    public void switchToSignUp(ActionEvent event) throws IOException { // Sign Up button
        this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        this.root = FXMLLoader.load(this.getClass().getResource("SignUpScene.fxml"));
        this.scene = new Scene(this.root);

        String css = this.getClass().getResource("SignUpScene.css").toExternalForm();
        scene.getStylesheets().add(css);

        this.stage.setScene(this.scene);

        this.stage.show();
    }
}