package mx.tc.j2se.tasks;

public class Main {
	public static void main(String[] args) {
		//System.out.println("Hello Training Center!");

		Task task = new TaskImpl("Trotar por las mañanas", 9, 180, 24);
		task.setActive(true);

		Task task2 = new TaskImpl("Ir al cine", 33);
		task2.setActive(true);

		Task task3 = new TaskImpl("Dar paseo al perro", 3, 60, 3);
		task3.setActive(true);

		Task task4 = new TaskImpl("Comer", 33);
		task4.setActive(true);

		Task task5 = new TaskImpl("Ver juego", 678);

		ArrayTaskList taskList= new ArrayTaskListImpl();
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
		}

		//taskList.remove(taskList.getTask(taskList.size()-1));
		//System.out.println(taskList.getTask(taskList.size()-1).getTitle());

		//System.out.println(taskList.incoming(32,39).size());
		//System.out.println(task.getTitle());
		//System.out.println(taskList.size());
		//System.out.println(taskList.getTask(1).getTitle());
	}
}

