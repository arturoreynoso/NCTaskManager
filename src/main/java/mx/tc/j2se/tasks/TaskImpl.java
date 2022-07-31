package mx.tc.j2se.tasks;

import java.time.LocalDateTime;

/**
 * <p>Class for tasks that a user can create, non repetitive and repetitive.</p>
 *
 * <p>The user can create a task, activate it, set and get its title, start time,
 * end time, tell if it's repetitive or not. In the case of a repetitive task,
 * the class provides a method to set and return the interval of the repetition.</p>
 *
 * @version     7.0 30 July 2022
 * @author      Arturo Yitzack Reynoso Sánchez
 */
public class TaskImpl implements Task {

    /* The title of the task */
    private String title;

    /* The start time of a repetitive task or the execution time
    *  of a non-repetitive task. */
    private LocalDateTime start;

    /* The end time of a repetitive task. In the case of a non-repetitive task,
    *  coincides with instance variable start. */
    private LocalDateTime end;

    /* The time between each repetition of a repetitive task */
    private long interval;

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
    TaskImpl(String title, LocalDateTime time) {
        if (title == null) {
            throw new IllegalArgumentException("title can't be null.");
        }
        if (title.isEmpty()) {
            throw new IllegalArgumentException("title can't be empty.");
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
    TaskImpl(String title, LocalDateTime start, LocalDateTime end, long interval) {
        if (title == null) {
            throw new IllegalArgumentException("title can't be null.");
        }
        if (title.isEmpty()) {
            throw new IllegalArgumentException("title can't be empty.");
        }
        if (end.compareTo(start) <= 0) {
            throw new IllegalArgumentException("end date must be greater than start date.");
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
    public LocalDateTime getTime() {
        return this.start;
    }

    @Override
    public void setTime(LocalDateTime time) {
        if (this.repeated) {
            this.repeated = false;
        }
        this.start = time;
        this.end = time;
    }

    @Override
    public LocalDateTime getStartTime() {
        return this.start;
    }

    @Override
    public LocalDateTime getEndTime() {
        return this.end;
    }

    @Override
    public long getRepeatInterval() {
        return (!this.repeated ? 0 : this.interval);
    }

    @Override
    public void setTime(LocalDateTime start, LocalDateTime end, long interval) {
        if (end.compareTo(start) <= 0) {
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
    public LocalDateTime nextTimeAfter(LocalDateTime current) {
        if ((!this.active) || (this.end.compareTo(current) <= 0)) {
            return LocalDateTime.MIN;
        }
        if (current.compareTo(this.start) < 0) {
            return this.start;
        }
        if (this.repeated) {
            LocalDateTime startTimeOfInterval = this.start;
            while (startTimeOfInterval.compareTo(current)<=0) {
                startTimeOfInterval = startTimeOfInterval.plusHours(this.interval);
            }
            if (startTimeOfInterval.compareTo(this.end) <= 0) {
                return startTimeOfInterval;
            }
        }
        return LocalDateTime.MIN;
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
            s = String.format("Non-repetitive Task: {title: %s, execution time: %s, active: %s}",
                    this.title, this.start, this.active);
        } else {
            s = String.format("Repetitive Task: {title: %s, start time: %s, end time: %s, interval: %d, active: %s}",
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
        result = 31 * result + this.start.hashCode();
        result = 31 * result + this.end.hashCode();
        result = 31 * result + (int)this.interval;
        result = 31 * result + active.hashCode();
        result = 31 * result + repeated.hashCode();
        return result;
    }
}
