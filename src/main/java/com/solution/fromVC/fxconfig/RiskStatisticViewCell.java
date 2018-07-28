package com.solution.fromVC.fxconfig;

import com.solution.fromVC.model.Risk;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class RiskStatisticViewCell extends ListCell<Risk>{

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
    protected void updateItem(Risk risk, boolean empty) {
        super.updateItem(risk, empty);

        if (empty || risk == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/riskStatisticViewCell.fxml"));
                fxmlLoader.setController(this);

                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            tagInfo.setText(risk.getName());
            String valueData = StatisticsController.riskListParam;
            riskValueCheckout(valueData, risk);

            setText(null);
            setGraphic(pane);
        }
    }

    private void riskValueCheckout(String valueName, Risk risk) {
        switch (valueName) {
            case "risks":
                valueInfo.setText("");
                break;
            case "value":
                valueInfo.setText(String.valueOf(risk.getValue()));
                break;
            case "loss":
                valueInfo.setText(String.valueOf(risk.getLoss()));
                break;
            case "likelihood":
                valueInfo.setText(String.valueOf(risk.getLikelihood()));
                break;
            }
    }
}
