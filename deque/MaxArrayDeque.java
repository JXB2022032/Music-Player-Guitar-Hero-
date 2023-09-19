package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> maxArray;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        maxArray = c;
    }

    public T max() {
        if (super.isEmpty())
            return null;
        T maxItem = super.get(0);
        for (int i=1; i<size(); i++) {
            T currentItem = super.get(i);
            if (maxArray.compare(currentItem, maxItem) > 0) {
                maxItem = currentItem;
            }
        }
        return maxItem;
    }

    public T max(Comparator<T> c) {
        if (super.isEmpty())
            return null;
        T maxItem =super.get(0);
        for (int i=1; i<size(); i++) {
            T currentItem = super.get(i);
            if (c.compare(currentItem, maxItem) > 0) {
                maxItem = currentItem;
            }
        }
        return maxItem;
    }
}
