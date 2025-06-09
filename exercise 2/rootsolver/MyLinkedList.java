package hus.oop.rootsolver;

public class MyLinkedList {
    private MyNode top;
    private int size;
    /**
     * Khởi tạo dữ liệu mặc định.
     */
    public MyLinkedList() {
        top = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public double get(int index) {
        return getNodeByIndex(index).data;
    }

    public void set(double data, int index) {
        getNodeByIndex(index).data = data;
    }

    public void add(double data) {
        insert(data, size);
    }

    public void insert(double data, int index) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        MyNode newNode = new MyNode(data);
        if (index == 0) {
            newNode.next = top;
            if (top != null) top.previous = newNode;
            top = newNode;
        } else {
            MyNode prev = getNodeByIndex(index - 1);
            newNode.next = prev.next;
            newNode.previous = prev;
            if (prev.next != null) prev.next.previous = newNode;
            prev.next = newNode;
        }
        size++;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        MyNode target = getNodeByIndex(index);
        if (target.previous != null) target.previous.next = target.next;
        else top = target.next;
        if (target.next != null) target.next.previous = target.previous;
        size--;
    }

    private MyNode getNodeByIndex(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        MyNode current = top;
        for (int i = 0; i < index; i++) current = current.next;
        return current;
    }
}
