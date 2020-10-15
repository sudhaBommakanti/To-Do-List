
import java.text.ParseException;
import java.util.*;
import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * Main class to implement all the valid command prompt options for the application
 * along with menu options for the user.
 *
 * @author  Sudha Bommakanti
 * @version 2020.10.11
 */
public class Main {

    private FileHandler fileHandler;
    private String path = "/Users/sudhabommakanti/IdeaProjects/To-Do-List/src/main/java/";
    SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
    //Public ArrayList<Task> getData = new ArrayList<>();
    private ArrayList<Task> taskList = new ArrayList<>();
    public Task task;

    /**
     * Main class constructor which initialises the file handler and calls the filehandler read method
     * to get the data to the user implement all the valid command prompt options for the application.
     */
    public Main() {
        fileHandler = new FileHandler();
        taskList = fileHandler.readAsData();
    }

    /**
     * text user interface - toDoList method which is used to print the welcome message to the user.
     */
    public void toDoLists() throws ParseException{
        welcomeMessage();
    }

    /**
     * Private method welcomeMessage()
     * This method calls the user interface options for the - toDoList.
     */
    private void welcomeMessage() throws ParseException{
        System.out.println(">> Welcome to ToDoLy");
        System.out.println(">> You have 0 tasks todo and 0 tasks are done!");
        System.out.println(">> Pick an option:");
        showCommands();
    }

    /**
     * Private method showCommands()
     * With this method the user can enter his choice of operation,redirected to the corresponding
     * option method and can come back to main menu.
     */
    private void showCommands() throws ParseException {
        int menuItem = -1;
        while( menuItem != 0){
            menuItem = menu();
            switch (menuItem){
                case 1:
                    showList();
                    break;
                case 2:
                    System.out.println("Add a new Task:");
                    addTask();
                    break;
                case 3:
                    editTask();
                    System.out.println("Edit a new task");
                    break;
                case 4:
                    saveAndQuit();
                    break;
                default:
                    System.out.println("Not a valid input");
            }
        }
    }

    /**
     * public method menu()
     * This method gives the user options to show Task list, add a new task, show task,
     * edit a task,save the task and continue to the main menu.
     * returns user choice as int.
     */
    public int menu(){
        int choice;
        Scanner sc = new Scanner(System.in);
        System.out.println(">> (1) Show Task List (by date or project) ");
        System.out.println(">> (2) Add New Task ");
        System.out.println(">> (3) Edit Task (update, mark as done, remove) ");
        System.out.println(">> (4) Save and Quit");
        System.out.print("> ");
        choice = sc.nextInt();
        return choice;
    }

    /**
     * public method showList()
     * This method show the list of tasks that the user has saved to the task list file.
     */
    public void showList() {
        System.out.println("\nTask List\n");
        //ArrayList<Task> checkData = fileHandler.readAsData();
       // System.out.println(checkData);
        int number = 0;
        for (Task item : taskList) {
            System.out.println(++number + ". " + item.getItem());
        }
    }

    /**
     * public method addTask()
     * This method allows the user to add a new task with the use of Task model class
     * with a task title, due date, status and project.
     * Then stores the Task to the file.
     * Can continue with the left task.
     */
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
                    fileHandler.writeAsData(taskList);
                }
                System.out.println("Do you want to continue to add another task:");
                count++;
            } else {
                i--; // Do not increase index for empty item.
            }
        }
    }

    public int editMenu(){
        int choice;
        Scanner sc = new Scanner(System.in);
        System.out.println("(1) Update the Task ");
        System.out.println("(2) Mark as Done");
        System.out.println("(3) Remove the Task");
        System.out.print("> ");
        choice = sc.nextInt();
        return choice;
    }

    public void editTask(){
        int menuItem = -1;
        while( menuItem != 0){
            menuItem = editMenu();
            switch (menuItem){
                case 1:
                    updateTask(task);
                    break;
                case 2:
                    markAsDone();
                    break;
                case 3:
                    remove();
                    System.out.println("Edit a new task");
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        }

    }

    //method to be implemented.
    public void updateTask(Task task) {
        System.out.println("update the task");
        //ArrayList<Task> checkData = fileHandler.readAsData();
        System.out.println(taskList);
    }

    public void markAsDone() {
        System.out.println("mark as done"); // method to be implemented
    }

    /**
     * sub task of edit task method
     * removes the task from the list of tasks
     * gets the index number of the task and removes the task with that index number
     * updates the file after a task is removed.
     */
    public void remove() {
        System.out.println("remove a task");
        System.out.println("----------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.print("What do you want to remove? (Enter number): ");
        int index = scanner.nextInt();
        if((index-1)<0 || index>taskList.size()) {
            System.out.println("Wrong index number! Please enter in range of 1 to "+taskList.size());
        } else {
            taskList.remove(index-1);
            fileHandler.writeAsData(taskList);
        }
        System.out.println("----------------------");
        System.out.println("Task Removed!");
    }

    /**
     * method to save the task to file and quit from the file to come back
     * to main menu options.
     */
    public void saveAndQuit() {
        System.out.println("save the task and continue"); // under implementation.
    }

    /**
     * main method to start the user command line interface
     * creates Main class object and calls toDoLists method.
     */
    public static void main(String[] args) throws ParseException {
        Main main = new Main();
        main.toDoLists();
    }
}
