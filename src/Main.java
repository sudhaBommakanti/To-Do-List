import CommandPack.Command;
import CommandPack.CommandWord;

import java.text.ParseException;
import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {

    private Parser parser;
    SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
    private static ArrayList<Task> taskList = new ArrayList<>();
    public Task task;

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
    }

    private boolean processCommand(Command command) throws ParseException{
        boolean wantToQuit = false;
        CommandWord commandWord = command.getCommandWord();

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

        final int MAX = 10;
        int count = 0;
        Scanner input = new Scanner(System.in);
        String[] list = new String[MAX];
        String userEnteredItem;
        System.out.println("Type 'yes' to add task to your list else type 'stop'.");

        for ( int i = 0; i < MAX; i++ ) {

            userEnteredItem = input.nextLine();
            if(!userEnteredItem.isEmpty()) {
                list[i] = userEnteredItem;

                if (userEnteredItem.equals("stop"))  {
                    break;
                }

                System.out.print("Enter the task title:");
                Scanner scanner = new Scanner(System.in);
                String title = scanner.nextLine();

                System.out.print("Enter the task date (dd/mm/yyyy):");
                Scanner scanner2 = new Scanner(System.in);
                Date date = format.parse(scanner2.next());

                System.out.print("Enter the status of the task: ");
                Scanner scanner3 = new Scanner(System.in);
                String status = scanner3.nextLine();

                System.out.print("Enter the project category: ");
                Scanner scanner4 = new Scanner(System.in);
                String projectCategory = scanner3.nextLine();

                task = new Task(title, date, status, projectCategory);
                taskList.add(task);

                for (int j = 0; j < taskList.size(); j++) {
                    FileHandler fileHandler = new FileHandler();
                    fileHandler.writeAsData(taskList);
                }
                System.out.println("Do you want to continue to add another task:");
                count++;
            } else {
                i--; // Do not increase index for empty item.
            }
        }

    }

    public void saveAndQuit(Command command) {
        System.exit(0);
    }

    public void showList() {
        System.out.println();
        System.out.println("----------------------------------------------");
        System.out.println("To-Do List");
        System.out.println("----------------------------------------------");
        int number = 0;
        for (Task item : taskList) {
            System.out.println(++number + ". " + item.getItem());
        }
        System.out.println("----------------------");
    }

    public static void main(String[] args) throws ParseException {
        Main main = new Main();
        main.toDoLists();
    }
}
