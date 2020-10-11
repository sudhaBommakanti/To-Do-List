import java.util.ArrayList;

public class Tasks {
    private ArrayList<Task> tasks;

    public Tasks() {
        tasks = new ArrayList<>();
        System.out.println("group of tasks");
    }

    public void showTasks() {
        {
            // display all tasks
            for(Task task : tasks) {
                task.getItem();
                System.out.println();   // empty line between tasks
            }
        }
    }
}
