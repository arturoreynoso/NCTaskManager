package mx.tc.j2se.tasks;

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
     */
    public ArrayTaskListImpl(Task[] taskList) {
        this.taskList = taskList;
    }

    /**
     * {@inheritDoc}
     * This adds a task to the end of the list.
     */
    @Override
    public void add(Task task) {
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
        return this.taskList[index];
    }

    @Override
    public ArrayTaskList incoming(int from, int to){
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
