package engine;
import game.controller.EngineFactory;
import game.engine.Engine;
import game.engine.animationHandlers.AnimationHandler;
import game.engine.animationHandlers.BaseAnimationHandler;
import game.engine.gameHandlers.BaseGameHandler;
import game.engine.gameHandlers.GameHandler;
import game.engine.levelHandlers.BaseLevelHandler;
import game.engine.levelHandlers.LevelHandler;
import game.util.Grid;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestEngineCreation {

    @Test
    void testEngineGridCreation(){
        GameHandler gh = new BaseGameHandler();
        AnimationHandler ah = new BaseAnimationHandler();
        LevelHandler lh = new BaseLevelHandler();
        EngineFactory.parseXML("games/2048/","engine.xml",gh,ah,lh);
        Engine e = new Engine(gh,ah,lh);
        assertTrue(e.getGrid().getGrid() instanceof Grid);
    }
}
