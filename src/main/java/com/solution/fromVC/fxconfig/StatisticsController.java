package com.solution.fromVC.fxconfig;


import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import com.solution.fromVC.model.Assets;
import com.solution.fromVC.model.Risk;
import com.solution.fromVC.service.AssetServiceImp;
import com.solution.fromVC.service.HibernateUtil;
import com.solution.fromVC.service.RiskServiceImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.hibernate.Session;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StatisticsController implements Initializable{

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private PieChart pieChart;

    @FXML
    private JFXListView<Risk> riskListView;

    @FXML
    private JFXListView<Assets> assetListView;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;

    private ObservableList<PieChart.Data> data = FXCollections.observableArrayList();

    private ObservableList<Risk> riskObservableList;

    private ObservableList<Assets> assetObservableList;

    private Session session = HibernateUtil.getSessionFactory().openSession();

    private int allRisks;
    private double medValueRisks;
    private double medLossRisks;
    private double medLikelihoodRisks;

    private int allAssets;
    private double medWorthAssets;
    private double medConfidentialityAssets;
    private double medIntegrityAssets;
    private double medAccessibilityAssets;

    private final Label caption = new Label("");

    static String riskListParam = "";
    static String assetListParam = "";

    public StatisticsController() {
        riskObservableList = FXCollections.observableArrayList();
        riskObservableList.addAll(loadAllRisks());
        assetObservableList = FXCollections.observableArrayList();
        assetObservableList.addAll(loadAllAssets());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        riskInitValues();
        assetInitValues();
        drawSidePane();
        riskListView.setDisable(true);
        assetListView.setDisable(true);
        caption.setTextFill(Color.WHITE);
        caption.setStyle("-fx-font: 16 arial;");
        anchorPane.getChildren().add(caption);
    }

    private void riskInitValues() {
        List<Risk> riskList = session.createNamedQuery("Risk.getRisk", Risk.class).getResultList();
        allRisks = riskList.size();
        medValueRisks = riskList.stream().mapToDouble(Risk::getValue).average().getAsDouble();
        medLossRisks = riskList.stream().mapToDouble(Risk::getLoss).average().getAsDouble();
        medLikelihoodRisks = riskList.stream().mapToDouble(Risk::getLikelihood).average().getAsDouble();
    }

    private void assetInitValues() {
        List<Assets> resultList = session.createNamedQuery("Asset.getAsset", Assets.class).getResultList();
        allAssets = resultList.size();
        medWorthAssets = resultList.stream().mapToDouble(Assets::getWorth).average().getAsDouble();
        medConfidentialityAssets = resultList.stream().map(Assets::getConfidentiality).filter(a -> a.equals(true)).count();
        medAccessibilityAssets = resultList.stream().map(Assets::getAccessibility).filter(a -> a.equals(true)).count();
        medIntegrityAssets = resultList.stream().map(Assets::getIntegrity).filter(a -> a.equals(true)).count();
    }

    @FXML
    void riskStatActive(ActionEvent event) {
        data.clear();
        data.add(new PieChart.Data("All risks", allRisks));
        data.add(new PieChart.Data("Average risks value", medValueRisks));
        data.add(new PieChart.Data("Average risks loss", medLossRisks));
        data.add(new PieChart.Data("Average risks likelihood", medLikelihoodRisks));
        pieChart.setData(data);
        caption.setText("");
        for(final PieChart.Data data : pieChart.getData()){
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            riskListView.setDisable(false);
                            assetListView.setDisable(true);

                            riskListView.setItems(null);

                            caption.setTranslateX(event.getSceneX());
                            caption.setTranslateY(event.getSceneY());
                            caption.setText(String.valueOf(data.getPieValue()));

                            for(Risk risk : riskObservableList){
                                riskListParam = listCellRiskValueInfo(data.getName());
                                new RiskStatisticViewCell().updateItem(risk, false);
                            }

                            riskListView.setItems(riskObservableList);
                            riskListView.setCellFactory(riskListViewCell -> new RiskStatisticViewCell());
                        }
                    });
        }
    }

    private String listCellRiskValueInfo(String dataName) {
        String valueInfo = "";
        switch (dataName){
            case "All risks": valueInfo = "risks";
                break;
            case "Average risks value" : valueInfo = "value";
                break;
            case "Average risks loss" : valueInfo = "loss";
                break;
            case "Average risks likelihood" : valueInfo = "likelihood";
                break;
        }
        return valueInfo;
    }

    @FXML
    void assetStatActive(ActionEvent event) {
        data.clear();
        data.add(new PieChart.Data("All assets", allAssets));
        data.add(new PieChart.Data("Average assets worth", medWorthAssets));
        data.add(new PieChart.Data("Amount of confidentiality", medConfidentialityAssets));
        data.add(new PieChart.Data("Amount of accessibility", medAccessibilityAssets));
        data.add(new PieChart.Data("Amount of integrity", medIntegrityAssets));
        pieChart.setData(data);
        caption.setText("");
        for(final PieChart.Data data : pieChart.getData()){
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            assetListView.setDisable(false);
                            riskListView.setDisable(true);

                            assetListView.setItems(null);

                            caption.setTranslateX(event.getSceneX());
                            caption.setTranslateY(event.getSceneY());
                            caption.setText(String.valueOf(data.getPieValue()));

                            for(Assets asset : assetObservableList){
                                assetListParam = listCellAssetValueInfo(data.getName());
                                new AssetStatisticViewCell().updateItem(asset, false);
                            }

                            assetListView.setItems(assetObservableList);
                            assetListView.setCellFactory(assetListViewCell -> new AssetStatisticViewCell());
                        }
                    });
        }
    }

    private String listCellAssetValueInfo(String dataName) {
        String valueInfo = "";
        switch (dataName){
            case "All assets": valueInfo = "assets";
                break;
            case "Average assets worth" : valueInfo = "worth";
                break;
            case "Amount of confidentiality" : valueInfo = "confidentiality";
                break;
            case "Amount of accessibility" : valueInfo = "accessibility";
                break;
            case "Amount of integrity" : valueInfo = "integrity";
        }
        return valueInfo;
    }

    private void drawSidePane() {
        try {
            VBox vBox = FXMLLoader.load(getClass().getResource("/fxml/statisticDrawer.fxml"));
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

    private List<Risk> loadAllRisks(){
        RiskServiceImp service = new RiskServiceImp();
        return service.getRisk();
    }

    private List<Assets> loadAllAssets(){
        AssetServiceImp service = new AssetServiceImp();
        return service.getAsset();
    }

}
