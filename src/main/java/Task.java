import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;

/**
 * Model class for Task
 * contains getters for the task fields
 * @author  Sudha Bommakanti
 * @version 2020.10.11
 */
public class Task implements Serializable
{
    private String title;
    private Date dueDate;
    private String status;
    private String project;

    SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");

    //Task constructor which initializes the task fields when the task object is created.
    public Task(String title, Date dueDate, String status, String project) {
        this.title = title;
        this.dueDate = dueDate;
        this.status = status;
        this.project = project;
    }

    //getTitle method to get the Task title
    public String getTitle() {
        return this.title;
    }

    //getDate method to get the Task due date
    public Date getDate() {
        return this.dueDate;
    }

    //getStatus method to get the Task status
    public String getStatus() {
        return this.status;
    }

    //getProjectCategory method to get the Task category(Home,Work etc;)
    public String getProjectCategory() {
        return this.project;
    }

    //getItem method to get the Task details
    public String getItem() {
        return "" + this.title + "**" + format.format(this.dueDate) + "**" + this.status + "**" + this.project;
        //return "" + this.title + "**" + format.format(this.dueDate) + "**" + this.status + "**" + this.project ;
    }

    @Override
    public String toString() {
        return "" + this.title + "**" + format.format(this.dueDate) + "**" + this.status + "**" + this.project;
        //return "" + this.title + "**" + format.format(this.dueDate) + "**" + this.status + "**" + this.project ;
    }

}
