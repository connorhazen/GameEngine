package game.view;

import game.controller.GameStorageHandler;
import game.engine.UpdateObject;
import game.parse.FileReader;
import game.parse.XMLException;
import game.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.HashMap;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.NumberBinding;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
    public static final String DATA ="data/";
    public static final String EXTENSION = ".gif";
    public static final int BUTTON_HEIGHT = 80;
    public static final int BUTTON_WIDTH = 80;
    public static final Map<String, Double> ROTATE_MAP = Map.of(
            "LEFT", -90.0,
            "RIGHT", 90.0,
            "UP", 0.0,
            "DOWN", 180.0
    );
    private static final int HBOX_HEIGHT = 100;

    private Scene display;
    private Map<String, Image> images;
    private Stage stage;
    private Consumer<Action> eventCaller;
    private Map<String, String> imageMap;
    private boolean usingBackRound = false;
    private Pane centerPane;
    private BorderPane viewOrganizer;
    private HBox bottomDisplay;
    private UpdateObject currentUpdateObject;
    private String saveLocation;
    private Label gameMessages;
    private Runnable resetFunc;
    private Runnable pausePlayFunc;


    public DynamicView(double sceneWidth, double sceneHeight, Map<String, String> images, boolean changingColors, String saveLocation){
        stage = new Stage();
        initializeDisplay();
        display = new Scene(viewOrganizer, sceneWidth, sceneHeight);
        stage.setScene(display);
        imageMap = images;
        stage.show();
        usingBackRound = changingColors;
        this.saveLocation = saveLocation+"/SavedGames/";
        try {
            makeImagesInMap();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }



    private void initializeDisplay() {
        viewOrganizer = new BorderPane();
        bottomDisplay = new HBox();
        centerPane = new Pane();
        bottomDisplay.setPrefHeight(HBOX_HEIGHT);
        viewOrganizer.setCenter(centerPane);
        viewOrganizer.setBottom(bottomDisplay);
    }

    @Override
    public void setResetRunnable(Runnable r){
        resetFunc = r;
    }

    @Override
    public void setPausePlayRunnable(Runnable r){
        pausePlayFunc = r;
    }

    @Override
    public void updateGridDisplay(UpdateObject uo) throws XMLException {
        currentUpdateObject = uo;
        centerPane.getChildren().clear();
        displayGrid(uo.getGrid());
        if(!uo.getGameRunning()){
            System.out.println(uo.getGameRunning());
            displayStatusUO(uo);
        }
    }

    private void displayStatusUO(UpdateObject uo) {
        if(uo.getGameLost()&&gameMessages!=null){
            gameMessages.setText("YOU LOST");
        }
        if (uo.getGameWon()&&gameMessages!=null){
            gameMessages.setText("YOU WON");
        }
    }

    private void displayGrid(Grid grid) {

        if(grid == null){
            return;
        }
        int height = grid.getHeight();
        int width = grid.getWidth();

        NumberBinding widthBind = Bindings.divide(centerPane.widthProperty(), width);
        NumberBinding heightBind = Bindings.divide(centerPane.heightProperty(), height);

        grid.loop((c) -> cellMaker(c, grid, heightBind, widthBind));
    }

    private void cellMaker(Coordinates c, Grid grid, NumberBinding heightBind,
        NumberBinding widthBind) {
        Cell cell = grid.getCell(c.x, c.y);
        String image = getImageFile(cell.getValue(), cell.getType());
        Node n = null;
        if(image!=null) {
            ImageView view = makeImage(image, cell, heightBind, widthBind);
            n=view;
            centerPane.getChildren().add(view);
        }
        else{
            AnchorPane pane = makePane(c, widthBind, heightBind);
            Button l = makeNumber(cell.getValue());
            n = l;
            pane.getChildren().add(l);
            centerPane.getChildren().add(pane);
        }
        n.setOnMouseClicked((e) -> eventCaller.accept(new ClickedAction("ClickedCell", c)));
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
        b.setMinWidth(BUTTON_WIDTH);
        b.setMinHeight(BUTTON_HEIGHT);
        b.setFocusTraversable(false);
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

    public void addDisplayInfo(String initial){
        gameMessages = new Label(initial);
        bottomDisplay.getChildren().add(gameMessages);
        gameMessages.setWrapText(true);

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

    private AnchorPane makePane(Coordinates c, NumberBinding width, NumberBinding height){
        AnchorPane ret = new AnchorPane();
        ret.maxWidthProperty().bind(width);
        ret.minWidthProperty().bind(width);
        ret.maxHeightProperty().bind(height);
        ret.minHeightProperty().bind(height);
        ret.layoutXProperty().bind(Bindings.multiply(width,c.x));
        ret.layoutYProperty().bind(Bindings.multiply(height,c.y));
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

                String trial = parseImageMapValues(s, value);
                if(trial!= null){
                    return trial;
                }
            }
            catch (Exception e){
                continue;
            }
        }
        return null;
    }

    private String parseImageMapValues(String s, int value) {
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
        return null;
    }

    private ImageView makeImage(String fileName, Cell cell,
        NumberBinding heightBind, NumberBinding widthBind) throws XMLException{
        Image image = null;
        if(!fileName.equals("empty")){
            image=images.get(fileName+EXTENSION);
        }

        ImageView view = new ImageView(image);
        view.fitHeightProperty().bind(heightBind);
        view.fitWidthProperty().bind(widthBind);
        view.layoutXProperty().bind(Bindings.multiply(widthBind,cell.getCoords().x));
        view.layoutYProperty().bind(Bindings.multiply(heightBind,cell.getCoords().y));
        view.setRotate(ROTATE_MAP.get(cell.getDirection()));
        return view;
    }

    private void makeImagesInMap() throws Exception {
        images = new HashMap<>();
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File inputStream = new File(DATA + FILE_PATH);

        for(File f : inputStream.listFiles()){
            String[] split = f.getName().split("/");
            String name = split[split.length-1];
            try{
                FileInputStream inputStream2 = new FileInputStream(
                    Objects.requireNonNull(classLoader.getResource(FILE_PATH + name)).getFile());

                images.put(name, new Image(inputStream2));

            }
            catch (Exception e){
                continue;
            }
        }
    }


}
