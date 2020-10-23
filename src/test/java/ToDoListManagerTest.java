
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest
{
    private static ToDoListManager toDo;
    private static FileHandler fileHandler;
    //SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");

    @BeforeAll
    public static void init() throws ParseException
    {
        /*
        * 1. copy the file as it now to the resources in the test folder
        * 2. create a file handler and add some tasks manually to the filehandler
        */
        SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
        Task a1 =  new Task("title1",format.parse("20/03/2019"), "done", "bank");
        List<Task> list = new ArrayList<>();
        list.add(a1);
        fileHandler = new FileHandler();
        fileHandler.writeAsObject(list, "TaskCheck.txt");
        toDo = new ToDoListManager("TaskCheck.txt");

    }

    @Test
    public void test() {
        assertTrue(true);
    }

    @Test
    public void checkCountToDoTaskTest() {
        int expected = 3;
        assertEquals(expected, toDo.checkToDoTask());
    }

    @Test
    public void checkCountDoneTaskTest() {
        int expected = 2;
        assertEquals(expected, toDo.checkDoneTasks());
    }
}