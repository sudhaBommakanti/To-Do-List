package com.company;
import java.text.ParseException;
import java.util.*;
import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {

    private Parser parser;
    SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
    private static ArrayList<String> toDoList = new ArrayList<String>();
    public Task task;
    private List list;

    public Main() {
        parser = new Parser();
    }

    public void toDoLists() throws ParseException{
        welcomeMessage();
        boolean finished = false;
        while(! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("There is no todo list.Good bye.");
    }

    private boolean processCommand(Command command) throws ParseException{
        boolean wantToQuit = false;
        CommandWord commandWord = command.getCommandWord();
        System.out.println("Given Command Word in Main " + commandWord);

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("No command word with this name");
                break;
            case Add:
                System.out.println("Add a new Task:");
                addTask();
                break;
            case SHOW:
                showList();
                break;
            case EDIT:
                editTask();
                System.out.println("Edit a new task");
                break;
            case SAVE:
                System.out.println("Save the task and continue");
                saveAndQuit(command);
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

    public void editTask() {
        System.out.println("edit the tasks");
    }

    public void addTask() throws ParseException {
            System.out.print("Enter the task title:");
            Scanner scanner = new Scanner(System.in);
            String title = scanner.nextLine();
            System.out.println("the name of the title : " + title);
            System.out.print("Enter the task date (dd/mm/yyyy):");
            Scanner scanner2 = new Scanner(System.in);
            Date date=format.parse(scanner2.next());
            task = new Task(title,date);
            String theItem = task.getItem();
            toDoList.add(theItem);
    }

    public void saveAndQuit(Command command) {
        System.exit(0);
    }

    public void showList() {
        System.out.println();
        System.out.println("----------------------");
        System.out.println("To-Do List");
        System.out.println("----------------------");
        int number = 0;
        for (String item : toDoList) {
            System.out.println(++number + ". " + item);
        }
        System.out.println("----------------------");
    }

    public static void main(String[] args) throws ParseException {

    Main main = new Main();
    main.toDoLists();
	// write your code here


    }
}
