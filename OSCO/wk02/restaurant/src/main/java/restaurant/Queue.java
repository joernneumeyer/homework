package restaurant;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.jcip.annotations.NotThreadSafe;
/**
 * Simple generic unbound queue. This implementation is not tread safe.
 * This implementation will fail on get, when get is called in the empty 
 * queue situation.
 * @author ode
 * @param <T>
 */
@NotThreadSafe
class Queue<T> {
  final Object lock = new Object();
    /**
     * Static Node.
     */
    private static class Node<E> {
        final E item;

        Node<E> next;

        Node(E item) {
            this.item = item;
            next = null;
        }
    }

    private Node<T> head, tail;
    /**
     * Empty queue constructor.
     */
    Queue() {
        head = null;
        tail = null;
    }

    /**
     * Empty test.
     * @return true if queue contains no elements.
     */
    boolean empty() {
      synchronized (this.lock) {
        return (head == null);
      }
    }

    /**
     * Put one element at tail of queue.
     * @param item to put.
     */
    void put(T item) {
      synchronized (this.lock) {
        Node<T> t = tail;
        tail = new Node<>(item);
        if (empty()) {
            head = tail;
        } else {
            t.next = tail;
        }
      }
    }

    /**
     * Get and remove (take) one element from head.
     * This method may fail  an Exception if called on an empty Queue.
     * @return the head element.
     */
    T get() {
      synchronized (this.lock) {
        T v = head.item;
        Node<T> t = head.next;
        head = t;
        return v;
      }
    }
    
    T take() {
      while (this.empty()) { }
      return this.get();
    }
}
