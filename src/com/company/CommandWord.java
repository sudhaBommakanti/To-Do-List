package com.company;

public enum CommandWord
{
    // A value for each command word along with its
    // corresponding user interface string.

 SHOW("Show"),
    Add("Add"),
     EDIT("Edit"),
      SAVE("Save"),UNKNOWN("?") ;

    // The command string.
    private String commandString;

    CommandWord(String commandString) {
        this.commandString = commandString;
    }

    public String toString() {
        return commandString;
    }
}
