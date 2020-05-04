##GRIDguys
Connor Hazen - cch57
Charles Papandreou - cnp20
###Each person's role in developing the project
* Charles worked on developing the game engine API’s as well as some of their implementations, focusing in particular on 2048. In addition, Charles helped work on the initial main menu and redesigning the view.
* Connor worked on developing implementations of many of the Util classes as well as the player, factories, and implementations of both the Snake and Minesweeper games, adding functionality to the operations interactions and rules for these games that can be reused in future games.
###what are the project's design goals, specifically what kinds of new features did you want to make easy to add
* When thinking about this project, we wanted to create a framework that was quite generalizable, but also open to future additions. During planning, we tried to think out every scenario, but it is also important to plan the features we don't know about. Therefore, we wanted to create a framework easily added upon while still providing enough functionality to be data driven. There is a fine line between making functions too generalizable that they don't achieve anything, and making functions too broad and not allowing for specific game implementations. Therefore, we wanted to allow the user to create games through a combination of 10-20 generic and modifiable events. To0 few, and the rules, interactions and operations would be hyper-specific. Too many and the user might as well just code the game. 
* We also wanted to leave open “sockets” for future additions, ie. adding UI controls or animations. The locations for these future additions already exist in the code and just need to be implemented. 
###Describe the high-level design of your project, focusing on the purpose and interaction of the core classes
* The general high level flow of our project starts at the player. This player selects a game file, which is then used to create a game object. This game object is a bundle containing both engine and view objects parsed from XML. Whenever an action is detected using listeners, this action is sent to the engine. The engine utilizes a three step process to produce the updated state. First, it is passed to the game handler. The handler's responsibility is to use its specified interactions rules and operations in conjunction with the performed action to modify the grid. The rules specify whether or not an action is to be performed on cells, the interaction specifies which cells to perform on, and the operation is what should be done to those cells given a user’s action. This modified grid is then passed through a level handler and animation handler. It is the level handlers responsibility to create the initial grid upon the games start as well as to determine if a game stopping condition has been triggered such as a win or loss. The animation handler helps to determine animations between the previous and new grid states. While we did not implement the animation handler there is room to easily expand this functionality later. All of this information as well as any triggered events based on the executed event are all passed back to the game object in an update object, which either helps execute the next required event or updates the view. The View then takes the update object and displays the new version of the grid along with any status updates (lost or won).
###What assumptions or decisions were made to simplify your project's design, especially those that affected adding required features
* The main assumption for our project was the grid aspect. This may seem rather obvious and ridiculous to state, but it does summarize our whole plan. It affected how we coded rules and operations,  the display, and even the parser. By assuming the game takes place on a contained grid, we could narrow down the scope of our generalized events. 
###Describe, in detail, how to add new features to your project, especially ones you were not able to complete by the deadline
* There are a few locations where one could add new features. If the additions concerned new events , then the additions are quite simple.  If the current selection of rules, operations and interactions does not suffice, then adding new implementations is quite easy. These three classes are based off quite simple interfaces and require little code to create. Once made, one must just add their name to the XML file in question and the framework deals with the rest. 
* Events themselves concern all of a game's progression and controls, but perhaps the user wants to change the visuals. We pass a game Update Object from the engine to the view on any update. This contains the new grid, a few status booleans, and an animation. If the user wanted to change visuals, all the information needed is contained in this object. This means the user does not need to change any API’s to redesign the look. 