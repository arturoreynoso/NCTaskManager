package mx.tc.j2se.tasks;
/**
 * <p>Implementation of LinkedTaskList. This implementation uses a
 * double linked list of tasks. A new private inner class <code>Node</code>
 * is created, where a node wraps a task.</p>
 *
 * @version     4.0 6 July 2022
 * @author      Arturo Yitzack Reynoso Sánchez
 */
public class LinkedTaskListImpl extends AbstractTaskList{

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

    @Override
    public void add(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("You can't add a null task.");
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
            throw new IllegalArgumentException("You can't remove a null task.");
        }
        Node node = getNode(task);
        if (node == null) {
            return false;
        }
        removeNode(node);
        return true;
    }

    @Override
    public int size() {
        return this.length;
    }

    @Override
    public Task getTask(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index can't be negative.");
        }
        if (this.length <= index) {
            throw new IllegalArgumentException("index can't be equal or greater than the size of the list.");
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
}
