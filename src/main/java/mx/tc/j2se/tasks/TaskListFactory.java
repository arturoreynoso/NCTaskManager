package mx.tc.j2se.tasks;
/**
 * <p>Class that creates different implementations of lists of tasks.</p>
 *
 * <p>The class has a static method, that based on the type of the enum types
 * of the class ListTypes, creates one list of tasks of the following type:
 * i) ARRAY, or ii) LINKED. The ARRAY type creates an array of tasks,
 * the LINKED type creates a double linked list of tasks.</p>
 *
 * @version     5.0 12 July 2022
 * @author      Arturo Yitzack Reynoso Sánchez
 */
public class TaskListFactory {

    public static AbstractTaskList createTaskList(ListTypes.types type) {
        if (type == ListTypes.types.ARRAY) {
            return new ArrayTaskListImpl();
        } else if (type == ListTypes.types.LINKED) {
            return new LinkedTaskListImpl();
        }
        return null;
    }
}
