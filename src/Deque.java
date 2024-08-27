import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;


// 双向队列，维护头指针和尾指针，使用bilinklist实现 双向链表
public class Deque<Item> implements Iterable<Item> {


    // 链表节点定义
    private class LinkNode {
        Item item;
        LinkNode next;
        LinkNode pre;

        public LinkNode(Item ite) {
            item = ite;
            next = pre = null;
        }
    }

    // 头尾引用
    private LinkNode first;
    private LinkNode last;
    // 队列长度
    private int size;

    // construct an empty deque
    public Deque() {
        first = last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {

        if (item == null) {
            throw new IllegalArgumentException();
        }
        LinkNode newnode = new LinkNode(item);
        if (first == null) {
            first = last = newnode;
            size++;
            return;
        }
        LinkNode oldfirst = first;
        first = newnode;
        newnode.next = oldfirst;
        oldfirst.pre = newnode;
        size++;
        if (size() == 1) {
            last = newnode;
        }
    }

    // add the item to the back
    public void addLast(Item item) {

        if (item == null) {
            throw new IllegalArgumentException();
        }
        LinkNode newnode = new LinkNode(item);
        if (last == null) {
            first = last = newnode;
            size++;
            return;
        }
        LinkNode oldlast = last;
        last = newnode;
        oldlast.next = newnode;
        newnode.pre = oldlast;
        size++;
        if (size() == 1) {
            first = newnode;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item oldfirst = first.item;
        first = first.next;
        if (first != null) {
            first.pre = null;

        }
        size--;
        return oldfirst;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item oldlast = last.item;

        last = last.pre;
        if (last != null) {
            last.next = null;
        }

        size--;
        return oldlast;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator<Item>();
    }

    private class DequeIterator<Item> implements Iterator<Item> {
        private LinkNode current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = (Item) current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("remove");
        }

    }

    // unit testing (required)
    public static void main(String[] args) {

        Deque<Integer> test = new Deque<Integer>();

        StdOut.print("add test");
        StdOut.print("add first 1 2 3");
        test.addLast(1);
        test.addFirst(1);
        test.addFirst(2);
        test.addFirst(3);
        StdOut.print("add last 4 5 6");
        test.addLast(4);
        test.addLast(5);
        test.addLast(6);
        StdOut.print("size after add:" + test.size());
        StdOut.print("remove first and last");
        StdOut.print("first remove " + test.removeFirst());
        StdOut.print("last remove " + test.removeLast());
        StdOut.print("size after remove:" + test.size());
        StdOut.print("iterator test");
        Iterator<Integer> it = test.iterator();
        while (it.hasNext()) {
            StdOut.print(it.next());
            it.remove();
        }
        StdOut.print("size and isEmpty test");
        StdOut.print(test.size());
        StdOut.print(test.isEmpty());
        StdOut.print("error test");
        try {
            test = new Deque<Integer>();
            test.addFirst(null);

        } catch (IllegalAccessError e) {
            StdOut.print(e);
        }
        try {
            test = new Deque<Integer>();
            test.removeFirst();
        } catch (NoSuchElementException e) {
            StdOut.print(e);
        }
        try {
            test = new Deque<Integer>();
            test.removeLast();
        } catch (NoSuchElementException e) {
            StdOut.print(e);
        }
        try {
            test = new Deque<Integer>();
            test.iterator().remove();
        } catch (UnsupportedOperationException e) {
            StdOut.print(e);

        }


    }

}
