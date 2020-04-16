Bejeweled: level is completed
 * Inside the game.engine class, the gameHandles will update the last turn. At this point, the game.engine now asks the level builder to check game completion. This condition defined by xml is then run. In this case it would be if the grid is all one type, i.e. empty. In such a case, then the level builder adds a new level event to the updateObject. This is then called which tells the level builder to form the new level. 

Snake: head runs into body
 * Inside of the game class, the game.engine.executeEvent() function will be called seeing as this is a step type game that runs regardless. Inside of this execute event function, the grid is passed to the game handler, where given that it is a step action, it will determine which cells it must interact with and then modify all those cells based on the rules that come with this kind of event. In this case the rules would be if the head touches the edge or a snake cell then the game is over. When the head runs into the body, the cell it is trying to move to will be a body piece. This will determine that the loss rules were triggered and as such the return update object which contains a copy of the updated grid along with other necessary info will contain a boolean that specifies that the game is now over. Upon returning this object, the game object for this game will pass the necessary info to the game.view, in this case telling it to display game over message.

Snake: normal step event
 * Inside of the game class, the game.engine.executeEvent() function will be called seeing as this is a step type game that runs regardless. Inside of this execute event function, the grid is passed to the game handler, where given that it is a step action, it will determine which cells it must interact with and then modify all those cells based on the rules that come with this kind of event. In this case the rules would be if the head touches the edge or a snake cell then the game is over, and if it touches a food source/new block it will grow. When the head runs into empty space, the grid is updated as normal. The game is not over, so the update return object specifies the game is not over and a copy of the grid. Upon returning this object, the game object for this game will pass the necessary info to the game.view, in this case updating the display grid to hold new information of the locations of different cells.

Snake: head hits edge
 * Inside of the game class, the game.engine.executeEvent() function will be called seeing as this is a step type game that runs regardless. Inside of this execute event function, the grid is passed to the game handler, where given that it is a step action, it will determine which cells it must interact with and then modify all those cells based on the rules that come with this kind of event. In this case the rules would be if the head touches the edge or a snake cell then the game is over. When the head runs into the edge, there is no valid location for it to move to. This will determine that the loss rules were triggered and as such the return update object which contains a copy of the updated grid along with other necessary info will contain a boolean that specifies that the game is now over. Upon returning this object, the game object for this game will pass the necessary info to the game.view, in this case telling it to display game over message.

Snake: try to turn 180
 * Inside of the game class, the game.engine.executeEvent() function will be called seeing as this is a step type game that runs regardless. Inside of this execute event function, the grid is passed to the game handler, where given that it is a step action, it will determine which cells it must interact with and then modify all those cells based on the rules that come with this kind of event. This type of event is an snake arrow key event, which means it acts only on the head cell and specifies its orientation. The rules for orientation here would have a case specifying that the cell cannot point immediately in its opposite direction. The cell will not be updated because nothing can happen. The grid will be returned as normal in the update return object. Upon returning this object, the game object for this game will pass the necessary info to the game.view, in this case showing no visible change until the next normal step occurs.

Snake: add new block to collect
 * Within the GameHandler, after a block has been consumed by snakehead, the gamehandler will update grid to look as it should. In the update object that is returned, a RandomBlockPlacer event would be stored, which after the game.view has been updated this new event will be passed to the game.engine which will select from all empty cell types a location to put a new block.

Minesweeper: you clear the whole level
 * Within Engine, after game handler has handled most recent event, the level handler will check to see if the game has been completed. It will see there are no more basic empty cell types. As a result, it will specify in the update return object that the game is over and the player has won. This object will be returned to game.engine, which will pass the necessary info to game.view to change display.

Minesweeper: Click a bomb
 * Within GameHandler, the event that is passed would be a click with a reference to a specific cell that was clicked. The cell specified would be updated in grid based on what type it was according to the rules. Seeing as the cell is a bomb and the player clicked it, the bomb would be displayed, and the update return object would be set to specify that the game has been lost. Upon returning this object to game.engine, the game.view will update as necessary to display that the game is over. 

Minesweeper: Place a flag
 * Within GameHandler, the event that is passed would be a right click with a reference to a specific cell that was clicked. The cell specified would be updated in grid based on what type it was according to the rules. If the cell is hidden, it will place a flag there. If it has already been revealed then the rules would specify that a flag cannot be placed. The update return object would have this new updated grid. Upon returning this object to game.engine, the game.view will update as necessary.

Open new game after losing previous
 * This is quite simple. The player will delete the old game object (game.view and game.engine) and create a new version based off the selected game file. 

Pause the game
 * The player game.controller will tell the game.view to display window pause. This window is a custom game.view we have created. It contains the buttons resume, restart and main menu. These all have associated events that call methods in the player to complete the function. 

Start a game from game.Main Menu
 * The player has a main menu game.view. This is hardcoded and contains the various game options. When a game is picked, the player creates a new game object with the associated file. 

2048:  right arrow
 * Within the Engine, execute event would be called which will pass the right arrow event to the game handler. This will determine that the right arrow event for 2048 should be executed on all cells as its set of things to interact with. The rules that are specified for this type of event would be to combine cells that have the same number. The grid would be iterated over from right to left in this case row by row, moving cells as far right as possible without colliding, and when they do collide check if they are able to combine. The grid would be updated and passed back to game class in updateobject. The game object would pass the copy of the grid to the game.view to update as needed.

2048: all blocks full
 * Within gamehandler, after an arrowkey event occurs, the update object will have a addRandomNumberBlock event stored to be set to happen next after the game.view is updated. Once this happens, if there is no new space for a block to be added, then the update returnobject would store that the game is over and the game.view would be modified from game.engine as needed.

2048: reached 2048 
 * Within gamehandler, after an arrowkey event occurs, the grid will be handed to the levelhandler, which will determine whether or not any of the cells contain the number 2048. If they do, the update return object will hold the updated grid as well as the fact that the game is over and user has won. The game.view will be updated with this message.

Have two games running:
 * The player holds a list of current game objects. These objects contain both the game.view and game.engine for a specific game. Whichever game has focus will have the active listeners. The other games are all paused until focus shifts again. 
 
 
