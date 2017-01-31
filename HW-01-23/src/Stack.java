/*
 * Mark Weiman
 * https://github.com/markzz/emu-wn17/blob/COSC311/HW-01-23/src/Stack.java
 * COSC 311
 * HW 01/23
 * WINTER 2017
 */

/* only handles doubles */
public class Stack {
    private Node head;

    private class Node {
        private double data;
        private Node next;

        public Node(double d, Node n) {
            this.data = d;
            this.next = n;
        }
    }

    public Stack() {
        this.head = null;
    }

    public void push(double d) {
        this.head = new Node(d, this.head);
    }

    public double pop() {
        double ret = this.head.data;
        this.head = this.head.next;
        return ret;
    }
}
