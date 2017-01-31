/*
 * Mark Weiman
 * https://github.com/markzz/emu-wn17/blob/COSC311/HW-01-23/src/Main.java
 * COSC 311
 * HW 01/23
 * WINTER 2017
 */

public class Main {
    public static double solve(String str) {
        double d1, d2;
        Stack s = new Stack();
        String[] str_arr = str.split(" ");
        double to_push = 0;

        for (String a : str_arr) {
            /* this is where I wish I had macros */
            switch (a) {
                case "+":
                    d2 = s.pop();
                    d1 = s.pop();
                    to_push = d1 + d2;
                    break;
                case "-":
                    d2 = s.pop();
                    d1 = s.pop();
                    to_push = d1 - d2;
                    break;
                case "*":
                    d2 = s.pop();
                    d1 = s.pop();
                    to_push = d1 * d2;
                    break;
                case "/":
                    d2 = s.pop();
                    d1 = s.pop();
                    to_push = d1 / d2;
                    break;
                default:
                    to_push = Double.parseDouble(a);
            }

            s.push(to_push);
        }

        return s.pop();
    }

    public static void main(String[] argv) {
        String data[] = {"3.0 2.0 4.0 + *", "3.0 2.0 4.0 + 5.0 + *", "3.0 2.0 4.0 5.0 + * +"};

        for (String d : data) {
            System.out.printf("%-24s -> %.01f\n", d, solve(d));
        }
    }
}
