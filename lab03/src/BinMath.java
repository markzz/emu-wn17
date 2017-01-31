/*
 * Mark Weiman
 * COSC 221 0
 * WINTER 2017
 * Lab 03
 */

import java.util.Scanner;

public class BinMath {
    public static Scanner stdin = new Scanner(System.in);

    public static int show_prompt() {
        int ret;

        System.out.println("1. Add two binary numbers");
        System.out.println("2. Subtract two binary numbers");
        System.out.println("3. Quit");
        System.out.print("\nMake selection: ");

        ret = stdin.nextInt();
        return ret;
    }

    public static void do_math(char operator) {
        String op1, op2, res;

        System.out.print("Enter first operand (binary): ");
        op1 = stdin.next();
        System.out.print("Enter second operand (binary): ");
        op2 = stdin.next();
        res = operator == '+' ? add(op1, op2) : sub(op1, op2);
        System.out.printf("Binary:  %s %c %s = %s\n", op1, operator, op2, res);
        System.out.printf("Decimal: % 8d %c % 8d = % 8d\n\n", to_dec(op1), operator, to_dec(op2), to_dec(res));
    }

    public static int to_dec(String s) {
        int ret = 0;
        int i;
        char c;
        boolean is_neg;

        is_neg = s.charAt(0) == 49;

        for (i = 1; i < 8; i++) {
            c = s.charAt(i);
            ret += c == 49 ? 1 << (7-i) : 0;
        }

        ret = is_neg ? ret-128 : ret;

        return ret;
    }

    public static String add(String s1, String s2) {
        char[] carr = {48, 48, 48, 48, 48, 48, 48, 48};
        int carry = 0;

        for (int i = 7; i >= 0; i--) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (carry == 0) {
                    carr[i]++;
                }
            } else if (s1.charAt(i) == 49) {
                if (carry == 1) {
                    carr[i]++;
                } else {
                    carry++;
                }
                
            } else if (s1.charAt(i) == 48) {
                if (carry == 1) {
                    carr[i]++;
                    carry--;
                }
            }
        }

        return String.valueOf(carr) + (s1.charAt(0) == s2.charAt(0) && s1.charAt(0) != carr[0] ? " (overflow)" : "");
    }

    public static String sub(String s1, String s2) {
        String tmp;

        /* flip bits of s2 */
        char[] s2_chars = s2.toCharArray();
        for (int i = 0; i < 8; i++) {
            s2_chars[i] = s2_chars[i] == 49 ? '0' : '1';
        }
        tmp = String.valueOf(s2_chars);
        
        /* add one */
        tmp = add(tmp, "00000001");

        return add(s1, tmp);
    }

    public static void main(String[] argv) {
        int selection = 0;
        String op1, op2, res;

        while (true) {
            selection = show_prompt();

            if (selection == 1) {
                do_math('+');
            } else if (selection == 2) {
                do_math('-');
            } else {
                System.out.println("Good bye.");
                break;
            }
        }
    }
}
