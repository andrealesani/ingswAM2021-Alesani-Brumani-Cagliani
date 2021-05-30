package client.GUI.controllers;

import client.GUI.GUI;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class FourPlayersController implements GUIController{
    private GUI gui;

    @FXML
    private BorderPane paneBoard;
    @FXML
    private GridPane leftGrid;
    @FXML
    private ImageView market;
    @FXML
    private GridPane playerBoardsGrid;
    @FXML
    public ImageView firstPB;
    @FXML
    public ImageView secondPB;
    @FXML
    public ImageView thirdPB;
    @FXML
    public ImageView fourthPB;
    @FXML
    private ImageView card00;
    @FXML
    private ImageView card01;
    @FXML
    private ImageView card02;
    @FXML
    private ImageView card03;
    @FXML
    private ImageView card10;
    @FXML
    private ImageView card11;
    @FXML
    private ImageView card12;
    @FXML
    private ImageView card13;
    @FXML
    private ImageView card20;
    @FXML
    private ImageView card21;
    @FXML
    private ImageView card22;
    @FXML
    private ImageView card23;
    @FXML
    private ImageView marble00;
    @FXML
    private ImageView marble01;
    @FXML
    private ImageView marble02;
    @FXML
    private ImageView marble03;
    @FXML
    private ImageView marble10;
    @FXML
    private ImageView marble11;
    @FXML
    private ImageView marble12;
    @FXML
    private ImageView marble13;
    @FXML
    private ImageView marble20;
    @FXML
    private ImageView marble21;
    @FXML
    private ImageView marble22;
    @FXML
    private ImageView marble23;

    public void initialize() {

        market.fitWidthProperty().bind(leftGrid.widthProperty());
        market.fitHeightProperty().bind(leftGrid.heightProperty().divide(2));

        firstPB.fitWidthProperty().bind(playerBoardsGrid.widthProperty().divide(2));
        firstPB.fitHeightProperty().bind(playerBoardsGrid.heightProperty().divide(2));

        secondPB.fitWidthProperty().bind(playerBoardsGrid.widthProperty().divide(2));
        secondPB.fitHeightProperty().bind(playerBoardsGrid.heightProperty().divide(2));

        thirdPB.fitWidthProperty().bind(playerBoardsGrid.widthProperty().divide(2));
        thirdPB.fitHeightProperty().bind(playerBoardsGrid.heightProperty().divide(2));

        fourthPB.fitWidthProperty().bind(playerBoardsGrid.widthProperty().divide(2));
        fourthPB.fitHeightProperty().bind(playerBoardsGrid.heightProperty().divide(2));

    }

    public void setGameBoard(URL location, ResourceBundle resources) {
        GridPane gridPane = new GridPane();
    }

    @Override
    public void setGui(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void updateFromServer(String jsonMessage){
        //TODO
    }
}
