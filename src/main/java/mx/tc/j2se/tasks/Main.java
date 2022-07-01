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

		/*ArrayTaskList taskList= new ArrayTaskListImpl();
		taskList.add(task);
		taskList.add(task2);
		taskList.add(task3);
		taskList.add(task4);
		taskList.add(task5);

		taskList.remove(taskList.getTask(5));
		System.out.println(taskList.size());
		//System.out.println(taskList.getTask(1).getTitle());

		for (int i = 0; i < taskList.size(); i++) {
			System.out.println(taskList.getTask(i).getTitle());
		}*/

		LinkedTaskList taskList2 = new LinkedTaskListImpl();
		taskList2.add(task);
		taskList2.add(task2);
		taskList2.add(task3);
		taskList2.add(task4);
		taskList2.add(task5);

		//taskList2.remove(taskList2.getTask(4));
		//System.out.println(taskList2.size());
		//System.out.println(taskList2.getTask(1).getTitle());
		//System.out.println(taskList2.incoming(56,60));

		for (int i = 0; i < taskList2.incoming(56, 60).size(); i++) {
			System.out.println(taskList2.getTask(i).getTitle());
		}
	}
}

