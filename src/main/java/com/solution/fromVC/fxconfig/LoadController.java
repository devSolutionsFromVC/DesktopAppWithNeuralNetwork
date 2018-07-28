package com.solution.fromVC.fxconfig;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoadController implements Initializable{

    @FXML
    private StackPane rootPane;

    private VBox vBox = new VBox();

//    @FXML
//    private StackPane presentPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new DoPresent().start();
        rootPane.getChildren().add(vBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setDisable(true);
    }

    class DoPresent extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(3000);

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Parent root = null;
//                        StackPane pane = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/fxml/present.fxml"));
//                            pane = FXMLLoader.load(getClass().getResource("/fxml/present.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.initStyle(StageStyle.TRANSPARENT);
//                        loadPresent();

                        FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), root);
                        fadeIn.setFromValue(0);
                        fadeIn.setToValue(1);
                        fadeIn.setCycleCount(1);

                        fadeIn.play();
                        stage.show();

                        rootPane.getScene().getWindow().hide();
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


//        private void loadPresent() {
//            try {
//                StackPane pane = FXMLLoader.load(getClass().getResource("/fxml/present.fxml"));
//                presentPane.getChildren().setAll(pane);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
    }
}
