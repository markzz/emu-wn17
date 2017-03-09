import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void generate_random_graph(int size, int max_val) throws FileNotFoundException {
        Graph ret = new Graph(size);
        Random ran = new Random();
        int tmp;

        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                if (i != j) {
                    tmp = ran.nextInt(max_val + 1);
                    if (tmp == 0) {
                        ret.set_val(i, j, Integer.MAX_VALUE);
                    } else {
                        ret.set_val(i, j, tmp);
                    }
                }
            }
        }

        stream_out(new FileOutputStream(String.format("graph-%d", size)), ret);
        System.out.printf("Wrote to graph-%d...\n", size);
    }

    public static Graph parse_file(String filename) throws FileNotFoundException {
        Scanner fin = new Scanner(new FileReader(filename));
        Graph ret;
        int len, tmp;

        len = fin.nextInt();
        ret = new Graph(len);

        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= len; j++) {
                tmp = fin.nextInt();

                if (i != j && tmp == 0) {
                    tmp = Integer.MAX_VALUE;
                }

                ret.set_val(i, j, tmp);
            }
        }

        return ret;
    }

    public static void stream_out(OutputStream os, Graph graph) {
        PrintWriter pw = new PrintWriter(os);
        pw.println(graph.get_size());
        pw.print(graph.to_string());
        pw.flush();
        pw.close();
    }

    public static long time_test(Graph g) throws FileNotFoundException {
        long init_time = System.currentTimeMillis();
        g.shortest_paths_graph();
        long end_time = System.currentTimeMillis();
        return end_time - init_time;
    }

    public static void main(String[] argv) throws FileNotFoundException {
        /* create graphs for time tests, this will take a long time */
        /*generate_random_graph(100, 10);
        generate_random_graph(200, 10);
        generate_random_graph(500, 10);
        generate_random_graph(1000, 10);*/

        Graph g1 = parse_file("../files/file1");
        Graph g2 = parse_file("../files/file2");

        Graph h1 = g1.shortest_paths_graph();
        Graph h2 = g2.shortest_paths_graph();

        stream_out(new FileOutputStream("output1"), h1);
        stream_out(new FileOutputStream("output2"), h2);

        long l1 = time_test(parse_file("../graph-100"));
        long l2 = time_test(parse_file("../graph-200"));
        long l3 = time_test(parse_file("../graph-500"));
        long l4 = time_test(parse_file("../graph-1000"));

        System.out.println("+------+-----------+");
        System.out.println("| Size | Time (ms) |");
        System.out.println("+------+-----------+");
        System.out.printf("| %4d | %9d |\n", 100, l1);
        System.out.printf("| %4d | %9d |\n", 200, l2);
        System.out.printf("| %4d | %9d |\n", 500, l3);
        System.out.printf("| %4d | %9d |\n", 1000, l4);
        System.out.println("+------+-----------+");
    }
}
