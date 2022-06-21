package mx.tc.j2se.tasks;

public class Main {
	public static void main(String[] args) {
		System.out.println("Hello Training Center!");
		Task task = new TaskImpl();
		System.out.println(task.getTime());

		Task task2 = new TaskImpl("Ir al cine", 18);
		System.out.println(task2.getTime());

		Task task3 = new TaskImpl("Trotar por las mañanas", 9, 185, 24);
		task3.setActive(true);
		System.out.println(task3.nextTimeAfter(155));
	}
}

