public class ArrayDeque<T> {
    /**
     * Similar to sentinel nodes.
     * Note that the value corresponding to sentFrontIdx/senBackIdx
     * is excluded from the valid items. Here we use a circular view
     * on the array, using modular arithmetic
     *
     * sentFrontIdx can also be considered nextFirst
     * sentBackIdx can also be considered nextLast
     */
    private int sentFrontIdx;
    private int sentBackIdx;

    /**
     * invariant: size == (sentBackIdx - sentFrontIdx - 1) mod capacity
     */
    private int size;
    /**
     * The maximum size of the ArrayDeque. Capacity should be doubled
     * every time it is equal to size. and It should divide 2 if size
     * is less than capacity * 0.25 and capacity >= 16. We initialize
     * capacity to be 8.
     */
    private int capacity;

    private T[] arrayBase;

    /**
     * create an empty array
     */
    public ArrayDeque() {
        sentFrontIdx = 0;
        sentBackIdx = 1;
        size = 0;
        capacity = 8;
        arrayBase = (T[]) new Object[capacity];
    }

    /**
     * Helper function to calculate the true index counting from
     * given index using modular arithmetic. Note that count can
     * be negative.
     */
    private int getCircularIndex(int start, int count) {
        return ((start + count) % capacity + capacity) % capacity;
    }

    /**
     * Given the idx of conceptual ArrayDeque, return the true index of
     * arrayBase.
     * @param idx
     * @return
     */
    private int getTrueIdx(int idx) {
        return getCircularIndex(sentFrontIdx, idx + 1);
    }

    /**
     * Double the capacity and update the arrayBase
     * Note that sentFrontIdx and sentBackIdx should all be modified
     */
    private void doubleCapacity() {
        T[] arrayDoubled = (T[]) new Object[2 * capacity];
        for (int i = 0; i < size; i++) {
            //Note that sentFrontIdx is and excluded idx
            arrayDoubled[i] = arrayBase[getCircularIndex(sentFrontIdx, i + 1)];
        }
        arrayBase = arrayDoubled;
        capacity = arrayDoubled.length;
        sentBackIdx = size;
        sentFrontIdx = getCircularIndex(0, -1);
    }

    /**
     * Halve the capacity. It works under the assumption that capacity >= 2 * size
     * and size should not be 0
     */
    private void halveCapacity() {
        T[] arrayHalved = (T[]) new Object[capacity / 2];
        for (int i = 0; i < size; i++) {
            arrayHalved[i] = arrayBase[getTrueIdx(i)];
        }
        arrayBase = arrayHalved;
        capacity = arrayHalved.length;
        sentBackIdx = size;
        sentFrontIdx = getCircularIndex(0, -1);
    }


    /**
     *  Adds an item of type T to the front of the deque.
     * @param item
     */
    public void addFirst(T item) {
        if (size == capacity) {
            doubleCapacity();
        }
        sentFrontIdx = getCircularIndex(sentFrontIdx, -1);
        arrayBase[getCircularIndex(sentFrontIdx, 1)] = item;
        size += 1;
    }

    /**
     *  Adds an item of type T to the back of the deque.
     * @param item
     */
    public void addLast(T item) {
        if (size == capacity) {
            doubleCapacity();
        }
        sentBackIdx = getCircularIndex(sentBackIdx, 1);
        arrayBase[getCircularIndex(sentBackIdx, -1)] = item;
        size += 1;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items in the deque.
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(arrayBase[getTrueIdx(i)]);
            System.out.print(" ");
        }
        System.out.print("\n");
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     * @return
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        if (size <= capacity / 4 && capacity >= 16) {
            halveCapacity();
        }

        sentFrontIdx = getCircularIndex(sentFrontIdx, 1);
        size -= 1;
        return arrayBase[sentFrontIdx];
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     * @return
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (size <= capacity / 4 && capacity >= 16) {
            halveCapacity();
        }
        sentBackIdx = getCircularIndex(sentBackIdx, -1);
        size -= 1;
        return arrayBase[sentBackIdx];
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     * @param index
     * @return
     */
    public T get(int index) {
        return arrayBase[getTrueIdx(index)];
    }

}
