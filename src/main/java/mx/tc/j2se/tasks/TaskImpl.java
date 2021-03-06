package mx.tc.j2se.tasks;
/**
 * <p>Class for tasks that a user can create, non repetitive and repetitive.</p>
 *
 * <p>The user can create a task, activate it, set and get its title, start time,
 * end time, tell if it's repetitive or not. In the case of a repetitive task,
 * the class provides a method to set and return the interval of the repetition.</p>
 *
 * @version     6.0 21 July 2022
 * @author      Arturo Yitzack Reynoso Sánchez
 */
public class TaskImpl implements Task {

    /* The title of the task */
    private String title;

    /* The start time of a repetitive task or the execution time
    *  of a non-repetitive task. */
    private int start;

    /* The end time of a repetitive task. In the case of a non-repetitive task,
    *  coincides with instance variable start. */
    private int end;

    /* The time between each repetition of a repetitive task */
    private int interval;

    /* Indicates if the task is active. */
    private boolean active;

    /* Indicates if the task is repetitive. */
    private boolean repeated;

    /* Empty constructor. */
    public TaskImpl() {}

    /**
     * Constructor for non-repetitive tasks.
     * @throws IllegalArgumentException if title is not a
     *         String or has zero length or time is negative.
     */

    TaskImpl(String title, int time) {
        if (title == null) {
            throw new IllegalArgumentException("title can't be null.");
        }
        if (title.isEmpty()) {
            throw new IllegalArgumentException("title can't be empty.");
        }

        if (time <= 0) {
            throw new IllegalArgumentException("time must be positive");
        }
        this.title = title;
        this.start = time;
        this.active = false;
        this.repeated = false;
        this.end = time;
    }

    /**
     * Constructor for repetitive tasks.
     * @throws IllegalArgumentException if i) title is not a
     *         String or has zero length, ii) start is negative,
     *         iii) end is not greater than start, iv) interval
     *         is not positive.
     */
    TaskImpl(String title, int start, int end, int interval) {
        if (title == null) {
            throw new IllegalArgumentException("title can't be null.");
        }
        if (title.isEmpty()) {
            throw new IllegalArgumentException("title can't be empty.");
        }
        if (start < 0) {
            throw new IllegalArgumentException("start must be non-negative.");
        }
        if (end <= start) {
            throw new IllegalArgumentException("end must be greater than start.");
        }
        if (interval <= 0) {
            throw new IllegalArgumentException("interval must be positive.");
        }
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.active = false;
        this.repeated = true;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean isActive() {
        return this.active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public int getTime() {
        return this.start;
    }

    @Override
    public void setTime(int time) {
        if (time < 0) {
            throw new IllegalArgumentException("time must not be negative.");
        }
        if (this.repeated) {
            this.repeated = false;
        }
        this.start = time;
        this.end = time;
    }

    @Override
    public int getStartTime() {
        return this.start;
    }

    @Override
    public int getEndTime() {
        return this.end;
    }

    @Override
    public int getRepeatInterval() {
        return (!this.repeated ? 0 : this.interval);
    }

    @Override
    public void setTime(int start, int end, int interval) {
        if (start < 0) {
            throw new IllegalArgumentException("start must be positive.");
        } else if (end <= start) {
            throw new IllegalArgumentException("end must be greater than start.");
        } else if (!(interval > 0)) {
            throw new IllegalArgumentException("interval must be positive.");
        }
        if (!this.repeated) {
            this.repeated = true;
        }
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    @Override
    public boolean isRepeated() {
        return this.repeated;
    }

    @Override
    public int nextTimeAfter(int current) {
        if (current < 0) {
            throw new IllegalArgumentException("current must not be negative.");
        }
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
            if (startTimeOfInterval <= this.end) {
                return startTimeOfInterval;
            }
        }
        return -1;
    }

    @Override
    public Task clone() throws CloneNotSupportedException {
        return (TaskImpl)super.clone();
    }

    /**
     * Returns a string representation of the task.
     * @return a string representation of the task.
     */
    @Override
    public String toString() {
        String s;
        if (!this.repeated) {
            s = String.format("Non-repetitive Task: {title: %s, execution time: %d, active: %s}",
                    this.title, this.start, this.active);
        } else {
            s = String.format("Repetitive Task: {title: %s, start time: %d, end time: %d, interval: %d, active: %s}",
                    this.title, this.start, this.end, this.interval, this.active);
        }
        return s;
    }

    /**
     * Compares the object received with this task.
     * @param o the object to compare.
     * @return <code>true</code> if the task is equal to the object received;
     *         <code>false</code> in other case.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TaskImpl)) {
            return false;
        }
        TaskImpl task = (TaskImpl)o;
        return task.title.equals(this.title) && task.start == this.start && task.end == this.end
                && task.active == this.active && task.repeated == this.repeated;
    }

    /**
     * Returns the hash code value for this task.
     * @return the hash code value for this task.
     */
    @Override
    public int hashCode() {
        Boolean active = this.active;
        Boolean repeated = this.repeated;
        int result = this.title.hashCode();
        result = 31 * result + this.start;
        result = 31 * result + this.end;
        result = 31 * result + this.interval;
        result = 31 * result + active.hashCode();
        result = 31 * result + repeated.hashCode();
        return result;
    }
}
