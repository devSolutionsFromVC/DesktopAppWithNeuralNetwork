package com.solution.fromVC.fxconfig;

import com.solution.fromVC.model.Risk;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.io.IOException;

public class RiskListViewCell extends ListCell<Risk>{

    @FXML
    private Label riskLabel;

    @FXML
    private Label liLabel;

    @FXML
    private Label loLabel;

    @FXML
    private GridPane gridPane;
//
//    @FXML
//    private AnchorPane anchor;

    @FXML
    private ImageView rImage;

    @FXML
    private ImageView liImage;

    @FXML
    private ImageView loImage;

    private FXMLLoader fxmlLoader;

    @Override
    protected void updateItem(Risk risk, boolean empty) {
        super.updateItem(risk, empty);

        if (empty || risk == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/riskListCell.fxml"));
                fxmlLoader.setController(this);
//                try {
//                    fxmlLoader = FXMLLoader.load(getClass().getResource("/fxml/riskListCell.fxml"));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            riskLabel.setText(risk.getName());
            liLabel.setText(String.valueOf(risk.getLikelihood()));
            loLabel.setText(String.valueOf(risk.getLoss()));

            if(risk.getValue() >= 8){
                setId("important");
                gridPane.getStylesheets().add("fxml/css/listView.css");
            }

            rImage.setImage(new Image("fxml/css/pictures/riskCellView.png"));
            if(risk.getLikelihood() >= 4){
                liImage.setImage(new Image("fxml/css/pictures/riskLike(hight)CellView.png"));
                liLabel.setTextFill(Color.RED);
//                liLabel.setStyle("-fx-background-color: yellow");
            }else {
                liImage.setImage(new Image("fxml/css/pictures/riskLike(norm)CellView.png"));
            }
            if(risk.getLoss() >= 4){
                loImage.setImage(new Image("fxml/css/pictures/riskLoss(higth)CellView.png"));
                loLabel.setTextFill(Color.RED);
//                loLabel.setStyle("-fx-background-color: yellow");
            }else {
                loImage.setImage(new Image("fxml/css/pictures/riskLoss(norm)CellView.png"));
            }
            setText(null);
            setGraphic(gridPane);
        }
    }
}
