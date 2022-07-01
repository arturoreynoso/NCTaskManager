package mx.tc.j2se.tasks;
/**
 * <p>Implementation of ArrayTaskList. This implementation uses an
 * array of tasks. if a task must be added, removed or replaced, a
 * new array of tasks must be created, the size changed accordingly and
 * tasks copied to the new array. </p>
 *
 * @version     3.0 1 July 2022
 * @author      Arturo Yitzack Reynoso Sánchez
 */
public class ArrayTaskListImpl implements ArrayTaskList {

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
            if (!(taskList[i] instanceof Task)) {
                throw new IllegalArgumentException("The list of tasks must contain elements of " +
                        " classes that implements interface Task.");
            }
        }
        this.taskList = taskList;
    }

    /**
     * {@inheritDoc}
     * This adds a task to the end of the list.
     */
    @Override
    public void add(Task task) {
        if (!(task instanceof Task)) {
            throw new IllegalArgumentException("task must be an instance of Task.");
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
        if (!(task instanceof Task)) {
            throw new IllegalArgumentException("task must be an instance of Task.");
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
        if (index < 0 || this.taskList.length <= index) {
            throw new IllegalArgumentException("index must not exceed the permissible " +
                    "limits for the list.");
        }
        return this.taskList[index];
    }

    @Override
    public ArrayTaskList incoming(int from, int to){
        if (from < 0  || to <= from) {
            throw new IllegalArgumentException("from must not be negative nor greater or " +
                    "equal than to.");
        }
        ArrayTaskList incomingTaskList = new ArrayTaskListImpl();
        outer:
        for(Task task : this.taskList) {
            if(!task.isActive()) {
                continue;
            }
            if (!task.isRepeated() && (from < task.getTime() && task.getTime() < to)) {
                incomingTaskList.add(task);
                continue;
            }

            if (task.isRepeated()) {
                if (to <= task.getStartTime() || task.getEndTime() <= from) {
                    continue;
                }
                if ((from < task.getStartTime()) && (task.getStartTime() < to)) {
                    incomingTaskList.add(task);
                    continue;
                }

                for (int i = task.nextTimeAfter(task.getStartTime()); i != -1; i = task.nextTimeAfter(i)){
                    if ((from < i) && (i < to)) {
                        incomingTaskList.add(task);
                        continue outer;
                    }
                }
            }
        }
        return incomingTaskList;
    }
}
