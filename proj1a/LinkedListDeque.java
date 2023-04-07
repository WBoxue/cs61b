public class LinkedListDeque<T> {
    public class Node {
        public Node prev;
        public T item;
        public Node next;

        public Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }

        public Node(Node p, Node n) {
            prev = p;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        Node node = new Node(sentinel.next.prev, item, sentinel.next);
        sentinel.next.prev.next = node;
        sentinel.next.prev = node;
        sentinel.next = node;
        size += 1;
    }

    public void addLast(T item) {
        Node node = new Node(sentinel.next.prev, item, sentinel.next);
        sentinel.next.prev.next = node;
        sentinel.next.prev = node;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node p = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(p.item + " ");
            p = p.next;
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T item = sentinel.next.item;
        sentinel.next.next.prev = sentinel.next.prev;
        sentinel.next.prev.next = sentinel.next.next;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T item = sentinel.next.prev.item;
        sentinel.next.prev.prev.next = sentinel.next;
        sentinel.next.prev = sentinel.next.prev.prev;
        size -= 1;

        return item;
    }

    public T get(int index) {
        Node p = sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    public T getRecursive(int index) {
        Node p = sentinel.next;
        return recursiveHelper(p, index);
    }

    private T recursiveHelper(Node p, int index) {
        if (index == 0) {
            return p.item;
        }
        return recursiveHelper(p.next, index --);
    }
}
