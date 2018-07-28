package com.solution.fromVC.fxconfig;

import com.solution.fromVC.service.RiskServiceImp;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RiskTableController implements Initializable{

    private ObservableList<com.solution.fromVC.model.Risk> list = FXCollections.observableArrayList();

    @FXML
    private AnchorPane anchPane;

    @FXML
    private TableColumn<com.solution.fromVC.model.Risk, String> descCol;

    @FXML
    private TableColumn<com.solution.fromVC.model.Risk, String> likehoodCol;

    @FXML
    private TableColumn<com.solution.fromVC.model.Risk, String> riskCol;

    @FXML
    private TableView<com.solution.fromVC.model.Risk> tableView;

    @FXML
    private TableColumn<com.solution.fromVC.model.Risk, String> lossCol;

    @FXML
    private TableColumn<com.solution.fromVC.model.Risk, String> valueCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCol();
        loadData();
    }

    private void loadData() {
        RiskServiceImp service = new RiskServiceImp();
        List<com.solution.fromVC.model.Risk> risk = service.getRisk();
        list.addAll(risk);
        tableView.getItems().setAll(list);
    }

    private void initCol() {
        riskCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        likehoodCol.setCellValueFactory(new PropertyValueFactory<>("likelihood"));
        lossCol.setCellValueFactory(new PropertyValueFactory<>("loss"));
        valueCol.setCellValueFactory(new PropertyValueFactory<>("value"));
    }

    public static class Risk{
        private final SimpleStringProperty name;
        private final SimpleStringProperty description;
        private final SimpleStringProperty likelihood;
        private final SimpleStringProperty loss;
        private final SimpleStringProperty value;

        public Risk(String risk, String description, String likelihood, String loss, String value) {
            this.name = new SimpleStringProperty(risk);
            this.description = new SimpleStringProperty(description);
            this.likelihood = new SimpleStringProperty(likelihood);
            this.loss = new SimpleStringProperty(loss);
            this.value = new SimpleStringProperty(value);
        }

        public String getName() {
            return name.get();
        }

        public String getDescription() {
            return description.get();
        }

        public String getLikelihood() {
            return likelihood.get();
        }

        public String getLoss() {
            return loss.get();
        }

        public String getValue() {
            return value.get();
        }

    }


    @FXML
    void deleteRisks(ActionEvent event) {
        ObservableList<com.solution.fromVC.model.Risk> selectedRisks, allRisks;
        allRisks = tableView.getItems();
        selectedRisks = tableView.getSelectionModel().getSelectedItems();
        RiskServiceImp service = new RiskServiceImp();
        service.removeRisks(selectedRisks);
        selectedRisks.forEach(allRisks::remove);
    }
}
