package com.company;

public enum CommandWord
{

    // A value for each command word along with its
    // corresponding user interface string.

   SHOW("Show Task List (by date or project)"), ADD("Add New Task"),EDIT("Edit Task (update, mark as done, remove)"),SAVE("Save and Quit"),UNKNOWN("?") ;

    // The command string.
    private String commandString;

    CommandWord(String commandString) {
        this.commandString = commandString;
    }

    public String toString() {
        return commandString;
    }
}
