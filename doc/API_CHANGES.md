##GRIDguys
Connor Hazen - cch57
Charles Papandreou - cnp20

###View API
* REMOVED: displayWindow(). We are removing this method as all statuses will now be self contained to their associated classes.
* REMOVED: removeWindow(). This is the counterpart to the above method. Similar reason for removal. 
* ADDED: setEventCaller(), setResetRunnable(), setPausePlayRunnable(). These methods are used so the player could pass in the required lambdas that affect the controller. They could be passed in the constructor, but we did not want to change more API’s than necessary. 
* ADDED getScene(), getStage(). We added these methods so the player could set up the key listeners on the associated window. The stage is needed to determine if focused. 
##ENGINE
###EngineAPI
* ADDED: initializeGrid(). This was added to allow the game object to set up the grid. In particular, this was used to allow reset functionality to work.
* ADDED: setGrid(). This was added to allow us to load in grids to be stored in a game when loading in a previously saved game.
###GAMEHANLDER
* ADDED: GameHandler, Interaction, Rules, Operations internal APIs were all added to allow for different added components to all interact in the same manner. Making these interfaces and adding them as an internal API for the engine allows a very concrete framework within which we create new components for future games. This will ensure that they all interact within our program in the correct way.
###LEVELHANLDER
* ADDED: LevelHandler was added as an internal API for engine that would allow there to be a specified manner in which the engine accesses and passes information to the level handler about the grid in order to determine game ending states
###PLAYER
* ADDED: makeListeners(), handleEvent(), updateView(). We added these public methods even though they are never publicly accessed in our project. Instead, they exist in the case of a future networked player addition.  In such a case, events, updates and listeners would have additional calls from the paired player. 
###UPDATEOBJECT
* ADDED: getGameLost(), getGameWon(). We added these methods so the view could know the current game state.
* ADDED: setGameLost(), setGameWon(). We added these methods so the engine could set game state. 
* ADDED: getGameRunning(). This is used to determine if the game is either won or lost. 
* ADDED: getNextAction(). We added the functionality of follow events in the engine. This is where such an event is stored when the engine has completed its update. 
