package com.solution.fromVC.fxconfig;

import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import com.jfoenix.validation.RequiredFieldValidator;
import com.solution.fromVC.model.RiskManager;
import com.solution.fromVC.service.RiskManagerService;
import com.solution.fromVC.service.RiskManagerServiceImp;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PresentController implements Initializable {

    @FXML
    private StackPane rootPane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXButton enterButton;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private Label incorectLabel;

    public void initialize(URL location, ResourceBundle resources) {
        drawSidePane();
        dataValidation();
//        Platform.runLater(() ->
//                enterButton.setOnAction(new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(ActionEvent event) {
//                        ProgressIndicator progressIndicator = new ProgressIndicator();
//                        AnchorPane ap = new AnchorPane(progressIndicator);
//                        anchorPane.setDisable(true);
//                        rootPane.getChildren().add(ap);
//                    }
//                })
//        );
    }

    private void drawSidePane() {
        try {
            VBox vBox = FXMLLoader.load(getClass().getResource("/fxml/drawer.fxml"));
            drawer.setSidePane(vBox);

            HamburgerBasicCloseTransition burgerTask = new HamburgerBasicCloseTransition(hamburger);
            burgerTask.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                burgerTask.setRate(burgerTask.getRate() * -1);
                burgerTask.play();

                if(drawer.isShown()){
                    drawer.close();
                }else {
                    drawer.open();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dataValidation() {
        RequiredFieldValidator validator = new RequiredFieldValidator();
    }

    @FXML
    void startProgram(ActionEvent event) {
        String login = username.getText();
        String pass = password.getText();
        RiskManagerService riskManagerService = new RiskManagerServiceImp();
        if( riskManagerService.findManagerByUsername(login) != null){
            RiskManager manager = riskManagerService.findManagerByUsername(login);
            if(pass.equals(manager.getPassword()) && login.equals(manager.getName())){
                ((Node) (event.getSource())).getScene().getWindow().hide();
                try {
                    Parent parent = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(parent);
                    stage.setScene(scene);
                    stage.getIcons().add(new Image("/fxml/pictures/Control-Panel.png"));
//                    pi.setDisable(true);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                incorectLabel.setText("Username or password invalid!");
            }
        }else {
            incorectLabel.setText("Username or password invalid!");
        }
    }

    class LoginTask extends Task{

        @Override
        protected Object call() throws Exception {
            return null;
        }
    }
}

