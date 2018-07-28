package com.solution.fromVC.fxconfig;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.effects.JFXDepthManager;
import com.solution.fromVC.NeuralService.MLPerceptron;
import com.solution.fromVC.NeuralService.RiskInputData;
import com.solution.fromVC.model.Assets;
import com.solution.fromVC.model.Risk;
import com.solution.fromVC.service.AssetServiceImp;
import com.solution.fromVC.service.RiskServiceImp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProcessingRiskController implements Initializable{

    @FXML
    private JFXListView<Risk> riskListView;

    @FXML
    private JFXListView<Assets> assetListView;

    @FXML
    private Label riskName;

    @FXML
    private Label assetName;

    @FXML
    private Label assetPriority;

    @FXML
    private Label riskStatus;

    @FXML
    private Label riskResultLabel;

    @FXML
    private LineChart<Double, Integer> lineChart;
    private XYChart.Series<Double, Integer> series, series1, series2;

    private ObservableList<Risk> riskObservableList;

    private ObservableList<Assets> assetObservableList;

    private Risk selectedRisk = new Risk();

    private Assets selectedAsset = new Assets();

    private RiskReader riskReader = new RiskReader();

    private double stage = 1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        JFXDepthManager.setDepth(riskListView, 4);
        JFXDepthManager.setDepth(assetListView, 4);
        JFXDepthManager.setDepth(riskName, 4);
        JFXDepthManager.setDepth(assetName, 4);
        JFXDepthManager.setDepth(assetPriority, 4);
        JFXDepthManager.setDepth(riskStatus, 4);
        JFXDepthManager.setDepth(riskResultLabel, 4);
        riskListView.setItems(riskObservableList);
        riskListView.setCellFactory(riskListViewCell -> new RiskListViewCell());
        assetListView.setItems(assetObservableList);
        assetListView.setCellFactory(assetListViewCell -> new AssetListViewCell());
        riskListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Risk>() {
            @Override
            public void changed(ObservableValue<? extends Risk> observable, Risk oldValue, Risk newValue) {
                Risk selectedItem = riskListView.getSelectionModel().getSelectedItem();
                selectedRisk = selectedItem;
                riskName.setText(selectedItem.getName());
                riskReader.setRisk(selectedItem);
                if(selectedItem.getValue() >= 4){
                    riskStatus.setText("Critical");
                }else {
                    riskStatus.setText("Normal");
                }
            }
        });
        assetListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Assets>() {
            @Override
            public void changed(ObservableValue<? extends Assets> observable, Assets oldValue, Assets newValue) {
                Assets selectedItem = assetListView.getSelectionModel().getSelectedItem();
                selectedAsset = selectedItem;
                assetName.setText(selectedItem.getName());
                riskReader.setAsset(selectedItem);
                if(selectedItem.getWorth() >= 4){
                    assetPriority.setText("Valuable");
                }else {
                    assetPriority.setText("Not Valuable");
                }
            }
        });
        series = new XYChart.Series<>();
        series.setName("Risk Data");
        series1 = new XYChart.Series<>();
        series1.setName("Asset Data");
        series2 = new XYChart.Series<>();
        series2.setName("Result");

        lineChart.getData().add(series);
        lineChart.getData().add(series1);
        lineChart.getData().add(series2);

        new Thread(new RiskReader()).start();
    }

    public double[] processingResult(){
        RiskInputData riskInputData = new RiskInputData(selectedRisk.getLikelihood(), selectedRisk.getLoss(), selectedAsset.getWorth());
        return riskInputData.riskAttributes();
    }

    @FXML
    void procAction(ActionEvent event) {
        String result = MLPerceptron.riskResult();
        riskResultLabel.setText(result);
        riskReader.seriesSetData();
        stage += 2;
    }

    public ProcessingRiskController() {
        riskObservableList = FXCollections.observableArrayList();
        riskObservableList.addAll(loadAllRisks());
        assetObservableList = FXCollections.observableArrayList();
        assetObservableList.addAll(loadAllAssets());
    }

    private List<Risk> loadAllRisks(){
        RiskServiceImp service = new RiskServiceImp();
        return service.getRisk();
    }

    private List<Assets> loadAllAssets(){
        AssetServiceImp service = new AssetServiceImp();
        return service.getAsset();
    }

    class RiskReader implements Runnable{

        private int riskValue;
        private int assetValue;

        public void setRisk (Risk risk){
            this.riskValue = risk.getLoss();
        }

        public void setAsset (Assets asset){
            this.assetValue = asset.getWorth();
        }

        @Override
        public void run() {}

        private void seriesSetData(){
            if (riskValue != 0 & assetValue != 0) {
                series.getData().add(new XYChart.Data<>(stage, riskValue));
                series1.getData().add(new XYChart.Data<>(stage + 0.25, assetValue));
                series2.getData().add(new XYChart.Data<>(stage + 0.5, (riskValue + assetValue) / 2));
            }
        }
    }
}
