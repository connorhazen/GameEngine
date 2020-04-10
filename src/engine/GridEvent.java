package engine;

import engine.interaction.Interaction;
import engine.operation.Operation;
import engine.rules.Rules;
import util.*;

import java.lang.reflect.Constructor;
import java.util.List;

public class GridEvent implements Event {

    public static final String OUTTER_PACKAGE = "engine.";
    public static final String OPERATION_PACKAGE = OUTTER_PACKAGE+"operation.";
    public static final String INTERACTION_PACKAGE = OUTTER_PACKAGE+"interaction.";
    public static final String RULES_PACKAGE = OUTTER_PACKAGE+"rules.";

    private Operation myOperation;
    private Interaction myInteraction;
    private Rules myRules;
    private Action nextAction;

    public GridEvent(String operation, String interaction, String rules) {
        myOperation = (Operation)createObjectOf(operation, OPERATION_PACKAGE);
        myInteraction = (Interaction)createObjectOf(interaction, INTERACTION_PACKAGE);
        myRules = (Rules)createObjectOf(rules, RULES_PACKAGE);
        nextAction = null;
    }

    @Override
    public Grid execute(Grid currentGrid) {
        Grid2dArray copiedGrid = new Grid2dArray(currentGrid);
        myInteraction.setGrid(copiedGrid);
        while(myInteraction.hasNext()){
            List<MutableCell> currCells = myInteraction.next();
            if(myRules.canPerform(currCells)){
                myOperation.execute(currCells);
            }
        }
        return copiedGrid;
    }

    @Override
    public Action getNextAction() {
        return nextAction;
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
