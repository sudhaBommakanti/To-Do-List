import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    @Test
    public void test() {
        assertTrue(true);
    }

    @Test
    public void checkCountToDoTaskTest() {
        ToDoListManager toDo = new ToDoListManager();
        int expected = 1;
        assertEquals(expected, toDo.checkToDoTask());
    }

    @Test
    public void checkCountDoneTaskTest() {
        ToDoListManager toDo = new ToDoListManager();
        int expected = 3;
        assertEquals(expected, toDo.checkDoneTasks());
    }
}