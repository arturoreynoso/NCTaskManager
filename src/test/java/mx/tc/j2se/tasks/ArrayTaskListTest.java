package mx.tc.j2se.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayTaskListTest {
    @Test
    void testArrayTaskListSize() {
        AbstractTaskList taskList = new ArrayTaskListImpl();
        Task task = new TaskImpl("Correr por las mañanas a las 9:00 a.m.", LocalDateTime.of(2018,2,3,12,30),
                LocalDateTime.of(2018,6,24,14,30), 24);
        task.setActive(true);

        taskList.add(task);
        assertEquals(1, taskList.size());
        assertEquals("Correr por las mañanas a las 9:00 a.m.", taskList.getTask(0).getTitle());
    }
}
