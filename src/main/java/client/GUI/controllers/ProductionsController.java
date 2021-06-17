package client.GUI.controllers;

import client.ClientView;
import client.GUI.GUI;
import client.GUI.SceneName;
import com.google.gson.Gson;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import model.resource.ResourceType;
import network.Command;
import network.UserCommandsType;
import network.beans.MessageWrapper;
import network.beans.ProductionHandlerBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is the GUIController which handles the selection of productions to activate
 */
public class ProductionsController implements GUIController {
    /**
     * The client's GUI object
     */
    private GUI gui;
    /**
     * The object used to store all of the client's information
     */
    private ClientView clientView;
    /**
     * The object used to deserialize json messages
     */
    private Gson gson;

    /**
     * The graphical elements of this controller's scene
     */
    @FXML
    public ImageView inputCoin, inputServant, inputShield, inputStone, outputCoin, outputServant, outputShield, outputStone;
    @FXML
    public Label inputJollyLabel, inputCoinLabel, inputServantLabel, inputShieldLabel, inputStoneLabel, outputJollyLabel, outputCoinLabel, outputServantLabel, outputShieldLabel, outputStoneLabel, outputFaithLabel;
    @FXML
    public Button closeWindowButton, confirmButton, resetButton;
    @FXML
    public GridPane productionsGrid;
    @FXML
    public Text descriptionText;

    //CONSTRUCTORS

    /**
     * Handles initialization for this class by creating the deserializer
     */
    public void initialize() {
        this.gson = new Gson();
    }

    //SETTERS

    /**
     * Sets the GUI object for the controller
     *
     * @param gui of type GUI - the main GUI class.
     */
    @Override
    public void setGui(GUI gui) {
        this.gui = gui;
        this.clientView = gui.getClientView();
    }

    //PUBLIC METHODS

    /**
     * Updates the necessary parts of the scene based on what message was received from the server
     *
     * @param jsonMessage the message received from the server
     */
    @Override
    public void updateFromServer(String jsonMessage) {
        MessageWrapper response = gson.fromJson(jsonMessage, MessageWrapper.class);

        switch (response.getType()) {
            case PRODUCTIONHANDLER:

                ProductionHandlerBean productionHandlerBean = clientView.getProductionHandler(clientView.getUsername());

                //Update the interface
                drawProductions(productionHandlerBean);
                drawCurrentInput(productionHandlerBean);
                drawCurrentOutput(productionHandlerBean);

                //Set up interactibles
                enableSetupProductionChoiceButtons(productionHandlerBean);
                enableSetupJollyConversionButtons();

                descriptionText.setText("Please, select the productions you wish to activate. Before confirming, all jollies in input and output must be converted by clicking on the chosen resource.");

                break;
            default:
                System.out.println("Warning: received unexpected message " + jsonMessage);
        }
    }

    /**
     * Closes the popup
     */
    public void closeWindow() {
        gui.getSceneBySceneName(SceneName.PRODUCTIONS).getWindow().hide();
    }

    //PUBLIC COMMAND METHODS

    /**
     * Sends the selectProduction command to the server with the given parameters
     *
     * @param number the number of the production to choose
     */
    public void selectProduction(int number) {
        System.out.println("SelectProduction: number - " + number);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("number", number);
        Command command = new Command(UserCommandsType.selectProduction, parameters);
        gui.sendCommand(gson.toJson(command));
    }

    /**
     * Sends the resetProductionChoice command to the server
     */
    public void resetProductionChoice() {
        System.out.println("ResetProductionChoice");
        Command command = new Command(UserCommandsType.resetProductionChoice, null);
        gui.sendCommand(gson.toJson(command));
    }

    /**
     * Sends the chooseJollyInput command to the server with the given parameters
     *
     * @param resource the resource to convert the jolly into
     */
    public void chooseJollyInput(ResourceType resource) {
        System.out.println("ChooseJollyInput: resource - " + resource);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("resource", resource);
        Command command = new Command(UserCommandsType.chooseJollyInput, parameters);
        gui.sendCommand(gson.toJson(command));
    }

    /**
     * Sends the chooseJollyOutput command to the server with the given parameters
     *
     * @param resource the resource to convert the jolly into
     */
    public void chooseJollyOutput(ResourceType resource) {
        System.out.println("ChooseJollyOutput: resource - " + resource);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("resource", resource);
        Command command = new Command(UserCommandsType.chooseJollyOutput, parameters);
        gui.sendCommand(gson.toJson(command));
    }

    /**
     * Sends the confirmProductionChoice command to the server
     */
    public void confirmProductionChoice() {
        System.out.println("ConfirmProductionChoice");
        Command command = new Command(UserCommandsType.confirmProductionChoice, null);
        gui.sendCommand(gson.toJson(command));
        closeWindow();
    }

    //PRIVATE SETUP METHODS

