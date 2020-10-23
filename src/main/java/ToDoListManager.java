import jdk.swing.interop.SwingInterOpUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Comparator;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * ToDoListManager class to write the task requirements like add,show,edit and save tasks to the file.
 * @author Sudha Bommakanti
 * @version 2020.10.15
 */
public class ToDoListManager {
    private static final int VIEW_TASKS = 1;
    private static final int ADD_TASK = 2;
    private static final int EDIT_TASK = 3;
    private static final int EXIT = 4;

    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    private FileHandler fileHandler;
    public List<Task> tasks;
    private Scanner input;

    /**
     * Initializes the class object with the scanner and file handler class.
     * @ param filename
     *
     */
    public ToDoListManager(String filename) {
        fileHandler = new FileHandler();
        tasks = fileHandler.readAsObject(filename);
        input = new Scanner(System.in);
    }

    /**
     * main method to run the application
     */
    public static void main(String[] args) throws ParseException {
        ToDoListManager toDoListManager = new ToDoListManager("Task.txt");
        toDoListManager.runToDoList();
    }

    /**
     * method to run the ToDoList options
     */
    public void runToDoList() {
        int option = -1;
        while (option != EXIT) {
            try {
                showMenu();
                option = input.nextInt();
                input.nextLine();
                switch (option) {
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
                        System.out.println("Program will save the tasks to file and exit");
                        saveAndQuit();
                        break;
                    default:
                        System.out.println("Unrecognized command");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Please enter only numbers");
                input.nextLine();
            }
        }
    }

    // Menu to show the details of the application usage
    private void showMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println(">> Welcome to ToDoLy");
        System.out.println(">> You have " + checkToDoTask() + " tasks todo and " + checkDoneTasks() + " tasks are done!");
        System.out.println(">> Pick an option:");
        System.out.println(String.format("1) Show Task List (by date or project) ", VIEW_TASKS));
        System.out.println(String.format("2) Add New Task ", ADD_TASK));
        System.out.println(String.format("3) Edit Task (update, mark as done, remove) ", EDIT_TASK));
        System.out.println(String.format("4) Save and Quit", EXIT));
        System.out.print("> ");
    }

    /**
     * method to check the to do list tasks from the list
     * returns integer - no of tasks to do.
     */
    public int checkToDoTask() {
        int toDoTasks = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getStatus().toLowerCase().equals("todo")) {
                toDoTasks++;
            }
        }
        return toDoTasks;
    }

    /**
     * method to check done tasks from the list
     * returns integer - no of tasks that are done.
     */
    public int checkDoneTasks() {
        int doneTasks = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getStatus().toLowerCase().equals("done")) {
                doneTasks++;
            }
        }
        return doneTasks;
    }

    /**
     * method to add tasks to the list
     * after adding the task to the list, the list will be written to the file
     */
    private void addTask() {
        try {
            System.out.println("Please enter task title\n (Title cannot be empty, max 20 characters) ");
            String title = input.nextLine();
            System.out.println("Please enter task due data title\n (date should be in the format \"dd/MM/yyyy\")) ");
            String date = input.nextLine();
            System.out.println("Please enter task status (done,todo) ");
            String status = input.nextLine();
            System.out.println("Please enter the task category \n (Home, work etc;) ");
            String category = input.nextLine();
            System.out.println("A new task has been added to your list.");
            tasks.add(new Task(title, format.parse(date), status, category));
            fileHandler.writeAsObject(tasks, "Task.txt");
        } catch (ParseException e) {
            System.out.println("File not found " + e);
        }
    }

    /**
     * method to edit the tasks.
     * consists of sub menu to have options update, mark as done and remove the task.
     */
    private void editTask() {
        int option = -1;
        System.out.println("Enter '1' to update the task and '2' to mark the task status as done and 3 to remove the task from the list and 4 to return to main menu");
        try {
            option = input.nextInt();
            input.nextLine();
            switch (option) {
                case 1:
                    updateTask();
                    break;
                case 2:
                    toggleTask();
                    break;
                case 3:
                    removeTask();
                case 4:
                    return;
                default:
                    System.out.println("Unrecognized command");
            }
        } catch (InputMismatchException ex) {
            System.out.println("Please enter only numbers");
            input.nextLine();
        }
    }

    /**
     * method to update the task
     * outputs no tasks to update if the list is empty, otherwise updates the task title with the new title.
     */
    private void updateTask() {
        try {
            if (tasks.size() <= 0) {
                System.out.println("Nothing to update, tasks list is empty");
            } else {
                System.out.println("Enter index of task to update");
                int index = input.nextInt();
                input.nextLine();
                System.out.println("Enter the new task title to update");
                String changeTitle = input.nextLine();
                tasks.get(index).setTitle(changeTitle);
            }
        } catch (InputMismatchException ex) {
            System.out.println("Please enter only numbers");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Invalid index number");
        }

    }

    /**
     * method to toggle the task or mark as done
     * toggles the task as done with the index number by the user.
     */
    private void toggleTask() {
        System.out.println("Enter the index of the task to mark as done");
        int index = input.nextInt();
        Task taskAtIndex = tasks.get(index);
        String taskAtIndexStatus = taskAtIndex.getStatus();
        if (taskAtIndexStatus.toLowerCase().equals("todo"))
            taskAtIndex.setStatus("Done");
    }

    /**
     * method to remove the task from the tasks list.
     * removes the task with the index number input by the user.
     */
    private void removeTask() {
        try {
            if (tasks.size() <= 0) {
                System.out.println("Nothing to remove, tasks list is empty");
            } else {
                System.out.println("Enter index of task to remove");
                int index = input.nextInt();
                input.nextLine();
                tasks.remove(index);
            }
        } catch (InputMismatchException ex) {
            System.out.println("Please enter only numbers");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Invalid index number");
        }
    }

    /**
     * method to show the tasks lists
     * user can choose the tasks to show as sort by date or sort by project category.
     */
    private void showTasks() {
        int option = -1;
        System.out.println("Enter '1' to show task lists by date and '2' to show tasks list by project and 3 return to the previous menu");
        try {
            option = input.nextInt();
            input.nextLine();
            switch (option) {
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
        } catch (InputMismatchException ex) {
            System.out.println("Please enter only numbers");
            input.nextLine();
        }
        System.out.println("---------------------------------------");
        if (tasks.size() > 0) {
            for (int index = 0; index < tasks.size(); index++) {
                System.out.println(
                        String.format("Index: %d : %s",
                                index, tasks.get(index)));
            }
        } else {
            System.out.println("No tasks to display");
        }
        System.out.println("---------------------------------------");
    }

    /**
     * method to sort the tasks by date
     * @ param Comparator class of type Task
     */
    public void sortTasksByDate(Comparator<Task> order) {
        System.out.println("Sort tasks by date");
        tasks.sort(order);
    }

    /**
     * method to sort the tasks by project category
     * @ param Comparator class of type Task
     */
    public void sortTasksByProject(Comparator<Task> order) {
        System.out.println("Sort tasks by project");
        tasks.sort(order);
    }

    /**
     * method to save the tasks list to file.
     * it also exits from the application.
     */
    public void saveAndQuit() {
        fileHandler.writeAsObject(tasks, "Task.txt");
        System.exit(0);
    }
}
