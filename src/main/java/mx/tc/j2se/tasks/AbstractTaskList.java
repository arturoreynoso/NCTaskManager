package mx.tc.j2se.tasks;
/**
 * <p>Abstract class for lists of tasks. Tasks in the list can be repeated, the
 * order of the tasks does not matter, with operations for delete, add,
 * get the size and get a task by index. </p>
 *
 * @version     4.0 6 July 2022
 * @author      Arturo Yitzack Reynoso Sánchez
 */
public abstract class AbstractTaskList {
    /**
     * Adds the specified task to the list.
     * @param task a task to be added to the list.
     * @throws IllegalArgumentException if task is null.
     */
    abstract void add(Task task);

    /**
     * Removes a task from the list and returns true if such a task was
     * in the list. If the list contains the same task several times,
     * any of them should be removed.
     * @param task a task to be removed from the list.
     * @return <code>true</code> if the list contains the task, else
     *         returns <code>false</code>.
     * @throws IllegalArgumentException if task is null.
     */
    abstract boolean remove(Task task);

    /**
     * Returns the number of tasks in the list.
     * @return the size of the list, the number of elements it contains.
     */
    abstract int size();

    /**
     * Returns the task in the list at the specified index.
     * @param index the index of the task to be retrieved.
     * @return the task in the list at the specified index.
     * @throws IndexOutOfBoundsException if index is negative or
     *         greater or equal than the number of tasks in the list.
     */
    abstract Task getTask(int index);

    /**
     * Returns a list of tasks whose: i) execution time is contained
     * in the (from, to) range (exclusive), or ii) at least one
     * repetition is contained in the (from, to) range (exclusive).
     * @param from                        the lower bound (exclusive) of at least one
     *                                    execution time of the tasks to include.
     * @param to                          the upper bound (exclusive) of at least one
     *                                    execution time of the tasks to include
     * @return <code>ArrayTaskList</code> a list of tasks that have at least one
     *                                    execution time scheduled in the range
     *                                    (from, to) (exclusive).
     * @throws IllegalArgumentException   if from is i) negative or ii) greater or equal than to.
     */
    AbstractTaskList incoming(int from, int to) {
        if (from < 0) {
            throw new IllegalArgumentException("'from' must be zero or positive.");
        }
        if (to <= from) {
            throw new IllegalArgumentException("'from' must be less than 'to'.");
        }

        AbstractTaskList incomingTaskList = null;

        /* Creating incomingTaskList based on the child class implementing it. */
        if (this instanceof ArrayTaskListImpl) {
            incomingTaskList = new ArrayTaskListImpl();
        } else if (this instanceof LinkedTaskListImpl) {
            incomingTaskList = new LinkedTaskListImpl();
        }

        outer:
        for (int i = 0; i < this.size(); i++) {
            Task task = this.getTask(i);
            if (task.nextTimeAfter(0) == -1) {
                continue;
            } else {
                int repetition = task.nextTimeAfter(0);
                while (repetition > 0) {
                    if ((from < repetition) && (repetition < to)) {
                        incomingTaskList.add(task);
                        continue outer;
                    }
                    repetition = task.nextTimeAfter(repetition);
                }
            }
        }
        return incomingTaskList;
    }
}
