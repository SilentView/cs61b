public class LinkedListDeque<T> {

    private class Node {
        private T item;
        private Node prev;
        private Node next;

        Node() {
            item = null;
            prev = null;
            next = null;
        }

        Node(T x, Node prevNode, Node nextNode) {
            item = x;
            prev = prevNode;
            next = nextNode;
        }
    }

    /**
     * Sentinel Node at the front
     */
    private Node sentFront;
    /**
     * Sentinel Node at the back
     */
    private Node sentBack;
    private int size;

    /**
     * Creates an empty linked list deque.
     */
    public LinkedListDeque() {
        size = 0;
        sentBack = new Node();
        sentFront = new Node();

        sentFront.next = sentBack;
        sentBack.prev = sentFront;
    }

    /**
     *  Adds an item of type T to the front of the deque.
     * @param item
     */
    public void addFirst(T item) {
        Node newNode = new Node(item, sentFront, sentFront.next);
        newNode.prev.next = newNode;
        newNode.next.prev = newNode;
        size += 1;
    }

    /**
     *  Adds an item of type T to the back of the deque.
     * @param item
     */
    public void addLast(T item) {
        Node newNode = new Node(item, sentBack.prev, sentBack);
        newNode.prev.next = newNode;
        newNode.next.prev = newNode;
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
        Node p = sentFront;
        for (int i = 0; i < size; i++) {
            p = p.next;
            System.out.print(p.item);
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
        Node toRemove = sentFront.next;
        toRemove.prev.next = toRemove.next;
        toRemove.next.prev = sentFront;
        size -= 1;
        return toRemove.item;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     * @return
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node toRemove = sentBack.prev;
        toRemove.prev.next = toRemove.next;
        toRemove.next.prev = toRemove.prev;
        size -= 1;
        return toRemove.item;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     * @param index
     * @return
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        // judge the iteration should start from sentFront or sentBack
        if (index >= size / 2) {
            //start from sentBack
            Node p = sentBack;
            //iteration time: [(size - 1) - index] + 1
            for (int i = 0; i < size - index; i++) {
                p = p.prev;
            }
            return p.item;
        } else {
            //start from sentFront
            Node p = sentFront;
            //iteration time: [index - 0] + 1
            for (int i = 0; i < index + 1; i++) {
                p = p.next;
            }
            return p.item;
        }
    }


    /**
     * Same as get, but uses recursion.
     * @param index
     * @return
     */
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getFromRecursive(sentFront.next, index);
    }

    /**
     * Helper function for getRecursive. Use starting node as input
     * get the number index node's item counting from starting node
     * If start is null or index is out of range, return null.
     */
    private T getFromRecursive(Node start, int index) {
        if (start == null) {
            return null;
        }
        if (index == 0) {
            return start.item;
        } else {
            return getFromRecursive(start.next, index - 1);
        }
    }

}
