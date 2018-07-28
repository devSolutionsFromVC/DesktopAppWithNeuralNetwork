package com.solution.fromVC.fxconfig;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.solution.fromVC.model.Assets;
import com.solution.fromVC.service.AssetServiceImp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ResourceBundle;

public class AddAssetController implements Initializable{


    @FXML
    private JFXTextField assetWorth;

    @FXML
    private JFXButton cancel;

    @FXML
    private JFXTextField assetName;

    @FXML
    private JFXTextField isConfidentiality;

    @FXML
    private JFXTextField assetDescription;

    @FXML
    private JFXButton asset;

    @FXML
    private JFXTextField isIntegrity;

    @FXML
    private JFXTextField isAccessibility;

    private AssetServiceImp service = new AssetServiceImp();

    private boolean inputFlag = true;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void saveAction(ActionEvent event) {
        String name = assetName.getText();
        String description = assetDescription.getText();
        String worth = assetWorth.getText();
        String confidentiality = isConfidentiality.getText();
        String integrity = isIntegrity.getText();
        String accessibility = isAccessibility.getText();
        if(name.isEmpty() || description.isEmpty() || worth.isEmpty() || confidentiality.isEmpty()
                || integrity.isEmpty() || accessibility.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter in all fields");
            alert.showAndWait();
        }
        if(consist(name, description)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("This Asset with this Description is exists");
            alert.showAndWait();
        }
        if("true".equals(confidentiality) | "false".equals(confidentiality) &
                "true".equals(integrity) | "false".equals(integrity) &
                "true".equals(accessibility) | "false".equals(accessibility)){
            inputFlag = false;
        }
        if(inputFlag){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("One or more of boolean parameter is not correct!");
            alert.showAndWait();
        }
        if(!inputFlag){
            Assets asset = new Assets(name, description, Integer.parseInt(worth), Boolean.parseBoolean(confidentiality), Boolean.parseBoolean(integrity), Boolean.parseBoolean(accessibility));
            service.addNewAsset(asset);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Asset added Successful!");
            alert.showAndWait();
        }
    }

    private boolean consist(String name, String description) {
        return service.findAssetByNameAndDescription(name, description) == null;
    }

    @FXML
    void cancelAction(ActionEvent event) {

    }

}
