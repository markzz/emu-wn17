/*
 * Mark Weiman
 * https://github.com/markzz/emu-wn17/blob/COSC311/HW-02-28/src/Main.java
 * COSC 311
 * HW 02/28
 * WINTER 2017
 */

public class Main {
    private static class LList {
        private LList next = null;
        private int data;

        public LList() {
            this.next = null;
        }
    }

    public static LList reverse(LList list) {
        if (list == null || list.next == null) {
            return list;
        }

        LList rev = reverse(list.next);
        LList curr = rev;
        while (curr.next != null) {
            curr = curr.next;
        }

        curr.next = list;
        list.next = null;

        return rev;
    }

    public static void main(String[] argv) {
        LList list = new LList();
        list.data = 1;
        list.next = new LList();
        list.next.data = 2;
        list.next.next = new LList();
        list.next.next.data = 3;

        for (LList tmp = list; tmp != null; tmp = tmp.next) {
            System.out.print(tmp.data + " ");
        }
        System.out.println();

        LList rev = reverse(list);

        for (LList tmp = rev; tmp != null; tmp = tmp.next) {
            System.out.print(tmp.data + " ");
        }
        System.out.println();
    }
}