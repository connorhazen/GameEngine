##names of all people who worked on the project
Connor Hazen
Charles Papandreou
###date you started, date you finished, and an estimate of the number of hours worked on the project

~April 1 - April 25, spending a cumulative ~100 hours

* any books, papers, online, or human resources that you used in developing the project

We referred to all previous class resources and labs, as well as stack overflow for small bugs and various public Java API’s for method parameter requirements

* files used to start the project (the class(es) containing main)

Main is located in the base Game Package, it is the only class and is strictly responsible for making the player. One must just run main. 

* files used to test the project and errors you expect your program to handle without crashing

The main issues occur when parsing a file. These errors are all thrown and displayed on the main page resulting in no crashes.
 
* any data or resource files required by the project (including format of non-standard files)

There is a language properties file that is necessary for all text.

Each game must possess a  folder in the Games directory in Data. This folder needs both an Engine and View xml file used to create the game object. Finally, the image files are needed in state images. 
any decisions, assumptions, or simplifications you made to handle vague, ambiguous, or conflicting requirements
 
* any known bugs, crashes, or problems with the project's functionality

We are unaware of any at this time. Given that we are a two person team and were largely focused on adding features and refactoring our design, we did not include as much testing as we hoped. We included various JUnit tests but were unable to include TestFX tests.

* any extra features included in the project

We allowed for loading and saving previous games, and multiple games at once. Added buttons to pause play reset games as well.

* your impressions of the assignment to help improve it in the future

This assignment was our most challenging yet in terms of design. It would have been a very different experience had it been as a part of a ten person team, but we are happy with the way it worked out given the limitations of working remotely. Planning the hierarchies and architecture for this project was key to working successfully and keeping the project moving along. Although there is a lot of work that was required during the planning section, it certainly made implementing this project easier.

As we created our data files, they came to represent a pseudo language of sorts. It was like we were creating instructions for assembly. This was a weird aha moment but is true of API’s in general. Your goal is to create an diverse instruction set that provides enough specificity to create a wide variety of games, but still simple enough to make the coding process easier for the user. 


