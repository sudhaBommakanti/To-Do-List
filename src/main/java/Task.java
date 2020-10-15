import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;

public class Task implements Serializable
{
    private String title;
    private Date dueDate;
    private String status;
    private String project;

    SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");

    public Task(String title, Date dueDate, String status, String project) {
        this.title = title;
        this.dueDate = dueDate;
        this.status = status;
        this.project = project;
    }

    public String getTitle() {
        return this.title;
    }

    public Date getDate() {
        return this.dueDate;
    }

    public String getStatus() {
        return this.status;
    }

    public String getProjectCategory() {
        return this.project;
    }

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
