/* Coder: Simone LeFavour
 * Date: Nov. 26, 2024
 * Description: Final Project for Creative Computation III. Space Shooter game. The LinkedList class
 * is a class of a linked list with basic operations such as add, remove, and iteration.
 */

package com.space_shooter;

import java.util.Iterator;
import java.util.NoSuchElementException;

// linked list implementation 
public class LinkedList<T> implements Iterable<T> {
    private Node<T> head; // head

    // node class
    private static class Node<T> {
        T data; // data in node
        Node<T> next; // next node

        Node(T data) {
            this.data = data;
        }
    }

    // new element in linked list
    public void add(T data) {
        if (head == null) {
            head = new Node<>(data);
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node<>(data);
        }
    }

    // remove from linked list
    public void remove(T data) {
        if (head == null) {
            return;
        }

        if (head.data.equals(data)) {
            head = head.next;
            return;
        }

        Node<T> current = head;
        while (current.next != null && !current.next.data.equals(data)) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    // iterator for linked list
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }
} // end linked list