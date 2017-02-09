import java.io.*;
import java.util.*;

public class Main {
    public static int best_choice(Relation reltc) {
        int[] counts = get_line_counts(reltc);
        int curr_max = 0;
        int ret = 0;

        for (int i = 0; i < counts.length; i++) {
            if (curr_max < counts[i]) {
                curr_max = counts[i];
                ret = i + 1;
            }
        }

        return ret;
    }

    public static boolean can_reach_anyone(Relation reltc) {
        int[] counts = get_line_counts(reltc);

        for (int i : counts) {
            if (i == reltc.get_size()) {
                return true;
            }
        }

        return false;
    }

    public static int[] get_line_counts(Relation reltc) {
        String[] matrix_lines = reltc.to_string().split("\n");
        int counts[] = new int[reltc.get_size()];

        for (int i = 0; i < matrix_lines.length ; i++) {
            char[] line = matrix_lines[i].replaceAll(" ", "").toCharArray();
            for (char c : line) {
                if (c == 49) {
                    counts[i]++;
                }
            }
        }

        return counts;
    }

    public static int[] minimal_set_of_persons(Relation reltc) {
        int max = best_choice(reltc);
        ArrayList<Integer> al = new ArrayList<>();
        Set<Integer> ret_set = new HashSet<>();

        String[] matrix = reltc.to_string().split("\n");

        char[] best_row = matrix[max-1].replaceAll(" ", "").toCharArray();
        for (int i = 1; i <= reltc.get_size(); i++) {
            if (best_row[i-1] == 48) {
                al.add(i);
            }
        }

        int[][] ia = new int[al.size()][];
        for (int i = 0; i < ia.length; i++) {
            ia[i] = rows_containing(al.get(i), reltc);
        }

        for (int i = 0; i < ia.length; i++) {

        }

        return null;
    }

    public static Relation parse_file(String filename) throws FileNotFoundException {
        Scanner fin = new Scanner(new FileInputStream(filename));
        String[] first_line, tmp;

        first_line = fin.nextLine().split(" ");
        Relation ret = new Relation(Integer.parseInt(first_line[0]));

        for (int i = 0; i < Integer.parseInt(first_line[1]); i++) {
            tmp = fin.nextLine().split(" ");
            ret.add(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]));
        }

        return ret;
    }

    public static int[] rows_containing(int needle, Relation rel) {
        ArrayList<Integer> al = new ArrayList<>();

        String[] sa = rel.to_string().split("\n");

        for (int i = 0; i < sa.length; i++) {
            if (sa[i].charAt(needle*2-2) == 49) {
                al.add(i+1);
            }
        }

        int[] ret = new int[al.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = al.get(i);
        }

        return ret;
    }

    public static void stream_percentages(OutputStream os, Relation reltc) {
        PrintWriter pw = new PrintWriter(os);
        int[] counts = get_line_counts(reltc);

        for (int i = 0; i < counts.length; i++) {
            float percentage = (float)counts[i] / reltc.get_size() * 100;
            pw.printf("Person %d:\t%.02f%%\n", i + 1, percentage);
        }

        pw.flush();
    }

    public static void main(String[] argv) throws FileNotFoundException {
        Relation rel = parse_file("../Files/file4");
        Relation tc = rel.transitive_closure();

        /* I think it's safe to assume everyone has given themselves the information */
        for (int i = 1; i <= tc.get_size(); i++) {
            tc.add(i, i);
        }

        System.out.printf("%s\n\n", tc.to_string());

        stream_percentages(System.out, tc);
        System.out.println();

        System.out.printf("Person %d is the best choice for maximum spread.\n", best_choice(tc));

        System.out.printf("There %s a person from whom information can spread to everyone.\n", can_reach_anyone(tc) ? "exists" : "does not exist");

        minimal_set_of_persons(tc);
    }
}
