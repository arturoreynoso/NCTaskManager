package mx.tc.j2se.tasks;
import java.util.stream.IntStream;

public class Main {
	public static void main(String[] args) throws CloneNotSupportedException {
		//System.out.println("Hello Training Center!");

		Task task = new TaskImpl("Trotar por las mañanas", 9, 180, 24);
		task.setActive(true);

		Task task2 = new TaskImpl("Ir al cine", 58);
		task2.setActive(true);
		//System.out.println(task2.nextTimeAfter(0));

		Task task3 = new TaskImpl("Dar paseo al perro", 3, 60, 3);
		task3.setActive(true);

		Task task4 = new TaskImpl("Comer", 33);
		task4.setActive(true);

		Task task5 = new TaskImpl("Ver juego", 678);
		task5.setActive(true);

		AbstractTaskList taskList = TaskListFactory.createTaskList(ListTypes.types.LINKED);
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

		AbstractTaskList taskList2 = taskList1.clone();

		Task task6 = task5.clone();
		/*System.out.println(task5);
		System.out.println(task6);

		System.out.println(taskList1.equals(taskList2));
		System.out.println(taskList1.hashCode());
		System.out.println(taskList2.hashCode());
		System.out.println(taskList1);
		System.out.println(taskList2);
		System.out.println(taskList2.getTask(3));*/

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

