package mx.tc.j2se.tasks;

import java.time.LocalDateTime;

/**
 * <p>Interface for tasks that a user can create, non repetitive and repetitive.</p>
 *
 * <p>The user can create a task, activate it, set and get its title, start time,
 * end time, tell if it's repetitive or not. In the case of a repetitive task,
 * the interface declares a method to set and return the interval of the repetition.</p>
 *
 * @version     7.0 30 June 2022
 * @author      Arturo Yitzack Reynoso Sánchez
 */
public interface Task extends Cloneable{
    /**
     * Describes the details of the task.
     * @return the title of the task.
     */
    String getTitle();

    /** Defines the title of the task.
     * @param  title the title of the task
     * @throws IllegalArgumentException if title is not a string
     * or has zero length.
     */
    void setTitle(String title);

    /** Describes if the task is active.
     * @return true if the task is active.
     */
    boolean isActive();

    /** Defines the state of the task, either active
     * or not.
     * @param active if the task is active.
     * @throws IllegalArgumentException if <code>active</> is not an
     * instance of boolean.
     */
    void setActive(boolean active);

    /** Returns the execution time for non-repetitive
     * tasks, or the start time of the repetitive task.
     * or not.
     * @return the execution time or the start time.
     */
    LocalDateTime getTime();

    /** Sets the execution time for non-repetitive tasks.
     * If the task is repetitive, it set it to
     * non-repetitive and then sets its execution time.
     * @param time the time of execution.
     * @throws IllegalArgumentException if time is negative.
     */
    void setTime(LocalDateTime time);

    /**
     * Returns the start time of the repetitive task.
     * If the task is non-repetitive, returns the
     * execution time.
     * @return start the start time.
     */
    LocalDateTime getStartTime();

    /**
     * Returns the end time of the repetitive task.
     * If the task is non-repetitive, returns the
     * execution time.
     * @return end the end time.
     */
    LocalDateTime getEndTime();

    /**
     * Returns the interval (span of time) of the
     * repetitive task. If the task is non-repetitive,
     * returns 0.
     * @return interval the interval of the task.
     */
    long getRepeatInterval();

    /**
     * Defines the start time, the end time, and the interval
     * of a repetitive task. If the task is non-repetitive,
     * it turns it into a repetitive task.
     * @param start the start time.
     * @param end the end time.
     * @param interval the interval.
     * @throws IllegalArgumentException if i) start is negative,
     *         ii) start is greater or equal than end or
     *         iii) or interval is not positive.
     */
    void setTime(LocalDateTime start, LocalDateTime end, long interval);

    /**
     * Indicates if the task is repetitive.
     * @return true if the task is repetitive.
     */
    boolean isRepeated();

    /**
     * Indicates the time of the next task after current time.
     * If the task is not active, it returns -1. If the task
     * is repetitive (non-repetitive) and active, and current time is before
     * the start time (execution) time, it returns the start time
     * (execution time).<br>
     *
     * If the task is active and non-repetitive and current time is equal or
     * after the execution time, it returns -1.<br>
     *
     * If the task is active and repetitive and if: i) current time is before a repetition,
     * it returns the time of the next repetition, or ii) current time is equal or after
     * the last repetition, it returns -1.<br>
     * @return the time of the next task (repetition) or -1 otherwise.
     * @throws IllegalArgumentException if current is negative.
     */
    LocalDateTime nextTimeAfter(LocalDateTime current);

    /**
     * Creates a shallow copy of this object.
     * @return a clone of this object.
     * @throws CloneNotSupportedException
     */
    Task clone() throws CloneNotSupportedException;
}
