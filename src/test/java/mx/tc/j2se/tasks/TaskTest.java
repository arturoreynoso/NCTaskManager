package mx.tc.j2se.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    @Test
    void testSettersAndGetters() {
        Task task = new TaskImpl();

        task.setTitle("Tomar café a las 9:00 a.m.");
        assertEquals("Tomar café a las 9:00 a.m.",task.getTitle());

        task.setActive(true);
        assertTrue(task.isActive());
        task.setActive(false);
        assertFalse(task.isActive());


        Task task2 = new TaskImpl("Drink a coffee at 9:00 a.m." ,0, 20, 3);
        task2.setTime(9);
        assertEquals(9, task2.getTime());
        assertFalse(task2.isRepeated());
    }

    @Test
    void testNextTimeAfter() {
        Task task = new TaskImpl("Go out to the movies today at 20:00 hrs", 20);
        assertEquals(-1, task.nextTimeAfter(0));
        task.setActive(true);
        assertEquals(task.nextTimeAfter(0),20);
        assertEquals(task.nextTimeAfter(20),-1);

        Task task2 = new TaskImpl("Jogging at 9:00 a.m. every day.", 9, 300, 24);
        assertEquals(-1, task2.nextTimeAfter(0));
        task2.setActive(true);
        assertEquals(9, task2.nextTimeAfter(0));
        assertEquals(33, task2.nextTimeAfter(9));
    }
}
