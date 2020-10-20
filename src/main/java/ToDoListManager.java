import jdk.swing.interop.SwingInterOpUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Comparator;
import java.util.Scanner;
import java.util.InputMismatchException;

public class ToDoListManager {
    private static final int VIEW_TASKS = 1;
    private static final int ADD_TASK = 2;
    private static final int EDIT_TASK = 3;
    private static final int EXIT = 4;
    private static final int UPDATE_TASK = 5;
    private static final int TOGGLE_TASK_STATUS = 6;
    private static final int REMOVE_TASK = 7;

    SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");

    private FileHandler fileHandler;
    public List<Task> tasks;
    private Scanner input;

    public ToDoListManager() {
        fileHandler = new FileHandler();
        tasks = fileHandler.readAsObject();
        input = new Scanner(System.in);
    }

    public static void main(String[] args) throws ParseException{
        ToDoListManager toDoListManager = new ToDoListManager();
        toDoListManager.runToDoList();
    }

    public void runToDoList(){
        int option = -1;
        while(option != EXIT){
            try{
                showMenu();
                option = input.nextInt();
                input.nextLine();
                switch(option){
                    case VIEW_TASKS:
                        showTasks();
                        break;
                    case ADD_TASK:
                        addTask();
                        break;
                    case EDIT_TASK:
                        editTask();
                        break;
                    case EXIT:
                        System.out.println("Program will exit");
                        break;
                    default:
                        System.out.println("Unrecognized command");
                }
            }
            catch(InputMismatchException ex){
                System.out.println("Please enter only numbers");
                input.nextLine();
            }
        }
    }

    private void showMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println(">> Welcome to ToDoLy");
        System.out.println(">> You have " + checkToDoTask() + " tasks todo and " + checkDoneTasks() + " tasks are done!");
        System.out.println(">> Pick an option:");
        System.out.println(String.format("1) Show Task List (by date or project) ", VIEW_TASKS));
        System.out.println(String.format("2) Add New Task ", ADD_TASK));
        System.out.println(String.format("3) Edit Task (update, mark as done, remove) ",EDIT_TASK));
        System.out.println(String.format("4) Save and Quit",EXIT));
        System.out.print("> ");
    }

    public int checkToDoTask() {
        int toDoTasks = 0;
        for( int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).getStatus().toLowerCase().equals("todo")) {
                toDoTasks++;
            }
        }
        return toDoTasks;
    }

    public int checkDoneTasks() {
        int doneTasks = 0;
        for( int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).getStatus().toLowerCase().equals("done")) {
                doneTasks++;
            }
        }
        return doneTasks;
    }

    private void addTask(){
        try{
            System.out.println("Please enter task title\n (Title cannot be empty, max 20 characters) ");
            String title = input.nextLine();
            System.out.println("Please enter task due data title\n (date should be in the format \"dd/MM/yyyy\")) ");
            String date = input.nextLine();
            System.out.println("Please enter task status (pending, done,todo) ");
            String status = input.nextLine();
            System.out.println("Please enter the task category \n (Home, work etc;) ");
            String category = input.nextLine();
            System.out.println("A new task has been added to your list.");
            tasks.add(new Task(title,format.parse(date),status,category));
            fileHandler.writeAsObject(tasks);
        }
        catch(ParseException e){
            System.out.println("File not found " + e);
        }
    }

    private void editTask() {
        int option = -1;
            try{
                showEditMenu();
                option = input.nextInt();
                input.nextLine();
                switch(option){
                    case UPDATE_TASK:
                        updateTask();
                        break;
                    case TOGGLE_TASK_STATUS:
                        toggleTask();
                        break;
                    case REMOVE_TASK:
                        removeTask();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Unrecognized command");
                }
            }
            catch(InputMismatchException ex){
                System.out.println("Please enter only numbers");
                input.nextLine();
            }
    }

    private void showEditMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println(String.format("5) Update the task(Enter the index of the task to edit) ", UPDATE_TASK));
        System.out.println(String.format("6) Mark as done", TOGGLE_TASK_STATUS));
        System.out.println(String.format("7) Remove the task (Enter the index of the task you want to remove) ",REMOVE_TASK));
        System.out.print("> ");
    }

    private void updateTask() {
        try{
            if(tasks.size() <= 0){
                System.out.println("Nothing to update, tasks list is empty");
            }
            else {
                System.out.println("Enter index of task to update");
                int index = input.nextInt();
                input.nextLine();
                System.out.println("Enter the task title to update");
                String changeTitle = input.nextLine();

                if (tasks.get(index).getTitle() == changeTitle) {
                    System.out.println("Enter the task title to replace:");
                   /* int taskTitle = input.nextLine();
                    tasks.set(index,new Task(setTitle(taskTitle)));*/
                }
            }
        }
        catch(InputMismatchException ex){
            System.out.println("Please enter only numbers");
        }
        catch(IndexOutOfBoundsException ex){
            System.out.println("Invalid index number");
        }

    }

    private void toggleTask() {
        int index = input.nextInt();
        Task taskAtIndex = tasks.get(index);
        String taskAtIndexStatus = taskAtIndex.getStatus();
        if(taskAtIndexStatus.toLowerCase().equals("todo"))
        taskAtIndex.setStatus("Done");
        tasks.add(index, taskAtIndex);
    }

    private void removeTask(){
        try{
            if(tasks.size() <= 0){
                System.out.println("Nothing to remove, tasks list is empty");
            }
            else{
                System.out.println("Enter index of task to remove");
                int index = input.nextInt();
                input.nextLine();
                tasks.remove(index);
            }
        }
        catch(InputMismatchException ex){
            System.out.println("Please enter only numbers");
        }
        catch(IndexOutOfBoundsException ex){
            System.out.println("Invalid index number");
        }
    }

    private void showTasks(){
        int option = -1;
        System.out.println("Enter '1' to show task lists by date and '2' to show tasks list by project and 3 return to the prev menu");
            try{
                option = input.nextInt(); //todo : 1. have a checker method(while and try catch)
                input.nextLine();
                switch(option){
                    case 1:
                        sortTasksByDate(Comparator.comparing(Task::getDate));
                        break;
                    case 2:
                        sortTasksByProject(Comparator.comparing(Task::getProjectCategory));
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Unrecognized command");
                }
            }
            catch(InputMismatchException ex){
                System.out.println("Please enter only numbers");
                input.nextLine();
            }
        if(tasks.size() > 0){
            for(int index = 0; index < tasks.size(); index++){
                System.out.println(
                        String.format("Index: %d : %s",
                                index, tasks.get(index)));
            }
        }
        else{
            System.out.println("No tasks to display");
        }
    }

    public void sortTasksByDate(Comparator<Task> order) {
        System.out.println("Sort tasks by date");
        tasks.sort(order);
    }

    public void sortTasksByProject(Comparator<Task> order) {
        System.out.println("Sort tasks by project");
        tasks.sort(order);
    }
}
