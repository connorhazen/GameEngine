## Description

Thi bug occurs when the view file is not set up correctly. Instead of throwing an
XMl exception, we throw a null pointer which is then not thrown farther up resulting in 
a weird state of nothing occurring. 

## Expected Behavior

We expect missing values in the view xml file to throw an exception that cna then be
displayed in the main menu. 

## Current Behavior

Currently there is no displayed message. 

## Steps to Reproduce

You could produce this error by removing the associated properties tag for a 
given view file. This does not throw XML exception so was un caught. 

## Failure Logs


org.opentest4j.AssertionFailedError: Unexpected exception type thrown ==> expected: <game.parse.XMLException> but was: <java.lang.NullPointerException>

## Hypothesis for Fixing the Bug

the testViewFactoryWrongFile() should tell us if the bug is fixed. The solution is to catch both exceptions 
in either the super factory of view factory. 