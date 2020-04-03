#Design Plan
###gridGUYS

##Introduction

Our team has chosen to create an API that facilitates the creation of grid based games. Examples of
such games include: 2048, candy crush, snake, etc. 

To establish such a framework, we will rely on interfaces and abstractions. In essence, we will code
the common shared elements of this game type while leaving open the ability to add new types in the 
future. Examples of such a shared features are the grid itself, score calculator, and level progression. 

##Overview 

The elements of a game can be broken into categories: UI elements (general), UI 
elements (game specific controls), game state displays, initial level setups, and game 
logic/interactions. 

##Design Details

Engine Internal: The Engine has 3 main children classes. These are the gameHandler, AnimationHandler, and LevelHandler. 

* The level handler contains three methods, create level, next level and check game over. 

* The animation handler just returns animations. These are created by passing a before and after state. 

* Finally the gameHandler. This has a method called run event. If the event has a specified result in the game, then it runs the associated interactions. The game handler has three dependencies, an interaction interface, rules interface and event interface. The event interface says what to do for each event. This uses the rules and interactions to complete the update. The various interfaces allow for the custom games. 

CRUCIAL: The event is what you do to the cell/s, the interaction is which cell/s you do it to, and the rules are the outcome of the interaction. This is truly where the various games are made. We will code some generic implementations that seem to be common, while also providing some more vague subclasses that can use lambda's supplied by the XML to make the custom implementations. 
