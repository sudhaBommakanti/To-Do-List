import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    private static Task task;
    private static FileHandler fileHandler;

    /*
     * create a file handler and add some tasks manually to the filehandler
     */
    @BeforeAll
    public static void init() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        task = new Task("title1", format.parse("20/03/2019"), "done", "bank");
        List<Task> list = new ArrayList<>();
        list.add(task);
        fileHandler = new FileHandler();
        fileHandler.writeAsObject(list, "TaskCheck.txt");
    }

    //test method to check the task title
    @Test
    public void checkTaskTitle() {
        String expected = "title1";
        assertEquals(expected, task.getTitle());
    }

    //test method to check the task status
    @Test
    public void checkTaskStatus() {
        String expected = "done";
        assertEquals(expected, task.getStatus());
    }

    //test method to check the task class details as string
    @Test
    public void checkToString() {
        String expected = "title1**20/03/2019**done**bank";
        assertEquals(expected, task.toString());
    }
}
