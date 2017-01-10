/*
 * Mark Weiman
 * https://github.com/markzz/emu-wn17/blob/COSC311/HW-01-05/src/PrefixSums.java
 * COSC 311
 * HW 01/05
 * WINTER 2017
 */

public class PrefixSums {
    public static void validate_array(int[] arr) {
        if (arr.length == 0 || arr[0] != 0) {
            System.err.println("Array malformed, bailing...");
            System.exit(0);
        }
    }

    public static void prefix_sum(int[] arr) {
        int i;

        for (i = 1; i < arr.length; i++) {
            arr[i] = arr[i] + arr[i - 1];
        }
    }

    public static void print_array(String prefix, int[] arr) {
        System.out.printf("%-7s ", prefix);

        if (arr.length == 0) return;
       
        for (int i : arr) {
            System.out.printf("%3d ", i);
        }

        System.out.println("");
    }

    public static void main(String[] argv) {
        int[] arr = {0, 2, 4, 6, 1};
        validate_array(arr);
        print_array("data", arr);
        prefix_sum(arr);
        print_array("prefSum", arr);
    }
}
