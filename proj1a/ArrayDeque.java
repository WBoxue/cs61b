public class ArrayDeque<T> {
    private int initSize = 8;
    private T[] array;
    private int head;
    private int rear;
    private int size;

    public ArrayDeque() {
        array = (T[]) new Object[initSize];
        head = initSize / 2;
        rear = initSize / 2;
        size = 0;
    }

    //Adds an item of type T to the front of the deque.
    public void addFirst(T item) {
        while (size + 1 >= array.length) {
            resizeMulTwo();
        }
        head = getMoved(head, -1);
        array[head] = item;
        size += 1;
    }

    //Adds an item of type T to the back of the deque.
    public void addLast(T item) {
        while (size + 1 >= array.length) {
            resizeMulTwo();
        }
        array[rear] = item;
        rear = getMoved(rear, 1);
        size += 1;
    }

    //Returns true if deque is empty, false otherwise.
    public boolean isEmpty() {
        return size == 0;
    }

    //Returns the number of items in the deque.
    public int size() {
        return size;
    }

    //Prints the items in the deque from first to last, separated by a space.
    public void printDeque() {
        int ptr = head;
        while (ptr != rear) {
            System.out.print(array[ptr] + " ");
            ptr = getMoved(ptr, 1);
        }
    }

    //Removes and returns the item at the front of the deque. If no such item exists, returns null.
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        while (size < array.length / 4 && array.length >= 16) {
            resizeDivFour();
        }
        T item = array[head];
        head = getMoved(head, 1);
        size -= 1;
        return item;
    }

    //Removes and returns the item at the back of the deque. If no such item exists, returns null.
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        while (size < array.length / 4 && array.length >= 16) {
            resizeDivFour();
        }
        rear = getMoved(rear, -1);
        T item = array[rear];
        size -= 1;
        return item;
    }

    //Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    // If no such item exists, returns null. Must not alter the deque!
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return array[(head + index) % array.length];
    }

    //move +1 or -1.
    private int getMoved(int ptr, int move) {
        assert move == 1 || move == -1;
        return (ptr + move + array.length) % array.length;
    }


    //resize array by 2
    private void resizeMulTwo() {
        T[] b = (T[]) new Object[array.length * 2];
        int ptr1 = head;
        int ptr2 = 0;
        while (ptr1 != rear) {
            b[ptr2] = array[ptr1];
            ptr1 = getMoved(ptr1, 1);
            ptr2 = getMoved(ptr2, 1);
        }
        array = b;
        head = 0;
        rear = size;
    }

    //resize array by 1/4
    private void resizeDivFour() {
        T[] b = (T[]) new Object[array.length / 4];
        int ptr1 = head;
        int ptr2 = 0;
        while (ptr1 != rear) {
            b[ptr2] = array[ptr1];
            ptr1 = getMoved(ptr1, 1);
            ptr2 = getMoved(ptr2, 1);
        }
        array = b;
        head = 0;
        rear = size;
    }
}
