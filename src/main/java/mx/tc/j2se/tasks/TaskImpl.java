package mx.tc.j2se.tasks;

/**
 * <p>Class for tasks that a user can create, non repetitive and repetitive.</p>
 *
 * <p>The user can create a task, activate it, set and get its title, start time,
 * end time, tell if it's repetitive or not. In the case of a repetitive task,
 * the class provides a method to set and return the interval of the repetition.</p>
 *
 * @version     2.0 27  June 2022
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
}
