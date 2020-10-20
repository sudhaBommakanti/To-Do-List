import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;

/**
 * Model class for Task
 * contains getters and setters for Task class fields
 *
 * @author Sudha Bommakanti
 * @version 2020.10.11
 */
public class Task implements Serializable, Comparable<Task> {
    private String title;
    private Date dueDate;
    private String status;
    private String project;

    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    //Task constructor which initializes the task fields when the task object is created.
    public Task(String title, Date dueDate, String status, String project) {
        this.title = title;
        this.dueDate = dueDate;
        this.status = status;
        this.project = project;
    }

    //getTitle method to get Task title
    public String getTitle() {
        return this.title;
    }

    //getDate method to get Task due date
    public Date getDate() {
        return this.dueDate;
    }

    //getStatus method to get Task status
    public String getStatus() {
        return this.status;
    }

    //getProjectCategory method to get Task category(Home,Work etc;)
    public String getProjectCategory() {
        return this.project;
    }

    //setTitle method to set the Task title
    public void setTitle(String title) {
        this.title = title;
    }

    //setDate method to set Task due date
    public void setDate(Date date) {
        this.dueDate = date;
    }

    //setStatus method to set Task status(todo, done)
    public void setStatus(String status) {
        this.status = status;
    }

    //setProjectCategory method to set Task category(Home,Work)
    public void setProjectCategory(String project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "" + this.title + "**" + format.format(this.dueDate) + "**" + this.status + "**" + this.project;
    }

    @Override
    public int compareTo(Task task) {
        if (getDate() == null || task.getDate() == null) {
            return 0;
        }
        return getDate().compareTo(task.getDate());
    }

}
