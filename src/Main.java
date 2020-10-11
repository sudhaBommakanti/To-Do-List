
import java.text.ParseException;
import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {

    SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
    private static ArrayList<Task> taskList = new ArrayList<>();
    public Task task;

    public void toDoLists() throws ParseException{
        welcomeMessage();
    }

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
                    System.out.println("Save the task and continue");
                    saveAndQuit();
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        }

    }

    private void welcomeMessage() throws ParseException{
        System.out.println(">> Welcome to ToDoLy");
        System.out.println(">> You have 0 tasks todo and 0 tasks are done!");
        System.out.println(">> Pick an option:");
        showCommands();
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

    public void saveAndQuit() {
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
