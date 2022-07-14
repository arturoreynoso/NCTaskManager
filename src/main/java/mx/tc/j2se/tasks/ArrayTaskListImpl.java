package mx.tc.j2se.tasks;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>Implementation of AbstractTaskList. This implementation uses an
 * array of tasks. if a task must be added, removed or replaced, a
 * new array of tasks must be created, the size changed accordingly and
 * tasks copied to the new array. Objects of this class can be cloned.</p>
 *
 * @version     5.0 12 July 2022
 * @author      Arturo Yitzack Reynoso Sánchez
 */
public class ArrayTaskListImpl extends AbstractTaskList {
    /* An array of tasks */
    Task[] taskList;

    /*Constructor with no parameters. Initializes a zero-length array of tasks. */
    public ArrayTaskListImpl() {
        this.taskList = new Task[0];
    }

    /**
     * Constructor that receives an array of tasks.
     * @param taskList an array of tasks.
     * @throws IllegalArgumentException if a task is not
     *         an instance of Task class.
     */
    public ArrayTaskListImpl(Task[] taskList) {
        for (int i = 0; i < taskList.length; i++) {
            if (taskList[i] == null) {
                String message = String.format("taskList[%d] is null. Tasks in the list can not be null!", i);
                throw new IllegalArgumentException(message);
            }
        }
        this.taskList = taskList;
    }

    /* Inner private class for iterators. */
    private class Iterador implements Iterator<Task> {

        /* Index of the iterator. */
        private int index;

        /* Indicates if there is a next task. */
        @Override public boolean hasNext() {
            return index < size();
        }

        /* Returns the next task. */
        @Override public Task next() {
            if (index >= size()) {
                throw new NoSuchElementException();
            }
            index += 1;
            return taskList[index-1];
        }

    }

    /**
     * Returns an iterator to iterate the array of tasks.
     * @return an iterator to iterate the array of tasks.
     */
    @Override public Iterator<Task> iterator() {
        return new Iterador();
    }

    /**
     * {@inheritDoc}
     * This adds a task to the end of the list.
     */
    @Override
    public void add(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("You can't add a null task.");
        }
        Task[] newTaskList = new Task[taskList.length + 1];
        if (taskList.length == 0) {
            newTaskList[0] = task;
        } else {
            System.arraycopy(this.taskList, 0, newTaskList, 0, this.taskList.length);
            newTaskList[newTaskList.length-1] = task;
        }
        this.taskList = newTaskList;
    }

    @Override
    public boolean remove(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("You can't remove a null task.");
        }
        for (int i = 0; i < this.taskList.length; i++) {
            if (taskList[i].equals(task)) {
                Task[] newTaskList = new Task[taskList.length - 1];
                if (i == 0) {
                    System.arraycopy(this.taskList, 1, newTaskList, 0, this.taskList.length - 1);
                    this.taskList = newTaskList;
                } else if (i == taskList.length - 1) {
                    System.arraycopy(this.taskList, 0, newTaskList, 0, this.taskList.length - 1);
                    this.taskList = newTaskList;
                } else {
                    System.arraycopy(this.taskList,0,newTaskList, 0, i);
                    System.arraycopy(this.taskList,i+1,newTaskList, i, taskList.length - i - 1);
                    this.taskList = newTaskList;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return taskList.length;
    }

    @Override
    public Task getTask(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index can't be negative!");
        }
        if (this.taskList.length <= index) {
            throw new IllegalArgumentException("index can't be equal or greater than the size of the list.");
        }
        return this.taskList[index];
    }


    @Override
    public AbstractTaskList clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Returns a string representation of the array of tasks.
     * @return a string representation of the array of tasks.
     */
    @Override
    public String toString() {
        return Arrays.toString(taskList);
    }

    /**
     * Compares the object received with this array for equality.
     * @param o the object to compare.
     * @return <code>true</code> if the array is equal to the object received;
     *         <code>false</code> in other case.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ArrayTaskListImpl)) {
            return false;
        }
        ArrayTaskListImpl tasks = (ArrayTaskListImpl) o;
        return Arrays.equals(taskList, tasks.taskList);
    }

    /**
     * Returns the hash code value for this array of tasks.
     * @return the hash code value for this array of tasks.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(taskList);
    }
}