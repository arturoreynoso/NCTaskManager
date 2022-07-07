package mx.tc.j2se.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LinkedTaskListTest {
    @Test
    void testArrayTaskListSize() {
        AbstractTaskList taskList = new LinkedTaskListImpl();
        Task task = new TaskImpl("Correr por las mañanas a las 9:00 a.m.", 9, 180, 24);
        task.setActive(true);

        taskList.add(task);
        assertEquals(1, taskList.size());
        assertEquals("Correr por las mañanas a las 9:00 a.m.", taskList.getTask(0).getTitle());
        assertEquals(taskList.incoming(176,178).size(),1);
    }
}
