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