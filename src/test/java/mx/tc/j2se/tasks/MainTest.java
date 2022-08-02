package mx.tc.j2se.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {
	@Test
	public void mainTest() {

		Task task = new TaskImpl("Trotar por las mañanas",
				LocalDateTime.of(2017, 2, 13, 9,0),
				LocalDateTime.of(2017, 3, 4, 16,0), 24);
		task.setActive(true);

		Task task2 = new TaskImpl("Ir al cine",
				LocalDateTime.of(2017, 2, 23, 19,0));
		task2.setActive(true);

		Task task3 = new TaskImpl("Dar paseo al perro",
				LocalDateTime.of(2017, 2, 15, 9,0),
				LocalDateTime.of(2017, 2, 25, 19,0), 12);
		task3.setActive(true);

		Task task4 = new TaskImpl("Comer",
				LocalDateTime.of(2017, 2, 24, 16,0));
		task4.setActive(true);

		Task task5 = new TaskImpl("Ver juego",
				LocalDateTime.of(2017, 2, 25, 8,0));
		task5.setActive(true);

		AbstractTaskList taskList = TaskListFactory.createTaskList(ListTypes.types.ARRAY);
		taskList.add(task);
		taskList.add(task2);
		taskList.add(task3);
		taskList.add(task4);
		taskList.add(task5);

		AbstractTaskList taskList1 = TaskListFactory.createTaskList(ListTypes.types.ARRAY);
		taskList1.add(task);
		taskList1.add(task2);
		taskList1.add(task3);
		taskList1.add(task4);
		taskList1.add(task5);

		assertEquals(taskList.hashCode(), taskList1.hashCode());
	}
}
