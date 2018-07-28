package com.solution.fromVC.fxconfig;

import com.solution.fromVC.model.Assets;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class AssetStatisticViewCell extends ListCell<Assets>{

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label valueInfo;

    @FXML
    private GridPane pane;

    @FXML
    private Label tagInfo;

    private FXMLLoader fxmlLoader;

    @Override
    protected void updateItem(Assets asset, boolean empty) {
        super.updateItem(asset, empty);

        if (empty || asset == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/assetStatisticViewCell.fxml"));
                fxmlLoader.setController(this);

                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            tagInfo.setText(asset.getName());
            String valueData = StatisticsController.assetListParam;
            assetValueCheckout(valueData, asset);

            setText(null);
            setGraphic(pane);
        }
    }

    private void assetValueCheckout(String valueData, Assets asset) {
        switch (valueData) {
            case "assets":
                valueInfo.setText("");
                break;
            case "worth":
                valueInfo.setText(String.valueOf(asset.getWorth()));
                break;
            case "confidentiality":
                valueInfo.setText(String.valueOf(asset.getConfidentiality()));
                break;
            case "accessibility":
                valueInfo.setText(String.valueOf(asset.getAccessibility()));
                break;
            case "integrity":
                valueInfo.setText(String.valueOf(asset.getIntegrity()));
                break;
        }
    }
}
