/**
 * Mark Weiman
 * https://github.com/markzz/emu-wn17/blob/COSC311/HW-03-02/src/Main.java
 * COSC 311
 * HW 03/02
 * WINTER 2017
 */

import java.util.Arrays;

public class Main {
    public static String array_to_string(int[] i) {
        if (i == null || i.length == 0) return "[]";

        String ret = "[";

        for (int j : i) {
            ret += String.format(" %d,", j);
        }

        ret = ret.substring(0, ret.length()-1);
        return ret + " ]";
    }

    public static int[] zip(int[] i, int[] j) {
        int[] k;
        
        if (i.length == 0 || j.length == 0) {
            return null;
        }

        int ret[] = new int[Integer.min(i.length, j.length) * 2];

        k = zip(Arrays.copyOfRange(i, 1, i.length), Arrays.copyOfRange(j, 1, j.length));

        System.arraycopy(i, 0, ret, 0, 1);
        System.arraycopy(j, 0, ret, 1, 1);

        if (k != null) {
            System.arraycopy(k, 0, ret, 2, k.length);
        }

        return ret;
    }
    
    public static void main(String[] argv) {
        int[] i = {1, 2, 3, 4, 5};
        int[] j = {9, 8, 7, 6, 5, 6, 7};

        int[] k = zip(i, j);

        System.out.println("ORIGINAL (1): " + array_to_string(i));
        System.out.println("ORIGINAL (2): " + array_to_string(j));
        System.out.println("ZIPPED:       " + array_to_string(k));
    }
}
