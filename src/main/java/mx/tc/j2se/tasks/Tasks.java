package mx.tc.j2se.tasks;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * A class that allows all collections of task type to save tasks and return a schedule of tasks,
 * based on the supplied LocalDateTime parameters.
 * @version     7.0 30 July 2022
 * @author      Arturo Yitzack Reynoso Sánchez
 */
public class Tasks {

    /**
     * A method that returns an iterable with tasks with execution time (if it's not repetitive)
     * or a repetition (if it's repetitive) between start time (exclusive) and end time (exclusive).
     * @param tasks an iterable of tasks.
     * @param start the initial datetime.
     * @param end the end datetime.
     * @return an iterable of tasks (elements of the tasks parameter) that complies with one of the
     *         folling conditions: i) if it's non repetitive, the execution time is contained in the
     *         period of time (start, end) (exclusive); ii) if it's repetitive, at least one repetition
     *         time is contained in the period of time (start, end) (exlusive).
     */
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        if (start == null) {
            throw new IllegalArgumentException("start date can not be null");
        } else if (end  == null) {
            throw new IllegalArgumentException("end date can not be null");
        }
        if (end.compareTo(start) <= 0) {
            throw new IllegalArgumentException("'from' must be less than 'to'.");
        }

        AbstractTaskList incomingTaskList;

        /* Creating incomingTaskList based on the child class implementing it. */
        if (tasks instanceof ArrayTaskListImpl) {
            incomingTaskList = TaskListFactory.createTaskList(ListTypes.types.ARRAY);
        } else {
            incomingTaskList = TaskListFactory.createTaskList(ListTypes.types.LINKED);
        }

        Stream<Task> stream = StreamSupport.stream(tasks.spliterator(), false);
        stream.filter(task -> Stream.iterate(task.getStartTime(), task::nextTimeAfter)
                        .limit(
                                !task.isRepeated() ?
                                        1 :
                                        (int) (Math.floor(Duration.between(task.getStartTime(),
                                                task.getEndTime()).toHours()/task.getRepeatInterval()) + 1))
                        .anyMatch(repetition -> (start.compareTo(repetition) < 0) && (repetition.compareTo(end) < 0)))
                .forEach(incomingTaskList::add);
        return incomingTaskList;
    }

    /**
     * A method that returns a sorted map of tasks that occurred between the period of
     * time (start, end) (exclusive). The keys are the datetimes where at least one task occurred,
     * the values are the set of tasks that occurred or schedule to occurr at that time.
     * @param tasks an iterable of tasks.
     * @param start the initial datetime.
     * @param end the end datetime.
     * @return a sorted map. The keys are instances of LocalDateTime, the datetimes where at least one task
     *         occurred. The values are the set of tasks that occurred or are scheduled to occur at that time.
     */
    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks,
                                                               LocalDateTime start, LocalDateTime end) {
        SortedMap<LocalDateTime, Set<Task>> schedule = new TreeMap<>();
        Iterable<Task> iterable = incoming(tasks, start, end);
        //Stream<Task> stream = StreamSupport.stream(iterable.spliterator(), false);
        //stream.forEach(task -> schedule.put(task.getStartTime(), ));
        for (Task task : iterable) {
            LocalDateTime date = task.getStartTime();
            while ( !(date == LocalDateTime.MIN)) {
                if (date.compareTo(start)>0 && date.compareTo(end)<0) {
                    if (schedule.get(date) == null) {
                        HashSet<Task> setTasks = new HashSet<>();
                        setTasks.add(task);
                        schedule.put(date, setTasks);
                    } else {
                        schedule.get(date).add(task);
                    }
                }
                date = task.nextTimeAfter(date);
            }
        }
        return schedule;
    }
}