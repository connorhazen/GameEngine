package game.controller;

import game.engine.UpdateObject;
import game.engine.UpdateStatus;
import game.util.Grid;

import java.io.*;

public class GameStorageHandler {

    public static void storeGame(UpdateObject gameState, String filename){
        try{
            FileOutputStream fil = new FileOutputStream(new File(filename));
            ObjectOutputStream oStream = new ObjectOutputStream(fil);
            oStream.writeObject(gameState.getGrid());
            oStream.close();
            fil.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    public static UpdateObject loadGame(String sourceLocation){
        try{
            FileInputStream fil = new FileInputStream(new File(sourceLocation));
            ObjectInputStream iStream = new ObjectInputStream(fil);
            Grid g = (Grid)iStream.readObject();
            iStream.close();
            fil.close();
            UpdateObject gameState = new UpdateStatus(g);
            return gameState;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
