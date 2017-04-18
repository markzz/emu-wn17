/*
 * Mark Weiman
 * https://github.com/markzz/emu-wn17/blob/COSC311/PROJ-02/src/Main.java
 * COSC 311
 * PROJECT 02
 * WINTER 2017
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] argv) throws IOException, FileNotFoundException {
        FileHashTable fht = new FileHashTable("database.dat");
        String line;
        char[] chars;
        int last;

        Scanner s = new Scanner(new FileInputStream("input.dat"));

        while (s.hasNextLine()) {
            line = s.nextLine();
            chars = line.toCharArray();
            if (line.contains("input")) {
                last = line.lastIndexOf("\"");
                fht.insert(line.substring(7, last));
            } else if (line.contains("delete")) {
                last = line.lastIndexOf("\"");
                fht.delete(line.substring(8, last));
            } else if (line.contains("printTable")) {
                fht.print_table();
                System.out.println();
            }
        }
    }
}
