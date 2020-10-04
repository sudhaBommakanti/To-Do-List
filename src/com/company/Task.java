package com.company;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
    private String title;
    private Date theDate;
    private String status;
    SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");

    public Task(String title, Date dueDate) {
        this.title = title;
        theDate = dueDate;
    }

    public String getTitle() {
        return this.title;
    }

    public Date getDate() {
        return theDate;
    }

    public String getItem() {
        return title + ", " + format.format(theDate) ;
    }

}
