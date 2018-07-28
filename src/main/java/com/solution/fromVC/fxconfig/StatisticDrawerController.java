package com.solution.fromVC.fxconfig;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class StatisticDrawerController implements Initializable{

    @FXML
    private Button trelloButton;

    @FXML
    private VBox vBox;

    @FXML
    private Button excelButton;

    @FXML
    private Pane pane;

    @FXML
    private Button slackButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


}
