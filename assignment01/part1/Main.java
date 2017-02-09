import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void generate_random_relation(int size) throws FileNotFoundException {
        Relation ret = new Relation(size);
        Random ran = new Random();

        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                if (ran.nextBoolean()) {
                    ret.add(i, j);
                }
            }
        }

        stream_out(new FileOutputStream(String.format("matrix-%d", size)), ret);
    }

    public static ArrayList<Integer[]> parse_file(String filename) throws FileNotFoundException {
        Scanner fin = new Scanner(new FileReader(filename));
        int len;
        Integer[] tmp;
        ArrayList<Integer[]> ret = new ArrayList<>();

        len = fin.nextInt();
        ret.add(new Integer[]{len, null});

        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= len; j++) {
                if (fin.nextInt() == 1) {
                    tmp = new Integer[]{i, j};
                    ret.add(tmp);
                }
            }
        }

        return ret;
    }

    public static Relation setup_relation(ArrayList<Integer[]> parsed_data) {
        Relation ret = null;

        for (Integer[] i : parsed_data) {
            if (i[1] == null) {
                ret = new Relation(i[0]);
                continue;
            }

            ret.add(i[0], i[1]);
        }

        return ret;
    }

    public static void stream_out(OutputStream os, Relation rel) {
        PrintWriter pw = new PrintWriter(os);
        pw.println(rel.get_size());
        pw.print(rel.to_string());
        pw.flush();
        pw.close();
    }

    public static void time_test(Relation relation) throws FileNotFoundException {
        System.out.printf("STARTING TIME TEST ON RELATION OF SIZE %d\n", relation.get_size());
        long init_time = System.currentTimeMillis();
        relation.transitive_closure();
        long end_time = System.currentTimeMillis();
        System.out.printf("Operation took %dms\n\n", end_time - init_time);
    }

    /* main function */
    public static void main(String[] argv) throws FileNotFoundException {
        /* create matrices for time tests, this will take more than 15 minutes on a slow machine */
        /*generate_random_relation(100);
        generate_random_relation(200);
        generate_random_relation(600);
        generate_random_relation(1000);
        */

        /* store and parse data from input files */
        ArrayList<Integer[]> al1 = parse_file("../Files/file1");
        ArrayList<Integer[]> al2 = parse_file("../Files/file2");
        ArrayList<Integer[]> al3 = parse_file("../Files/file3");
        ArrayList<Integer[]> al4 = parse_file("matrix-100");
        ArrayList<Integer[]> al5 = parse_file("matrix-200");
        ArrayList<Integer[]> al6 = parse_file("matrix-500");
        ArrayList<Integer[]> al7 = parse_file("matrix-1000");

        /* set up relations based on parsed data */
        Relation rel1 = setup_relation(al1);
        Relation rel2 = setup_relation(al2);
        Relation rel3 = setup_relation(al3);
        Relation rel4 = setup_relation(al4);
        Relation rel5 = setup_relation(al5);
        Relation rel6 = setup_relation(al6);
        Relation rel7 = setup_relation(al7);

        /* output transitive closures to out files */
        stream_out(new FileOutputStream("output1"), rel1.transitive_closure());
        stream_out(new FileOutputStream("output2"), rel2.transitive_closure());
        stream_out(new FileOutputStream("output3"), rel3.transitive_closure());

        /* perform Worshall's algorithm time tests */
        time_test(rel4);
        time_test(rel5);
        time_test(rel6);
        time_test(rel7);
    }
}
