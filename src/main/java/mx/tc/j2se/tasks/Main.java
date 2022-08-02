package mx.tc.j2se.tasks;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;
import java.util.stream.IntStream;

public class Main {
	public static void main(String[] args) {
		//System.out.println("Hello Training Center!");

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

		AbstractTaskList taskList = TaskListFactory.createTaskList(ListTypes.types.LINKED);
		taskList.add(task);
		taskList.add(task2);
		taskList.add(task3);
		taskList.add(task4);
		taskList.add(task5);

		/*SortedMap<LocalDateTime, Set<Task>> calendar = Tasks.calendar(taskList,
				LocalDateTime.of(2017, 2, 13, 8,0),
				LocalDateTime.of(2017, 3, 27, 9,0));
		System.out.println(calendar);*/
		System.out.println(taskList.getClass());
		System.out.println("HOLA");

		/*
		AbstractTaskList taskList1 = TaskListFactory.createTaskList(ListTypes.types.ARRAY);
		taskList1.add(task);
		taskList1.add(task2);
		taskList1.add(task3);
		taskList1.add(task4);
		taskList1.add(task5);

		AbstractTaskList taskList2 = taskList1.clone();

		Task task6 = task5.clone();
		System.out.println(task5);
		System.out.println(task6);

		System.out.println(taskList1.equals(taskList2));
		System.out.println(taskList1.hashCode());
		System.out.println(taskList2.hashCode());
		System.out.println(taskList1);
		System.out.println(taskList2);
		System.out.println(taskList2.getTask(3));

		//taskList2.incoming(56,60);
		System.out.println(taskList2.incoming(53,61));
		/*System.out.println(taskList2);
		taskList2.remove(task);
		taskList2.remove(task5);
		System.out.println(taskList2);*/

		/*for (int i = 0; i < taskList2.incoming(56, 60).size(); i++) {
			System.out.println(taskList2.incoming(56,60).getTask(i).getTitle());
		}*/

	}
}

