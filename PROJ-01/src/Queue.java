/*
 * Mark Weiman
 * https://github.com/markzz/emu-wn17/blob/COSC311/PROJ-01/src/Queue.java
 * COSC 311
 * PROJECT 01
 * WINTER 2017
 */

public class Queue {
    private Node head;
    private Node tail;
    private long size;

    private class Node {
        private Node next;
        private Node prev;
        private long data;

        public Node(long d, Node n, Node p) {
            this.data = d;
            this.next = n;
            this.prev = p;
        }
    }

    public Queue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void queue(long d) {
        if (this.head == null) {
            this.head = new Node(d, null, null);
            this.tail = this.head;
        } else {
            this.tail = new Node(d, null, this.tail);
            this.tail.prev.next = this.tail;
        }

        this.size++;
    }

    public long dequeue() {
        if (this.size == 0) {
            return 0;
        }

        Node tmp = this.head;
        this.head = this.head.next;
        this.size--;

        if (this.head != null) {
            this.head.prev = null;
        }

        if (this.size == 0) {
            this.tail = null;
            this.head = null;
        }

        return tmp.data;
    }

    public long get_size() {
        return this.size;
    }

    public long remove_dead(long curr_tick) {
        long ret = 0;
        for (Node tmp = this.head; tmp != null; tmp = tmp.next) {
            if (tmp.data < curr_tick) {
                if (tmp.prev != null) {
                    tmp.prev.next = tmp.next;
                }

                if (tmp.next != null) {
                    tmp.next.prev = tmp.prev;
                }

                ret++;
            }
        }

        return ret;
    }

    public long total_wait_time(long curr_tick) {
        long ret = 0;
        for (Node tmp = this.head; tmp != null; tmp = tmp.next) {
            ret += curr_tick - (tmp.data - 8);
        }

        return ret;
    }
}
