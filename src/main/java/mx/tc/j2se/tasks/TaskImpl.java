package mx.tc.j2se.tasks;

/**
 * <p>Class for tasks that a user can create, non repetitive and repetitive.</p>
 *
 * <p>The user can create a task, activate it, set and get its title, start time,
 * end time, tell if it's repetitive or not. In the case of a repetitive task,
 * the class provides a method to set and return the interval of the repetition.</p>
 *
 * @version     1.0 20  June 2022
 * @author      Arturo Yitzack Reynoso Sánchez
 */
public class TaskImpl implements Task {
    private String title;
    private int start;
    private int end;
    private int interval;
    private boolean active;
    private boolean repeated;

    public TaskImpl() {}

    /* Constructor for non-repetitive tasks. */
    TaskImpl(String title, int time) {
        this.title = title;
        this.start = time;
        this.active = false;
        this.repeated = false;
        this.end = time;
    }

    /* Constructor for repetitive tasks. */
    TaskImpl(String title, int start, int end, int interval) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.active = false;
        this.repeated = true;
    }

    /**
     * Describes the details of the task.
     * @return the title of the task.
     */
    @Override
    public String getTitle() {
        return this.title;
    }

    /** Defines the title of the task.
     * @param title the title of the task
     */
    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    /** Describes if the task is active.
     * @return active if the task is active.
     */
    @Override
    public boolean isActive() {
        return this.active;
    }

    /** Defines the state of the task, either active
     * or not.
     * @param active if the task is active.
     */
    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    /** Returns the execution time for non-repetitive
     * tasks, or the start time of the repetitive task.
     * or not.
     * @return the execution time or the start time.
     */
    @Override
    public int getTime() {
        return this.start;
    }

    /** Sets the execution time for non-repetitive tasks.
     * If the task is repetitive, it set it to
     * non-repetitive and then sets its execution time.
     * @param time the time of execution.
     */
    @Override
    public void setTime(int time) {
        if (this.repeated) {
            this.repeated = false;
        }
        this.start = time;
        this.end = time;
    }

    /**
     * Returns the start time of the repetitive task.
     * If the task is non-repetitive, returns the
     * execution time.
     * @return start the start time.
     */
    @Override
    public int getStartTime() {
        return this.start;
    }

    /**
     * Returns the end time of the repetitive task.
     * If the task is non-repetitive, returns the
     * execution time.
     * @return end the end time.
     */
    @Override
    public int getEndTime() {
        return this.end;
    }

    /**
     * Returns the interval (span of time) of the
     * repetitive task. If the task is non-repetitive,
     * returns 0.
     * @return interval the interval of the task.
     */
    @Override
    public int getRepeatInterval() {
        return (!this.repeated ? 0 : this.interval);
    }

    /**
     * Defines the start time, the end time, and the interval
     * of a repetitive task. If the task is non-repetitive,
     * it turns it into a repetitive task.
     * @param start the start time.
     * @param end the end time.
     * @param interval the interval.
     */
    @Override
    public void setTime(int start, int end, int interval) {
        if (!this.repeated) {
            this.repeated = true;
        }
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    /**
     * Indicates if the task is repetitive.
     * @return repeated if the task is repetitive.
     */
    @Override
    public boolean isRepeated() {
        return this.repeated;
    }

    /**
     * Indicates the time of the next task after current time.
     * If the task is not active, it returns -1. If the task
     * is repetitive (non-repetitive) and active, and current time is before
     * the start time (execution) time, it returns the start time
     * (execution time).
     *
     * If the task is active and non-repetitive and current time is equal or
     * after the execution time, it returns -1.
     *
     * If the task is active and repetitive and i) current time is between a repetition,
     * it returns the time of the next repetition, or ii) current time is equal or after
     * the end time, it returns -1.
     * @return the time of the next task or -1 otherwise.
     */
    @Override
    public int nextTimeAfter(int current) {
        if ((!this.active) || (current > this.end)) {
            return -1;
        }
        if (current < this.start) {
            return this.start;
        }
        if (this.repeated) {
            int startTimeOfInterval = this.start;
            while (current >= startTimeOfInterval) {
                startTimeOfInterval += this.interval;
            }
            return startTimeOfInterval;
        }
        return -1;
    }
}
