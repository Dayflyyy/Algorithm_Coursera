import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] rque;

    // N记录数组的大小
    private int N;

    // size记录实际上存储的数目
    private int size;


    // construct an empty randomized queue
    public RandomizedQueue() {

        N = 2;
        size = 0;
        rque = (Item[]) new Object[N];

    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // 调整数组尺寸
    private void resize(int capacity) {
        Item[] que = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            que[i] = rque[i];
        }
        rque = que;
        N = capacity;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size + 1 >= N) {
            resize(2 * N);
        }
        rque[size] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        if (size - 1 <= N / 4) {
            resize(N / 2);
        }
        int index = StdRandom.uniformInt(size);
        Item deid = rque[index];
        rque[index] = rque[size - 1];
        rque[--size] = null;
        return deid;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        return rque[StdRandom.uniformInt(size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator<Item>();
    }

    private class RandomizedQueueIterator<Item> implements Iterator<Item> {

        private Item[] iter = (Item[]) rque;
        private int itsize=size;

        public boolean hasNext() {
            return itsize > 0;

        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int index = StdRandom.uniformInt(itsize);
            Item item = iter[index];
            iter[index] = iter[itsize - 1];
            itsize -= 1;
            iter[itsize] = null;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("remove");
        }

    }

    // unit testing (required)
    public static void main(String[] args) {



        RandomizedQueue<Integer> test = new RandomizedQueue<Integer>();
        StdOut.print("___________test enqueue");
        test.enqueue(1);
        test.enqueue(2);
        test.enqueue(3);
        test.enqueue(4);
        test.enqueue(5);
        test.enqueue(6);
        test.enqueue(7);
        test.enqueue(8);
        test.enqueue(9);
        test.enqueue(10);
        StdOut.print("___________test iterator");
        for (Integer I : test) {
            StdOut.print(I);
        }
        StdOut.print("___________test dequeue");
        StdOut.print(test.dequeue());
        StdOut.print(test.dequeue());
        StdOut.print(test.dequeue());
        StdOut.print(test.dequeue());
        StdOut.print(test.dequeue());
        StdOut.print(test.dequeue());
        StdOut.print(test.dequeue());
        StdOut.print(test.isEmpty());
        StdOut.print(test.size());
        StdOut.print(test.sample());
        StdOut.print(test.sample());
        StdOut.print(test.sample());
        StdOut.print("___________test error");
        try {
            test.enqueue(null);

        } catch (IllegalAccessError e) {
            StdOut.print(e);
        }
        try {
            test = new RandomizedQueue<Integer>();
            test.dequeue();

        } catch (NoSuchElementException e) {
            StdOut.print(e);
        }
        try {
            test = new RandomizedQueue<Integer>();
            test.sample();
        } catch (NoSuchElementException e) {
            StdOut.print(e);
        }
        try {
            test = new RandomizedQueue<Integer>();
            test.iterator().remove();
        } catch (UnsupportedOperationException e) {
            StdOut.print(e);
        }
    }
}
