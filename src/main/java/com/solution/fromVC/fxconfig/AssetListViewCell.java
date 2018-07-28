package com.solution.fromVC.fxconfig;

import com.solution.fromVC.model.Assets;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.io.IOException;

public class AssetListViewCell extends ListCell<Assets>{


    @FXML
    private ImageView assetImg;

    @FXML
    private Label assetLabel;

    @FXML
    private Label worthLabel;

    @FXML
    private GridPane gridPane;

    @FXML
    private ImageView worthImg;

    private FXMLLoader fxmlLoader;

    @Override
    protected void updateItem(Assets asset, boolean empty) {
        super.updateItem(asset, empty);

        if (empty || asset == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/assetListCell.fxml"));
                fxmlLoader.setController(this);
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            assetLabel.setText(asset.getName());
            worthLabel.setText(String.valueOf(asset.getWorth()));

            if(asset.getWorth() >= 4){
                setId("important");
                gridPane.getStylesheets().add("fxml/css/listView.css");
            }

            assetImg.setImage(new Image("fxml/css/pictures/assetCellView.png"));
            if(asset.getWorth() >= 4){
                worthImg.setImage(new Image("fxml/css/pictures/assetWorth(higth).png"));
                worthLabel.setTextFill(Color.RED);
            }else {
                worthImg.setImage(new Image("fxml/css/pictures/assetWorth(norm).png"));
            }
            setText(null);
            setGraphic(gridPane);
        }
    }
}
