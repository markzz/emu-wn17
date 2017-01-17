/*
 * Mark Weiman
 * COSC 221 0
 * WINTER 2017
 * Lab 02
 */

import java.util.Scanner;

public class Convert {
    public static Scanner stdin = new Scanner(System.in);

    public static int show_prompt() {
        int ret;

        System.out.println("1. Convert decimal to binary");
        System.out.println("2. Convert binary to decimal");
        System.out.println("3. Quit");
        System.out.print("\nMake selection: ");

        ret = stdin.nextInt();
        return ret;
    }

    public static String to_binary(int i) {
        int j, k;
        String ret = "";

        ret += i < 0 ? "1" : "0";
        k = i < 0 ? i + 128 : i;
        System.out.println(k);

        for (j = 6; j >= 0; j--) {
            ret += k >= 1 << j ? "1" : "0";
            k -= k >= 1 << j ? 1 << j : 0;
        }

        return ret;
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
    
    public static void main(String[] argv) {
        int selection = 0;
        int dec;
        String bin;

        while (true) {
            selection = show_prompt();

            if (selection == 1) {
                System.out.print("Enter decimal number: ");
                dec = stdin.nextInt();
                System.out.printf("%d -> %s\n\n", dec, to_binary(dec));
            } else if (selection == 2) {
                System.out.print("Enter binary number: ");
                bin = stdin.next();
                System.out.printf("%s -> %d\n\n", bin, to_dec(bin));
            } else {
                System.out.println("Good bye.");
                break;
            }
        }
    }
}

/*
OUTPUT:
1. Convert decimal to binary
2. Convert binary to decimal
3. Quit

Make selection: 1
Enter decimal number: 11111111
11111111
11111111 -> 01111111

1. Convert decimal to binary
2. Convert binary to decimal
3. Quit

Make selection: 1
Enter decimal number: -1
127
-1 -> 11111111

1. Convert decimal to binary
2. Convert binary to decimal
3. Quit

Make selection: 1
Enter decimal number: -128
0
-128 -> 10000000

1. Convert decimal to binary
2. Convert binary to decimal
3. Quit

Make selection: 1
Enter decimal number: 15
15
15 -> 00001111

1. Convert decimal to binary
2. Convert binary to decimal
3. Quit

Make selection: 2
Enter binary number: 11111111
11111111 -> -1

1. Convert decimal to binary
2. Convert binary to decimal
3. Quit

Make selection: 2
Enter binary number: 00001111
00001111 -> 15

1. Convert decimal to binary
2. Convert binary to decimal
3. Quit

Make selection: 2
Enter binary number: 10000000
10000000 -> -128

1. Convert decimal to binary
2. Convert binary to decimal
3. Quit

Make selection: 3
Good bye.
*/
