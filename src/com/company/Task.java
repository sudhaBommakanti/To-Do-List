package com.company;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
    private String title;
    private Date theDate;
    private String status;
    private String projectCategory;
    SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");

    public Task(String title, Date dueDate, String status, String projectCategory) {
        this.title = title;
        theDate = dueDate;
        this.status = status;
        this.projectCategory = projectCategory;
    }

    public String getTitle() {
        return this.title;
    }

    public Date getDate() {
        return theDate;
    }

    public String getStatus() {
        return this.status;
    }

    public String getProjectCategory() {
        return this.projectCategory;
    }



    public String getItem() {
        return this.title + ", " + format.format(theDate) + ", " + this.status + ", " + this.projectCategory;
    }

}
