package game.view;

import game.controller.GameStorageHandler;
import game.engine.UpdateObject;
import game.parse.XMLException;
import game.util.*;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public class DynamicView implements View {

    public static final String DEFAULT_IMAGE = "questionMark";
    public static final String FILE_PATH = "StateImages/";
    public static final String EXTENSION = ".gif";
    public static final Map<String, Double> ROTATE_MAP = Map.of(
            "LEFT", -90.0,
            "RIGHT", 90.0,
            "UP", 0.0,
            "DOWN", 180.0
    );

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
    private Runnable resetFunc;
    private Runnable pausePlayFunc;
    private double gridWidth;
    private double gridHeight;

    public DynamicView(double sceneWidth, double sceneHeight, double gridWidth, double gridHeight, Map<String, String> images, boolean changingColors, String saveLocation){
        initializeDisplay();
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
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

    public void setResetRunnable(Runnable r){
        resetFunc = r;
    }

    public void setPausePlayRunnable(Runnable r){
        pausePlayFunc = r;
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
        if(grid == null){
            return;
        }
        int height = grid.getHeight();
        int width = grid.getWidth();

        grid.loop((c) -> {
            Cell cell = grid.getCell(c.x, c.y);
            String image = getImageFile(cell.getValue(), cell.getType());
            Node n = null;
            if(image!=null) {
                ImageView view = makeImage(image);
                n=view;
                view.setLayoutX(gridWidth / width * c.x);
                view.setLayoutY(gridHeight / height * c.y);
                view.setFitWidth(gridWidth / width);
                view.setFitHeight(gridHeight / height);
                view.setRotate(ROTATE_MAP.get(cell.getDirection()));
                gridDisplay.add(view,c.x,c.y);
            }
            else{
                AnchorPane pane = makePane(c, width, height);
                Button l = makeNumber(cell.getValue());
                n = l;
                pane.getChildren().add(l);
                gridDisplay.add(pane,c.x,c.y);
            }

            n.setOnMouseClicked((e) -> eventCaller.accept(new ClickedAction("ClickedCell", c)));
        });
    }

    private AnchorPane makePane(Coordinates c, int width, int height){
        AnchorPane ret = new AnchorPane();
        ret.setPrefWidth(gridHeight / width);
        ret.setPrefHeight(gridHeight / height);
        ret.setLayoutX(gridWidth / width * c.x);
        ret.setLayoutY(gridWidth / height * c.y);
        return ret;
    }

    private Button makeNumber(int value) {

        Button b = new Button(Integer.toString(value));


        if(usingBackRound){
            b.setBackground(new Background(new BackgroundFill(getColor(value), null, null)));
        }
        else {
            b.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        }
        AnchorPane.setTopAnchor(b, 0.0);
        AnchorPane.setBottomAnchor(b, 0.0);
        AnchorPane.setLeftAnchor(b, 0.0);
        AnchorPane.setRightAnchor(b, 0.0);
        b.setFocusTraversable(false);
        return b;
    }

    private Paint getColor(int value) {
        return Color.hsb(1.0,.05 + ((Math.log10(value)/Math.log10(2) * .1)%1), 1.0);
    }

    private String getImageFile(int value, String type) {
        if(imageMap.containsKey(type)){
            return imageMap.get(type);
        }
        for(String s: imageMap.keySet()){
            try{
                String[] split = s.split(" ");
                int lowerIndex = Integer.parseInt(split[0]);
                int upperIndex = Integer.MAX_VALUE;
                if(split.length>1){
                    if(split.length>2){
                        upperIndex = Integer.parseInt(split[3]);
                    }
                    if(value >= lowerIndex && value<= upperIndex){
                        return imageMap.get(s);
                    }
                }
                else{
                    if(value == lowerIndex){
                        return imageMap.get(s);
                    }
                }
            }
            catch (Exception e){
                continue;
            }

        }
        return null;
    }

    private ImageView makeImage(String fileName) throws XMLException{
        Image image = null;
        if(!fileName.equals("empty")){
            try{
                ClassLoader classLoader = ClassLoader.getSystemClassLoader();
                FileInputStream inputStream = new FileInputStream(Objects.requireNonNull(classLoader.getResource(FILE_PATH + fileName + EXTENSION)).getFile());
                image = new Image(inputStream);
            } catch (Exception e) {
                try{
                    ClassLoader classLoader = ClassLoader.getSystemClassLoader();
                    FileInputStream inputStream = new FileInputStream(
                            Objects.requireNonNull(classLoader.getResource(FILE_PATH + DEFAULT_IMAGE + EXTENSION)).getFile());
                    image = new Image(inputStream);
                } catch (Exception ex) {
                    throw new XMLException("Image not found: "+ fileName);
                }
            }
        }
        return new ImageView(image);
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
        Method m = getFunction(name,funcName);
        b.setOnAction(e -> {
            try{
                m.invoke(this);
            }
            catch(Exception ex){
                gameMessages.setText("Error With Button Action");
            }
        });
        bottomDisplay.getChildren().add(b);
    }

    public void addDisplayInfo(String initMessage){
        gameMessages = new Text(initMessage);
        bottomDisplay.getChildren().add(gameMessages);
    }

    private Method getFunction(String name, String funcName) {
        try {
            Method m = DynamicView.class.getDeclaredMethod(funcName);
            return m;
        } catch (Exception e) {
            throw new XMLException("Failed to make UI Element: " + name);
        }
    }

   public void reset(){
        resetFunc.run();
   }

   public void pausePlay(){
        pausePlayFunc.run();
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
