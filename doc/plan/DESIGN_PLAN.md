#Design Plan
###gridGUYS

##Introduction

Our team has chosen to create an API that facilitates the creation of grid based games. Examples of such games include: 2048, candy crush, snake, minesweeper,  etc.

To establish such a framework, we will rely on interfaces and abstractions. In essence, we will code the common shared elements of this game type while leaving open the ability to add new types in the future. Examples of such shared features are the grid itself, score calculator, and level progression.

##Overview

Our design is made up of three components: the player, the engine, and the view. This design essentially mirrors the MVC design we have previously discussed. The player acts as the main controller and deals with creating the main menu and then creating the correct view and engine based on the Data class and what game was selected. The data class is essentially a parser that reads an XML file and creates the engine and view by assembling components specified in the XML. 

These engines and views act as a generic agent that holds specific versions of Interfaces that carry out various functions. Each engine is compiled with the same interfaces, but the specific implementation of the interface is specified by XML. In this way, we can create many different games that all run using the same framework but swapping subclasses. 



Player:

The player will handle the initial startup of the menu screen and act as a sort of controller for the entire program. This will allow selection of file to start a specific game type. This file will be passed to a EngineFactory Class which will have a parser object and use reflection to create specific components of the engine class one piece at a time based on the types specified in XML file. After the factory has all necessary engine components created based on parser, it will create an Engine object and a View object which are both stored in a game object in a list of games in the Player. The players main job then after parsing is to call update on Engine in the active game whenever an action occurs, which will update its Grid object based on its stored framework, and return an immutable version. This version is then passed to the view to update it as well.

Engine:

The engine is the backend of each game. It is flexibly created based of the xml file. When initialized, it requires a game handler, Animation handler and level handler. These are interfaces with many subclasses allowing for radically different games using the same framework. 

The engine has a few public methods to allow the player/controller to interact with it. There is a create initial level which returns an immutable grid object that contains the current values. The other public method is execute event. This event is either a key press (button presses are essentially mapped to key codes) or cell click. The engine uses the saved game handler to execute the event. Next it creates the correct animation using the animation handler. Finally it checks with the level handler in case of moving to a new level or lost game. This information is returned to the player. 

Data:
	
Given an XML file, this is responsible for making an engine and a view specific to the game that is described in the XML file. This has three main components, including a EngineFactory, ViewFactory, and XMLParser. The xml parser will be used to get specifications for a specific component, which based on whether it relates to view or engine, will use the engine or view factory to create the component with its specifications and then use them to create the Engine. These resulting Engines and Views

View:
	The view is constructed quite similarly to the engine. It is piecemeal compiled based on the XML files. Its necessary interfaces are UI displays …. TODO. It has a public method called update grid that the player calls. It takes in a grid and animations, and updates the display. It also has the ability to communicate with the player using passes in lambdas. The lambdas are used for cell click events and allow the player to also update the engine. 

##Design Details

Engine Internal: 
The Engine has 3 main children classes. These are the gameHandler, AnimationHandler, and LevelHandler. 

The level handler contains three methods, create level, next level and check game over. 

The animation handler just returns animations. These are created by passing a before and after state. 

Finally the gameHandler. This has a method called run event. If the event has a specified result in the game, then it runs the associated interactions. The game handler has three dependencies, an interaction interface, rules interface and event interface. The event interface says what to do for each event. This uses the rules and interactions to complete the update. The various interfaces allow for the custom games. 

CRUCIAL: The event is what you do to the cell/s, the interaction is which cell/s you do it to, and the rules are the outcome of the interaction. This is truly where the various games are made. We will code some generic implementations that seem to be common, while also providing some more vague subclasses that can use lambda's supplied by the XML to make the custom implementations. 

View Internal: 
The internal API of the view is much simpler. After it is assembled by the parser/factory, its only job is to deal with button or cell events. The button events are handled by passed lambdas either provided by the player or the XML file itself. 

The cell events are handled by using the passed grid object in combination with our custom event class. The grid class provides an identifier for the given cell which then gets wrapped with the event class and passed to the engine. 
 
##Example Games
 * 2048
 
    * This game is a grid game based on the popular application 2048, in which the goal was using a small grid to combine blocks with the same numbers (starting at blocks of 1 2 and 4) and eventually create a block with a value of 2048 (or higher!)
    * Like all other grid games, this game is composed of a grid of cells.  The cells in this game will be required to either be empty or have numbers, which when selecting from types of cell objects to be added to the grid can use number cells. This game, unlike the others, will require that using arrow keys all cells will move in a certain direction. Based on the specified rules, that similar cells combine, the cells will combine as needed upon moving. As described in design details, our engine has an interface called GameHandler which stores a set of rules and interactions. The event in this case would be an arrow key press. This would be applied to ells based on interactions, which in this case would specify that this event is applied to all cells. The rules would then specify that for each cell depending on its neighbors, all of the same type would be combined in a correct order.
 * Minesweeper
    * This game is a grid game based on the classic Minesweeper game, in which the goal is to mark cells where bombs are located without triggering any of them. If you guess wrong and uncover a bomb the game is over. Cells that are uncovered that aren’t bombs will typically display numbers.
    * Like all other grid games, this game is composed of a grid of cells. The cells in this game will be required to either be empty or have numbers, which when selecting from types of cell objects to be added to the grid can use number cells. In addition, some cells may be required to be bombs, which can either be a new cell type or simply have a number specific to bombs. Either way, grids can store multiple types of cells. This game, unlike the others, uses right or left clicking actions to either mark a cell or uncover a cell. As described in design details our engine has an interface called GameHandler which stores a set of rules and interactions. The event in this case is a left or right click on a cell. Interactions in this case would specify that the event is executed on just the single cell. The rules would specify what the result of that click is based on the cells given state and the type of click.
 * Snake
    * This game is a grid game based on the classic Snake game, in which the goal is to grow the snake that moves as long as possible. If the snake crosses a food source, it grows by one, if it hits itself you lose, and otherwise can simply move around the screen. 
    * Like all other grid games, this game is composed of a grid of cells. The cells in this game will be required to be either Picture cells for food or the Snake, as well as blank cells. The grid will be able to store a variety of cells. This game, similarly to 2048 will have arrow keys as events. In addition, there will be an arbitrary step event. Unlike 2048, the arrow interaction will specify only that the event should be applied to the head of the snake, and will change the orientation of the snake’s head. The step would apply to an interaction that specifies the updating of all cells, which would start at the head and allow them to move forward using their neighbors. The rules would specify how the snake should act based on the type of cell the snake’s head moves into.
##Design Considerations
Our biggest consideration was concerning the ability to abstract rules, interactions and events. As discussed, this is really where the games are made. We really want to avoid making specific implementations as this reduces our code flexibility. Instead, we want to find a way to make complex specific rules possible to implement strictly from an XML file. We felt using the split we currently have will give us that flexibility. By combining different arrangements of game handlers, many different games can be created. In the cases of rules, events, or interactions that don't exist in our code base, we need a new way to add designs. 
We have not figured out the specifics of this yet. I am hoping that perhaps we can use passed in lambdas or maybe even go further piecemeal. Our one hesitation in going to far down the interface tree is the raw amount of code that might only be used for one game.  Yes its “generic”, but it would only ever be used once. 
