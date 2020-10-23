
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoListManagerTest
{
    private static ToDoListManager toDo;
    private static FileHandler fileHandler;
    /**
     * init method before writing test Methods
     * create a task and add it to the list
     * create a file handler and write the task object to it in the new text file.
     */
   @BeforeAll
    public static void init() throws ParseException
    {
        SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
        Task a1 =  new Task("title1",format.parse("20/03/2019"), "done", "bank");
        List<Task> list = new ArrayList<>();
        list.add(a1);
        fileHandler = new FileHandler();
        fileHandler.writeAsObject(list, "TaskCheck.txt");
        toDo = new ToDoListManager("TaskCheck.txt");

    }

    // method to check the count of todo tasks
    @Test
    public void checkCountToDoTaskTest() {
        int expected = 1;
        assertEquals(expected, toDo.checkToDoTask());
    }

    // method to check the count of done tasks
    @Test
    public void checkCountDoneTaskTest() {
        int expected = 1;
        assertEquals(expected, toDo.checkDoneTasks());
    }
}