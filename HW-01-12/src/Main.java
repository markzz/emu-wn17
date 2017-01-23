/*
 * Mark Weiman
 * https://github.com/markzz/emu-wn17/blob/COSC311/HW-01-12/Main.java
 * COSC 311
 * HW 01/10
 * WINTER 2017
 */

public class Main {
    public static void main(String[] argv) {
        int[] array = {0, 2, 4, 6, 1};
        int k = 4;

        LinkedList list = new LinkedList(array);
        System.out.printf("original list: %s\n", list.to_string());
        list.rm(k);
        System.out.printf("modified list: %s\n", list.to_string());
    }
}
