package game.engine;

import game.engine.interaction.Interaction;
import game.engine.operation.Operation;
import game.engine.rules.Rules;
import game.parse.XMLException;
import game.util.*;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class GridEvent implements Event {

    public static final String OUTER_PACKAGE = "game.engine.";
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


        myOperation = (Operation)createObjectOf(operation.get(0), OPERATION_PACKAGE, operationParams);
        myInteraction = (Interaction)createObjectOf(interaction.get(0), INTERACTION_PACKAGE, interactionParams);
        myRules = (Rules)createObjectOf(rules.get(0), RULES_PACKAGE, rulesParams);
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

    private Object createObjectOf(String rules, String pack, List<String> argument) throws XMLException {
        Object obj = null;
        try {
            Class c = Class.forName(pack + rules);
            Constructor objConstruct;
            try{
                Class[] params = new Class[] {List.class};
                objConstruct =  c.getDeclaredConstructor(params);
                objConstruct.setAccessible(true);
                obj = objConstruct.newInstance(argument);
            }
            catch (Exception e ){
                objConstruct = c.getDeclaredConstructor();
                objConstruct.setAccessible(true);
                obj = objConstruct.newInstance();
            }

        } catch (Exception e) {
            throw new XMLException("Bad event declaration: type - " + rules + " Args - " + argument);
        }
        return obj;
    }
}
