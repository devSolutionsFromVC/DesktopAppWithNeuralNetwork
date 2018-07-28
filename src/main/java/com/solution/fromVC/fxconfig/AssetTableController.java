package com.solution.fromVC.fxconfig;

import com.solution.fromVC.model.Assets;
import com.solution.fromVC.service.AssetServiceImp;
import javafx.beans.property.SimpleBooleanProperty;
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

public class AssetTableController implements Initializable{

    private ObservableList<Assets> list = FXCollections.observableArrayList();

    @FXML
    private TableView<Assets> tableView;

    @FXML
    private TableColumn<Assets, String> assetCol;

    @FXML
    private TableColumn<Assets, String> descCol;

    @FXML
    private TableColumn<Assets, String> worthCol;

    @FXML
    private TableColumn<Assets, Boolean> confCol;

    @FXML
    private TableColumn<Assets, Boolean> integrityCol;

    @FXML
    private TableColumn<Assets, Boolean> accessCol;

    @FXML
    private AnchorPane anch;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCol();
        loadData();
    }

    private void loadData() {
        AssetServiceImp service = new AssetServiceImp();
        List<Assets> assets = service.getAsset();
        list.addAll(assets);
        tableView.getItems().setAll(list);
    }

    private void initCol() {
        assetCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        worthCol.setCellValueFactory(new PropertyValueFactory<>("worth"));
        confCol.setCellValueFactory(new PropertyValueFactory<>("confidentiality"));
        integrityCol.setCellValueFactory(new PropertyValueFactory<>("integrity"));
        accessCol.setCellValueFactory(new PropertyValueFactory<>("accessibility"));
    }

    public static class Asset{
        private final SimpleStringProperty name;
        private final SimpleStringProperty description;
        private final SimpleStringProperty worth;
        private final SimpleBooleanProperty confidentiality;
        private final SimpleBooleanProperty integrity;
        private final SimpleBooleanProperty accessibility;

        public Asset(String asset, String description, String worth, Boolean confidentiality, Boolean integrity, Boolean accessibility) {
            this.name = new SimpleStringProperty(asset);
            this.description = new SimpleStringProperty(description);
            this.worth = new SimpleStringProperty(worth);
            this.confidentiality = new SimpleBooleanProperty(confidentiality);
            this.integrity = new SimpleBooleanProperty(integrity);
            this.accessibility = new SimpleBooleanProperty(accessibility);
        }

        public String getName() {
            return name.get();
        }

        public String getDescription() {
            return description.get();
        }

        public String getWorth() {
            return worth.get();
        }

        public boolean getConfidentiality() {
            return confidentiality.get();
        }

        public boolean getIntegrity() {
            return integrity.get();
        }

        public boolean getAccessibility() {
            return accessibility.get();
        }

    }

    @FXML
    void removeAssetFromTable(ActionEvent event) {
        ObservableList<Assets> assetSelected, allAssets;
        allAssets = tableView.getItems();
        assetSelected = tableView.getSelectionModel().getSelectedItems();
        AssetServiceImp service = new AssetServiceImp();
        service.removeAsset(assetSelected);
        assetSelected.forEach(allAssets::remove);
    }
}