    /**
     * Handles enabling for the interactive elements necessary for choosing productions
     *
     * @param productionHandlerBean the object containing the production handler information
     */
    private void enableSetupProductionChoiceButtons(ProductionHandlerBean productionHandlerBean) {
        ObservableList<Node> productionsChildren = productionsGrid.getChildren();
        int[] productions = productionHandlerBean.getProductions();

        int i = 0;
        //Set the spots corresponding to a productions to the select production method
        for (; i < productions.length; i++){
            int finalI = i + 1;
            productionsChildren.get(i).setOnMouseClicked(e -> selectProduction(finalI));
        }
        //Make the rest inert
        for (; i < productionsChildren.size(); i++){
            productionsChildren.get(i).setOnMouseClicked(null);
        }

        Map<ResourceType, Integer> input = productionHandlerBean.getInput();
        Map<ResourceType, Integer> output = productionHandlerBean.getOutput();
        //Chooses activation for confirm button (buttons' method call is binded in the fxml file since they never change)
        confirmButton.setDisable(input.get(ResourceType.UNKNOWN) != 0 || output.get(ResourceType.UNKNOWN) != 0);
    }

    /**
     * Handles enabling for the interactive elements necessary for choosing which resource to convert jollies into
     */
    private void enableSetupJollyConversionButtons() {
        //Sets input buttons
        inputCoin.setOnMouseClicked(e -> chooseJollyInput(ResourceType.COIN));
        inputServant.setOnMouseClicked(e -> chooseJollyInput(ResourceType.SERVANT));
        inputShield.setOnMouseClicked(e -> chooseJollyInput(ResourceType.SHIELD));
        inputStone.setOnMouseClicked(e -> chooseJollyInput(ResourceType.STONE));

        //Sets output buttons
        outputCoin.setOnMouseClicked(e -> chooseJollyOutput(ResourceType.COIN));
        outputServant.setOnMouseClicked(e -> chooseJollyOutput(ResourceType.SERVANT));
        outputShield.setOnMouseClicked(e -> chooseJollyOutput(ResourceType.SHIELD));
        outputStone.setOnMouseClicked(e -> chooseJollyOutput(ResourceType.STONE));
    }

    //PRIVATE DRAWING METHODS

    /**
     * Updates the sections of the scene pertaining to the available productions
     *
     * @param productionHandlerBean the object containing the production handler information
     */
    private void drawProductions(ProductionHandlerBean productionHandlerBean) {
        int[] productions = productionHandlerBean.getProductions();
        boolean[] activeProductions = productionHandlerBean.getActiveProductions();
        List<Node> productionChildren = productionsGrid.getChildren();

        //Base production
        ((ImageView) productionChildren.get(0)).setImage(new Image("/graphics/BaseProductionCard.png"));
        if (activeProductions[0])
            productionChildren.get(0).getStyleClass().add("selectedCard");
        else {
            productionChildren.get(0).getStyleClass().clear();
            productionChildren.get(0).getStyleClass().add("card");
        }

        //The rest of productions
        for (int i = 1; i < productionChildren.size(); i++) {
            Image card;
            if (i < productions.length) {
                card = new Image("/graphics/front/" + productions[i] + ".png");
                if (activeProductions[i])
                    productionChildren.get(i).getStyleClass().add("selectedCard");
                else {
                    productionChildren.get(i).getStyleClass().clear();
                    productionChildren.get(i).getStyleClass().add("card");
                }
            } else {
                card = new Image("/graphics/back/leadercardBack.png");
                productionChildren.get(i).getStyleClass().clear();
            }
            ((ImageView) productionChildren.get(i)).setImage(card);
        }
    }

    /**
     * Updates the sections of the scene pertaining to the current total productions input
     *
     * @param productionHandlerBean the object containing the production handler information
     */
    private void drawCurrentInput(ProductionHandlerBean productionHandlerBean) {
        Map<ResourceType, Integer> input = productionHandlerBean.getInput();

        inputJollyLabel.setText(input.get(ResourceType.UNKNOWN).toString());
        inputCoinLabel.setText(input.get(ResourceType.COIN).toString());
        inputServantLabel.setText(input.get(ResourceType.SERVANT).toString());
        inputShieldLabel.setText(input.get(ResourceType.SHIELD).toString());
        inputStoneLabel.setText(input.get(ResourceType.STONE).toString());
    }

    /**
     * Updates the sections of the scene pertaining to the current total productions output
     *
     * @param productionHandlerBean the object containing the production handler information
     */
    private void drawCurrentOutput(ProductionHandlerBean productionHandlerBean) {
        Map<ResourceType, Integer> output = productionHandlerBean.getOutput();

        outputJollyLabel.setText(output.get(ResourceType.UNKNOWN).toString());
        outputCoinLabel.setText(output.get(ResourceType.COIN).toString());
        outputServantLabel.setText(output.get(ResourceType.SERVANT).toString());
        outputShieldLabel.setText(output.get(ResourceType.SHIELD).toString());
        outputStoneLabel.setText(output.get(ResourceType.STONE).toString());
        outputFaithLabel.setText(output.get(ResourceType.FAITH).toString());
    }
}
