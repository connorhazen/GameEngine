package game.view;

import game.controller.GameStorageHandler;
import game.engine.UpdateObject;
import game.parse.XMLException;
import game.util.Action;
import game.util.Grid;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.function.Consumer;

public class DynamicView implements View {

    private Scene display;
    private Group root;
    private Stage stage;
    private Consumer<Action> eventCaller;
    private Map<String, String> imageMap;
    private boolean usingBackRound = false;
    private GridPane gridDisplay;
    private BorderPane viewOrganizer;
    private HBox bottomDisplay;
    private UpdateObject currentUpdateObject;
    private String saveLocation;
    private Text gameMessages;

    public DynamicView(double sceneWidth, double sceneHeight, Map<String, String> images, boolean changingColors, String saveLocation){
        initializeDisplay();
        display = new Scene(root, sceneWidth, sceneHeight);
        stage.setScene(display);
        imageMap = images;
        stage.show();
        usingBackRound = changingColors;
        this.saveLocation = saveLocation+"/SavedGames/";
    }

    private void initializeDisplay() {
        stage = new Stage();
        root = new Group();
        viewOrganizer = new BorderPane();
        gridDisplay = new GridPane();
        bottomDisplay = new HBox();
        viewOrganizer.setCenter(gridDisplay);
        viewOrganizer.setBottom(bottomDisplay);
    }

    @Override
    public void updateGridDisplay(UpdateObject uo) throws XMLException {
        currentUpdateObject = uo;
        gridDisplay.getChildren().clear();
        displayGrid(uo.getGrid());
        if(!uo.getGameRunning()){
            System.out.println(uo.getGameRunning());
            displayStatusUO(uo);
        }
    }

    private void displayStatusUO(UpdateObject uo) {
        if(uo.getGameLost()){
            gameMessages.setText("YOU LOST");
        }
        if (uo.getGameWon()){
            gameMessages.setText("YOU WON");
        }
    }

    private void displayGrid(Grid grid) {
        //TODO: populate grid with cells correctly
    }

    @Override
    public void setEventCaller(Consumer<Action> run) {
        eventCaller = run;
    }

    @Override
    public Scene getScene() {
        return display;
    }

    @Override
    public Stage getStage() {
        return stage;
    }

    public void addButton(String name, String funcName){
        Button b = new Button(name);
        b.setOnAction(e -> getFunction(name,funcName));
        bottomDisplay.getChildren().add(b);
    }

    public void addDisplayInfo(String initMessage){
        gameMessages = new Text(initMessage);
        bottomDisplay.getChildren().add(gameMessages);
    }

    private void getFunction(String name, String funcName) {
        try {
            Method m = DynamicView.class.getDeclaredMethod(funcName);
        } catch (Exception e) {
            throw new XMLException("Failed to make UI Element: " + name);
        }
    }

   public void reset(){

   }

   public void pausePlay(){

   }

   public void saveGame(){
        try{
            GameStorageHandler.storeGame(currentUpdateObject,saveLocation + LocalDateTime.now() +".sav");
        }
        catch(Exception e){
            gameMessages.setText("Game Could Not Be Saved");
        }
   }

}
