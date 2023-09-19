package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        this.items = (T[]) new Object[8];
        this.size = 0;
        this.nextFirst = 4;
        this.nextLast = 5;
    }

    public ArrayDeque(int capacity) {
        this.items = (T[]) new Object[capacity];
        this.size = 0;
        this.nextFirst = 4;
        this.nextLast = 5;
    }

    public int size() {
        return size;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(size*2);
        }

        this.items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size++;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size*2);
        }

        this.items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size++;
    }

    private void resize(int capacity) {
        T[] newDeque = (T[]) new Object[capacity];
        int oldIndex = plusOne(nextFirst);
        for (int newIndex = 0; newIndex < size; newIndex++) {
            newDeque[newIndex] = items[oldIndex];
            oldIndex = plusOne(oldIndex);
        }
        this.items = newDeque;
        this.nextFirst = capacity - 1;
        this.nextLast = size;
    }

    private int minusOne(int index) {
        return (index - 1 + items.length) % items.length;
    }

    private int plusOne(int index) {
        return (index + 1) % items.length;
    }

    public void printDeque() {
        for (int i=plusOne(nextFirst); i != nextLast; i=plusOne(i)) {
            System.out.print(items[i]);
            System.out.print(" ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0)
            return null;
        T toRemove = items[plusOne(nextFirst)];
        items[plusOne(nextFirst)] = null;
        nextFirst = plusOne(nextFirst);
        size--;

        if (items.length >= 16 && size < items.length / 4) {
            resize(items.length / 2);
        }
        return toRemove;
    }

    public T removeLast() {
        if (size == 0)
            return null;
        T toRemove = items[minusOne(nextLast)];
        items[minusOne(nextLast)] = null;
        size--;
        nextLast = minusOne(nextLast);

        if (items.length >= 16 && size < items.length / 4) {
            resize(items.length / 2);
        }
        return toRemove;
    }

    public T get(int index) {
        if (index > size) {
            return null;
        }
        return items[plusOne(nextFirst+index)];
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int pos;

        public ArrayDequeIterator() {
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

    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null)
            return false;
        if (other.getClass() != this.getClass())
            return false;
        ArrayDeque<T> o = (ArrayDeque<T>) other;
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

}
