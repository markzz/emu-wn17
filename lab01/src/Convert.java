/*
 * Mark Weiman
 * COSC 221 0
 * WINTER 2017
 * Lab 01
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
        int j;
        String ret = "";

        for (j = 7; j >= 0; j--) {
            ret += i >= 1 << j ? "1" : "0";
            i -= i >= 1 << j ? 1 << j : 0;
        }

        return ret;
    }

    public static int to_dec(String s) {
        int ret = 0;
        int i;
        char c;

        for (i = 0; i < 8; i++) {
            c = s.charAt(i);
            ret += c == 49 ? 1 << (7-i) : 0;
        }

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
EXAMPLE OUTPUT:

Convert decimal to binary
2. Convert binary to decimal
3. Quit

Make selection: 1
Enter decimal number: 172
172 -> 10101100

1. Convert decimal to binary
2. Convert binary to decimal
3. Quit

Make selection: 1
Enter decimal number: 78
78 -> 01001110

1. Convert decimal to binary
2. Convert binary to decimal
3. Quit

Make selection: 2
Enter binary number: 01011010
01011010 -> 90

1. Convert decimal to binary
2. Convert binary to decimal
3. Quit

Make selection: 2
Enter binary number: 00111100
00111100 -> 60

1. Convert decimal to binary
2. Convert binary to decimal
3. Quit

Make selection: 3
Good bye.
*/
