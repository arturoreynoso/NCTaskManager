package mx.tc.j2se.tasks;

import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <p>Abstract class for lists of tasks. Tasks in the list can be repeated, the
 * order of the tasks does not matter, with operations for delete, add,
 * get the size and get a task by index. Objects of concrete classes that
 * extends this class can be cloned.</p>
 *
 * @version     7.0 30 July 2022
 * @author      Arturo Yitzack Reynoso Sánchez
 */
public abstract class AbstractTaskList implements Iterable<Task>, Cloneable {
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
     * Creates a shallow copy of this object.
     * @return a clone of this object.
     */
    public AbstractTaskList clone() throws CloneNotSupportedException {
        return (AbstractTaskList)super.clone();
    }

    /**
     * Returns a string representation of the list of tasks.
     * @return a string representation of the list of tasks.
     */
    public abstract String toString();

    /**
     * Compares the object received with this list of tasks for equality.
     * @param o the object to compare.
     * @return <code>true</code> if the list is equal to the object received;
     *         <code>false</code> in other case.
     */
    public abstract boolean equals(Object o);

    /**
     * Returns the hash code value for this list of tasks.
     * @return the hash code value for this list of tasks.
     */
    public abstract int hashCode();

    /**
     * Returns a stream of elements of this list.
     * @return a stream of elements of this list.
     */
    abstract Stream<Task> getStream();
}
