package com.solution.fromVC.fxconfig;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.solution.fromVC.model.RiskManager;
import com.solution.fromVC.service.RiskManagerService;
import com.solution.fromVC.service.RiskManagerServiceImp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class DrawerController implements Initializable{

    @FXML
    private VBox vBox;

    @FXML
    private JFXTextField companyRegisterField;

    @FXML
    private JFXTextField passwordRegisterField;

    @FXML
    private JFXTextField usernameRegisterField;

    @FXML
    private JFXButton createButton;

    private RiskManagerService register;

    @FXML
    void createManager(ActionEvent event) {
        String newManagerUsername = usernameRegisterField.getText();
        String newManagerPassword = passwordRegisterField.getText();
        String newManagerCompany = companyRegisterField.getText();
        if(newManagerUsername.isEmpty() || newManagerPassword.isEmpty() || newManagerCompany.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter in all fields");
            alert.showAndWait();
        }else {
            RiskManager manager = new RiskManager(newManagerUsername, newManagerPassword, newManagerCompany);
            RiskManagerServiceImp serviceImp = new RiskManagerServiceImp();
            serviceImp.addNewManager(manager);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
