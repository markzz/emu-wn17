/*
 * Mark Weiman
 * https://github.com/markzz/emu-wn17/blob/COSC311/HW-01-12/LinkedList.java
 * COSC 311
 * HW 01/12
 * WINTER 2017
 */

public class LinkedList {
    private Node first;
    private int length;

    private class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public LinkedList(int[] arr) {
        Node last = null;
        this.length = 0;

        for (int i : arr) {
            if (last == null) {
                last = new Node(i, null);
                this.first = last;
            } else {
                last.next = new Node(i, null);
                last = last.next;
            }

            this.length++;
        }
    }

    public void rm(int index) {
        int i;
        Node prev = this.first;

        if (index < 0 || index >= this.length) return;

        index = this.length - index - 1;
        
        if (index == 0) {
            this.first = prev.next;
            return;
        }

        for (i = 1; i <= index; i++) {
            prev = prev.next;
        }

        prev.next = prev.next.next;
    }

    public String to_string() {
        Node n = this.first;
        String ret = "";

        while (n != null) {
            ret += String.format("%3d ", n.data);
            n = n.next;
        }

        return ret;
    }
}
