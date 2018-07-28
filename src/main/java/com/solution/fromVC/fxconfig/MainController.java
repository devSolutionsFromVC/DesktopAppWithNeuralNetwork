package com.solution.fromVC.fxconfig;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import com.solution.fromVC.model.Risk;
import com.solution.fromVC.model.RiskManager;
import com.solution.fromVC.service.RiskManagerServiceImp;
import com.solution.fromVC.service.RiskServiceImp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable{

    @FXML
    private HBox riskInfo;

    @FXML
    private HBox managerInfo;

    @FXML
    private Text loss;

    @FXML
    private Text likelihood;

    @FXML
    private Text name;

    @FXML
    private Text description;

    @FXML
    private Text value;

    @FXML
    private JFXTextField enterRisk;

    @FXML
    private JFXTextField enterManager;

    @FXML
    private Text nameManager;

    @FXML
    private Text companyManager;

    @FXML
    private JFXButton combinateButton;

    private RiskManager manager;

    private Risk risk;

    private boolean managerFlag = false;

    private boolean riskFlag = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        JFXDepthManager.setDepth(riskInfo, 2);
        JFXDepthManager.setDepth(managerInfo, 2);
    }

    @FXML
    void loadRiskInfo(ActionEvent event) {
        String enteredName = enterRisk.getText();
        RiskServiceImp service = new RiskServiceImp();
        risk = service.findByName(enteredName);
        if(risk.getName()!= null){
            String nameR = risk.getName();
            String descriptionR = risk.getDescription();
            String likelihoodR = String.valueOf(risk.getLikelihood());
            String lossR = String.valueOf(risk.getLoss());
            String resultValue;
            int valueR = risk.getValue();
            if(valueR == 0){
                resultValue = String.valueOf(risk.getLikelihood() * risk.getLoss());
            }else {
                resultValue = String.valueOf(valueR);
            }
            name.setText(nameR);
            description.setText(descriptionR);
            likelihood.setText("Likelihood is: " + likelihoodR);
            loss.setText("Loss is: " + lossR);
            value.setText("Value is: " + resultValue);
            riskFlag = true;
        }else {
            name.setText("");
            description.setText("");
            likelihood.setText("No Such Risk Available!");
            loss.setText("");
            value.setText("");
        }
    }

    @FXML
    void loadManagerInfo(ActionEvent event) {
        String managerName = enterManager.getText();
        RiskManagerServiceImp service = new RiskManagerServiceImp();
        manager = service.findManagerByUsername(managerName);
        if(manager.getName() != null){
            String name = manager.getName();
            String company = manager.getCompany();
            nameManager.setText(name);
            companyManager.setText(company);
            managerFlag = true;
        }else {
            nameManager.setText("No Such Manager Available!");
            companyManager.setText("");
        }
    }

    @FXML
    void combinate(ActionEvent event) {
        if(managerFlag & riskFlag){
            List<Risk> riskList = new ArrayList<>();
            riskList.add(risk);
            risk.setManager(manager);
            RiskServiceImp service = new RiskServiceImp();
            service.combinateRiskManager(risk);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Combinated Successful!");
            alert.showAndWait();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("This fields don`t combinate!");
            alert.showAndWait();
        }
    }

    @FXML
    void addRisk(ActionEvent event) {
        String path = "/fxml/addRisk.fxml";
        initializeParent(path);
    }

    @FXML
    void addAsset(ActionEvent event){
        String path = "/fxml/addAsset.fxml";
        initializeParent(path);
    }

    @FXML
    void allRisk(ActionEvent event) {
        String path = "/fxml/allRisks.fxml";
        initializeParent(path);
    }

    @FXML
    void allAssets(ActionEvent event) {
        String path = "/fxml/allAssets.fxml";
        initializeParent(path);
    }

    @FXML
    void riskProcess(ActionEvent event) {
        String path = "/fxml/riskProcess.fxml";
        initializeParent(path);
    }

    @FXML
    void viewStatistic(ActionEvent event) {
        String path = "/fxml/viewStatistics.fxml";
        initializeParent(path);
    }

    private void initializeParent(String path){
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
