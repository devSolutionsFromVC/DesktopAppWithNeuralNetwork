package com.solution.fromVC.fxconfig;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.solution.fromVC.model.Risk;
import com.solution.fromVC.service.RiskServiceImp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ResourceBundle;

public class AddRiskController implements Initializable{

    @FXML
    private JFXTextField riskName;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private JFXTextField riskLikelihood;

    @FXML
    private JFXTextField riskDescription;

    @FXML
    private JFXTextField riskLoss;

    @FXML
    private JFXButton saveButton;

    private RiskServiceImp riskService = new RiskServiceImp();

    @FXML
    void saveAction(ActionEvent event) {
        String name = riskName.getText();
        String description = riskDescription.getText();
        String likelihood = riskLikelihood.getText();
        String loss = riskLoss.getText();
        int value = Integer.parseInt(likelihood) * Integer.parseInt(loss);
        if(name.isEmpty() || description.isEmpty() || likelihood.isEmpty() || loss.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter in all fields");
            alert.showAndWait();
        }
        if(consist(name)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("This Risk is exists");
            alert.showAndWait();
        }
        Risk risk = new Risk(name, description, Integer.parseInt(likelihood), Integer.parseInt(loss), value);
        riskService.addNewRisk(risk);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Risk added Successful!");
        alert.showAndWait();
    }

    private boolean consist(String name){
        return riskService.findByName(name) == null;
    }

    @FXML
    void cancelAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
