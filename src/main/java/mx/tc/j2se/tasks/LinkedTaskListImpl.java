package mx.tc.j2se.tasks;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * <p>Implementation of AbstractTaskList. This implementation uses a
 * double linked list of tasks. Objects of this class can be cloned.
 * A private inner class <code>Node</code>
 * is created, where a node wraps a task.</p>
 *
 * @version     5.0 12 July 2022
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

    /* Inner private class for iterators. */
    private class Iterador implements Iterator<Task> {
        /* The previous node. */
        private Node previous;
        /* The next node. */
        private Node next;

        /* Creates a new iterator. */
        private Iterador() {
            previous = null;
            next = head;
        }

        /* Indicates if there is a next task. */
        @Override public boolean hasNext() {
            return next != null;
        }

        /* Returns the next task. */
        @Override public Task next() {
            if (next == null) {
                throw new NoSuchElementException();
            }
            Node n = next;
            previous = n;
            next = n.next;
            return previous.task;
        }
    }

    /**
     * Returns an iterator to iterate the linked list of tasks.
     * @return an iterator to iterate the linked list of tasks.
     */
    @Override public Iterator<Task> iterator() {
        return new Iterador();
    }

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
     * Cleans the list of tasks, leaving it with no elements.
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

    @Override
    public AbstractTaskList clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Returns a string representation of the linked list of tasks.
     * @return a string representation of the linked list of tasks.
     */
    @Override
    public String toString() {
        String s = "[]";
        if (this.head == null) {
            return s;
        }

        Node n = this.head;
        StringBuilder stringOfList = new StringBuilder("[" + n.task.toString());

        while (n.next != null) {
            stringOfList.append(", ").append(n.next.task.toString());
            n = n.next;
        }
        return stringOfList + "]";
    }

    /**
     * Compares the object received with this linked list for equality.
     * @param o the object to compare.
     * @return <code>true</code> if the list is equal to the object received;
     *         <code>false</code> in other case.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LinkedTaskListImpl)) {
            return false;
        }
        LinkedTaskListImpl tasks = (LinkedTaskListImpl) o;
        if (tasks.length != this.length) {
            return false;
        } else {
            Node m = this.head;
            Node n = tasks.head;
            while (m != null) {
                if (m.task.equals(n.task)) {
                    m = m.next;
                    n = n.next;
                } else {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Returns the hash code value for this linked list of tasks.
     * @return the hash code value for this linked list of tasks.
     */
    @Override
    public int hashCode() {
        int hashCode = 1;
        for (Task task : this) {
            hashCode = 31*hashCode + (task == null ? 0 : task.hashCode());
        }
        return hashCode;
    }
}
