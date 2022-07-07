package mx.tc.j2se.tasks;

public class Main {
	public static void main(String[] args) {
		//System.out.println("Hello Training Center!");

		Task task = new TaskImpl("Trotar por las mañanas", 9, 180, 24);
		task.setActive(true);

		Task task2 = new TaskImpl("Ir al cine", 60);
		task2.setActive(true);

		Task task3 = new TaskImpl("Dar paseo al perro", 3, 60, 3);
		task3.setActive(true);

		Task task4 = new TaskImpl("Comer", 33);
		task4.setActive(true);

		Task task5 = new TaskImpl("Ver juego", 678);
		task5.setActive(true);

		AbstractTaskList taskList = TaskListFactory.createTaskList(ListTypes.types.ARRAY);
		taskList.add(task);
		taskList.add(task2);
		taskList.add(task3);
		taskList.add(task4);
		taskList.add(task5);

		for (int i = 0; i < taskList.incoming(56, 60).size(); i++) {
			System.out.println(taskList.incoming(56,60).getTask(i).getTitle());
		}

		AbstractTaskList taskList2 = TaskListFactory.createTaskList(ListTypes.types.LINKED);
		taskList2.add(task);
		taskList2.add(task2);
		taskList2.add(task3);
		taskList2.add(task4);
		taskList2.add(task5);
		//taskList2.add(null);


		taskList2.remove(taskList2.getTask(4));
		System.out.println(taskList2.size());
		taskList2.remove(null);
		//System.out.println(taskList2.getTask(1).getTitle());
		//System.out.println(taskList2.incoming(56,60));

		/*for (int i = 0; i < taskList2.incoming(56, 60).size(); i++) {
			System.out.println(taskList2.incoming(56,60).getTask(i).getTitle());
		}*/
	}
}

