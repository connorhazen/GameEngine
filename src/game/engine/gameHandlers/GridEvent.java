package game.engine.gameHandlers;

import game.engine.ObjectMaker;
import game.engine.gameHandlers.interaction.Interaction;
import game.engine.gameHandlers.operation.Operation;
import game.engine.gameHandlers.rules.Rules;
import game.parse.XMLException;
import game.util.*;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class GridEvent implements Event {

    public static final String OUTER_PACKAGE = "game.engine.gameHandlers.";
    public static final String OPERATION_PACKAGE = OUTER_PACKAGE +"operation.";
    public static final String INTERACTION_PACKAGE = OUTER_PACKAGE +"interaction.";
    public static final String RULES_PACKAGE = OUTER_PACKAGE +"rules.";

    private Operation myOperation;
    private Interaction myInteraction;
    private Rules myRules;
    private Action ifExecute;
    private Action ifNoExecute;

    private Boolean didExecute;

    public GridEvent(List<String> operation, List<String> interaction, List<String> rules, Action ifExecute, Action ifNoExecute) {
        List<String> operationParams = new ArrayList<>(operation);
        List<String> rulesParams = new ArrayList<>(rules);
        List<String> interactionParams = new ArrayList<>(interaction);

        operationParams.remove(0);
        rulesParams.remove(0);
        interactionParams.remove(0);


        myOperation = (Operation) ObjectMaker.createObjectOf(operation.get(0), OPERATION_PACKAGE, operationParams);
        myInteraction = (Interaction)ObjectMaker.createObjectOf(interaction.get(0), INTERACTION_PACKAGE, interactionParams);
        myRules = (Rules)ObjectMaker.createObjectOf(rules.get(0), RULES_PACKAGE, rulesParams);
        this.ifExecute = ifExecute;
        this.ifNoExecute = ifNoExecute;
        didExecute = false;
    }

    @Override
    public Grid execute(Grid currentGrid) {
        Grid2dArray copiedGrid = new Grid2dArray(currentGrid);
        myInteraction.setGrid(copiedGrid);
        while(myInteraction.hasNext()){
            List<MutableCell> currCells = myInteraction.next();
            if(myRules.canPerform(currCells)){
                didExecute = true;
                myOperation.execute(currCells);
            }
        }
        return copiedGrid;
    }

    @Override
    public Action getNextAction() {
        if(didExecute){
            didExecute = false;
            return ifExecute;
        }
        return ifNoExecute;

    }

}
