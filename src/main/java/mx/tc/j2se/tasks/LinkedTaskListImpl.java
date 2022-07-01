package mx.tc.j2se.tasks;
/**
 * <p>Implementation of LinkedTaskList. This implementation uses a
 * double linked list of tasks. A new private inner class <code>Node</code>
 * is created, where a node wraps a task.</p>
 *
 * @version     3.0 1 July 2022
 * @author      Arturo Yitzack Reynoso Sánchez
 */
public class LinkedTaskListImpl implements LinkedTaskList{

    /* Private inner class of nodes. */
    private class Node {
        /* The task of the node */
        private Task task;
        /* The previous node */
        private Node previous;
        /* The next node */
        private Node next;

        /* Build a node with a task. */
        private Node(Task task) {
            this.task = task;
        }
    }

    /* First task of the list. */
    private Node head;
    /* Last task of the list. */
    private Node tail;
    /* Number of tasks in the list. */
    private int length;

    /**
     * Returns the length of the list.
     * @return the length of the list, the number of elements in the list.
     */

    public int getLength() {
        return this.length;
    }
    @Override
    public void add(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Cannot add a null element.");
        }
        Node n = new Node(task);
        length++;
        if (this.tail == null) {
            this.head = n;
        } else {
            this.tail.next = n;
            n.previous = tail;
        }
        this.tail = n;
    }

    /**
     * Cleans the list of tasks, with no elements.
     */
    private void clean() {
        this.head = null;
        this.tail = null;
        this.length = 0;
    }

    /**
     * Removes the node from the list.
     * @param n the node to be eliminated.
     */
    private void removeNode(Node n) {
        if (n == null || head == null) {
            return;
        }
        this.length--;
        if (this.head.equals(tail)) {
            clean();
        } else if (head.equals(n)) {
            n.next.previous = null;
            this.head = n.next;
        } else if (tail.equals(n)) {
            n.previous.next = null;
            tail = n.previous;
        } else {
            n.previous.next = n.next;
            n.next.previous = n.previous;
        }
    }

    /**
     * Returns the node that wraps the parameter task. If there is no
     * such node,returns null.
     * @param task the task of the node we want to retrieve.
     * @return the node of the parameter task.
     */
    private Node getNode(Object task) {
        Node n = this.head;
        while (n != null) {
            if (n.task.equals(task)) {
                return n;
            }
            n = n.next;
        }
        return null;
    }

    @Override
    public boolean remove(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("task can't be null.");
        }
        if (getNode(task) == null) {
            return false;
        }
        Node n = getNode(task);
        removeNode(n);
        return true;
    }

    @Override
    public int size() {
        return this.length;
    }

    @Override
    public Task getTask(int index) {
        if ((index < 0) || (index >= this.length)) {
            throw new IndexOutOfBoundsException("El índice no es válido.");
        }
        int counter = 0;
        Node n = this.head;
        while(n != null) {
            if (counter == index) {
                return n.task;
            }
            n = n.next;
            counter++;
        }
        return null;
    }

    @Override
    public LinkedTaskList incoming(int from, int to) {
        if (from < 0  || to <= from) {
            throw new IllegalArgumentException("from must not be negative nor greater or " +
                    "equal than to.");
        }
        LinkedTaskList incomingTaskList = new LinkedTaskListImpl();
        Node n = this.head;

        outer:
        while (n != null) {
            if (!n.task.isActive()) {
                n = n.next;
                continue;
            }
            if (!n.task.isRepeated() && (from < n.task.getTime() && n.task.getTime() < to)) {
                incomingTaskList.add(n.task);
                n = n.next;
                continue;
            }
            if (n.task.isRepeated()) {
                if (to <= n.task.getStartTime() || n.task.getEndTime() <= from) {
                    n = n.next;
                    continue;
                }
                if ((from < n.task.getStartTime()) && (n.task.getStartTime() < to)) {
                    incomingTaskList.add(n.task);
                    n = n.next;
                    continue;
                }
                for (int i = n.task.nextTimeAfter(n.task.getStartTime()); i != -1; i = n.task.nextTimeAfter(i)) {
                    if ((from < i) && (i < to)) {
                        incomingTaskList.add(n.task);
                        n = n.next;
                        continue outer;
                    }
                }
            }
            n = n.next;
        }
        return incomingTaskList;
    }
}
