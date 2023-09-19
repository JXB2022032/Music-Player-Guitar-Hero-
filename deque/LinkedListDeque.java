package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {

    private class Node {
        public T item;
        public Node prev;
        public Node next;

        public Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        this.sentinel = new Node(null, null, null);
        this.sentinel.next = sentinel;
        this.sentinel.prev = sentinel;
        this.size = 0;
    }

    public void addFirst(T item) {
        Node newFirst = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = newFirst;
        sentinel.next = newFirst;
        size++;
    }

    public void addLast(T item) {
        Node newLast = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = newLast;
        sentinel.prev = newLast;
        size++;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node node = sentinel.next;
        for (int i=0; i<this.size; i++) {
            System.out.print(node.item);
            System.out.print(" ");
            node = node.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (sentinel.next != sentinel) {
            Node firstNode = sentinel.next;
            T firstItem = firstNode.item;
            sentinel.next = firstNode.next;
            firstNode.next.prev = sentinel;

            firstNode.next = null;
            firstNode.prev = null;
            size--;
            return firstItem;
        }
        return null;
    }

    public T removeLast() {
        if (sentinel.prev != sentinel) {
            T removedItem = sentinel.prev.item;
            Node removedNode = sentinel.prev;
            removedNode.next = null;
            sentinel.prev = removedNode.prev;
            removedNode.prev.next = sentinel;
            removedNode.prev = null;
            size--;
            return removedItem;
        }
        return null;
    }

    public T get(int index) {
        Node firstNode = sentinel.next;
        for (int i=0; i<=this.size; i++) {
            if (i == index)
                return firstNode.item;
            firstNode = firstNode.next;
        }
        return null;
    }

    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private int pos;

        public LinkedListIterator() {
            this.pos = 0;
        }

        @Override
        public boolean hasNext() {
            return pos < size;
        }

        @Override
        public T next() {
            T returnItem = get(pos);
            pos++;
            return returnItem;
        }
    }

    public boolean equals(Object other) {   // Fix later
        if (this == other)
            return true;
        if (other == null)
            return false;
        if (other.getClass() != this.getClass())
            return false;
        LinkedListDeque<T> o = (LinkedListDeque<T>) other;

        if (o.size() != this.size()) {
            return false;
        }
        Iterator<T> list1 = this.iterator();
        Iterator<T> list2 = o.iterator();
        while (list1.hasNext() && list2.hasNext()) {
            if (!list1.next().equals(list2.next()))
                return false;
        }
        return true;
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(Node current, int index) {
        if (index == 0)
            return current.item;
        return getRecursiveHelper(current.next, index-1);
    }

}
