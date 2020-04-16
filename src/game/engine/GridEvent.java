package game.engine;

import game.engine.interaction.Interaction;
import game.engine.operation.Operation;
import game.engine.rules.Rules;
import game.util.*;

import java.lang.reflect.Constructor;
import java.util.List;

public class GridEvent implements Event {

    public static final String OUTER_PACKAGE = "game.engine.";
    public static final String OPERATION_PACKAGE = OUTER_PACKAGE +"operation.";
    public static final String INTERACTION_PACKAGE = OUTER_PACKAGE +"interaction.";
    public static final String RULES_PACKAGE = OUTER_PACKAGE +"rules.";

    private Operation myOperation;
    private Interaction myInteraction;
    private Rules myRules;
    private Action nextAction;

    private Boolean didExecute;

    public GridEvent(String operation, String interaction, String rules, Action nextAction) {
        myOperation = (Operation)createObjectOf(operation, OPERATION_PACKAGE);
        myInteraction = (Interaction)createObjectOf(interaction, INTERACTION_PACKAGE);
        myRules = (Rules)createObjectOf(rules, RULES_PACKAGE);
        this.nextAction = nextAction;
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
            return nextAction;
        }
        return null;

    }

    private Object createObjectOf(String rules, String pack) {
        Object obj = null;
        try {
            Class c = Class.forName(pack + rules);
            Constructor objConstruct = c.getDeclaredConstructor();
            objConstruct.setAccessible(true);
            obj = objConstruct.newInstance();
        } catch (Exception e) {
            //TODO: Class you were trying to create had a problem make exception
            System.out.println("Class you were trying to create had a problem");
        }
        return obj;
    }
}
