package com.company;

public class Main {

    private Parser parser;
    private List list;

    public Main() {
        parser = new Parser();
    }

    public void toDoLists() {
        welcomeMessage();

        boolean finished = false;

        while(! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("There is no todo list.Good bye.");
    }

    private boolean processCommand(Command command) {
        boolean wantToQuit = false;
        CommandWord commandWord = command.getCommandWord();


        switch (commandWord) {

            case ADD:
                System.out.println("Add a new Task:");
                break;
            case SHOW:
                System.out.println("Show the tasks list");
                break;
            case EDIT:
                System.out.println("Edit a new task");
                break;
            case SAVE:
                System.out.println("Save the task and continue");
                break;
            case UNKNOWN:
                System.out.println("No command word with this name");
                break;
        }

        return wantToQuit;
    }

    private void welcomeMessage() {
        System.out.println(">> Welcome to ToDoLy");
        System.out.println(">> You have 0 tasks todo and 0 tasks are done!");
        System.out.println(">> Pick an option:");
        parser.showCommands();

    }

    public static void main(String[] args) {

    Main main = new Main();
    main.toDoLists();
	// write your code here
        List list = new List();
        list.list();

    }
}
