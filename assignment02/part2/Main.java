import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static String interpret(int[][] i) {
        String ret = "";
        String tmp;
        int path_tmp;

        for (int j = 0; j < i[0].length; j++) {
            tmp = String.format("%d\n", j + 1);
            path_tmp = j;
            while (i[1][path_tmp] != path_tmp) {
                path_tmp = i[1][path_tmp];
                tmp = String.format("%d - %s", path_tmp+1, tmp);
            }

            tmp = String.format("[%3d]  %s", i[0][j], tmp);
            ret += tmp;
        }

        return ret;
    }

    public static int parse_file(String filename, Graph g) throws FileNotFoundException {
        Scanner fin = new Scanner(new FileReader(filename));
        int len, tmp;

        len = fin.nextInt();
        g.setup(len);

        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= len; j++) {
                tmp = fin.nextInt();

                if (i != j && tmp == 0) {
                    tmp = Integer.MAX_VALUE;
                }

                g.set_val(i, j, tmp);
            }
        }

        return fin.nextInt();
    }

    public static Graph parse_file2(String filename) throws FileNotFoundException {
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

    public static void stream_out(OutputStream os, String s) {
        PrintWriter pw = new PrintWriter(os);
        pw.println(s);
        pw.flush();
        pw.close();
    }
    public static long time_test(Graph g, int vertex) throws FileNotFoundException {
        long init_time = System.currentTimeMillis();
        g.dijkstra(vertex);
        long end_time = System.currentTimeMillis();
        return end_time - init_time;
    }


    public static void main(String[] argv) throws FileNotFoundException {
        Graph g = new Graph();
        int i = parse_file("../files/file3", g);
        int[][] j = g.dijkstra(i);
        stream_out(new FileOutputStream("output3"), interpret(j));

        g = new Graph();
        i = parse_file("../files/file4", g);
        j = g.dijkstra(i);
        stream_out(new FileOutputStream("output4"), interpret(j));

        Random ran = new Random(804715200); /* a very special number */

        Graph g100 = parse_file2("../graph-100");
        long t_100_1 = time_test(g100, ran.nextInt(g.get_size()) + 1);
        long t_100_2 = time_test(g100, ran.nextInt(g.get_size()) + 1);
        long t_100_3 = time_test(g100, ran.nextInt(g.get_size()) + 1);
        long t_100_4 = time_test(g100, ran.nextInt(g.get_size()) + 1);
        long t_100_5 = time_test(g100, ran.nextInt(g.get_size()) + 1);
        long t_100_6 = time_test(g100, ran.nextInt(g.get_size()) + 1);
        long t_100_7 = time_test(g100, ran.nextInt(g.get_size()) + 1);
        long t_100_8 = time_test(g100, ran.nextInt(g.get_size()) + 1);
        long t_100_9 = time_test(g100, ran.nextInt(g.get_size()) + 1);
        long t_100_10 = time_test(g100, ran.nextInt(g.get_size()) + 1);

        Graph g200 = parse_file2("../graph-200");
        long t_200_1 = time_test(g200, ran.nextInt(g.get_size()) + 1);
        long t_200_2 = time_test(g200, ran.nextInt(g.get_size()) + 1);
        long t_200_3 = time_test(g200, ran.nextInt(g.get_size()) + 1);
        long t_200_4 = time_test(g200, ran.nextInt(g.get_size()) + 1);
        long t_200_5 = time_test(g200, ran.nextInt(g.get_size()) + 1);
        long t_200_6 = time_test(g200, ran.nextInt(g.get_size()) + 1);
        long t_200_7 = time_test(g200, ran.nextInt(g.get_size()) + 1);
        long t_200_8 = time_test(g200, ran.nextInt(g.get_size()) + 1);
        long t_200_9 = time_test(g200, ran.nextInt(g.get_size()) + 1);
        long t_200_10 = time_test(g200, ran.nextInt(g.get_size()) + 1);

        Graph g500 = parse_file2("../graph-500");
        long t_500_1 = time_test(g500, ran.nextInt(g.get_size()) + 1);
        long t_500_2 = time_test(g500, ran.nextInt(g.get_size()) + 1);
        long t_500_3 = time_test(g500, ran.nextInt(g.get_size()) + 1);
        long t_500_4 = time_test(g500, ran.nextInt(g.get_size()) + 1);
        long t_500_5 = time_test(g500, ran.nextInt(g.get_size()) + 1);
        long t_500_6 = time_test(g500, ran.nextInt(g.get_size()) + 1);
        long t_500_7 = time_test(g500, ran.nextInt(g.get_size()) + 1);
        long t_500_8 = time_test(g500, ran.nextInt(g.get_size()) + 1);
        long t_500_9 = time_test(g500, ran.nextInt(g.get_size()) + 1);
        long t_500_10 = time_test(g500, ran.nextInt(g.get_size()) + 1);

        Graph g1000 = parse_file2("../graph-1000");
        long t_1000_1 = time_test(g1000, ran.nextInt(g.get_size()) + 1);
        long t_1000_2 = time_test(g1000, ran.nextInt(g.get_size()) + 1);
        long t_1000_3 = time_test(g1000, ran.nextInt(g.get_size()) + 1);
        long t_1000_4 = time_test(g1000, ran.nextInt(g.get_size()) + 1);
        long t_1000_5 = time_test(g1000, ran.nextInt(g.get_size()) + 1);
        long t_1000_6 = time_test(g1000, ran.nextInt(g.get_size()) + 1);
        long t_1000_7 = time_test(g1000, ran.nextInt(g.get_size()) + 1);
        long t_1000_8 = time_test(g1000, ran.nextInt(g.get_size()) + 1);
        long t_1000_9 = time_test(g1000, ran.nextInt(g.get_size()) + 1);
        long t_1000_10 = time_test(g1000, ran.nextInt(g.get_size()) + 1);

        System.out.println("+------+-----+-----------+");
        System.out.println("| size | run | time (ms) |");
        System.out.println("+------+-----+-----------+");
        System.out.printf("| %4d | %3d | %9d |\n", 100, 1, t_100_1);
        System.out.printf("| %4d | %3d | %9d |\n", 100, 2, t_100_2);
        System.out.printf("| %4d | %3d | %9d |\n", 100, 3, t_100_3);
        System.out.printf("| %4d | %3d | %9d |\n", 100, 4, t_100_4);
        System.out.printf("| %4d | %3d | %9d |\n", 100, 5, t_100_5);
        System.out.printf("| %4d | %3d | %9d |\n", 100, 6, t_100_6);
        System.out.printf("| %4d | %3d | %9d |\n", 100, 7, t_100_7);
        System.out.printf("| %4d | %3d | %9d |\n", 100, 8, t_100_8);
        System.out.printf("| %4d | %3d | %9d |\n", 100, 9, t_100_9);
        System.out.printf("| %4d | %3d | %9d |\n", 100, 10, t_100_10);
        System.out.printf("| %4d | %3d | %9d |\n", 200, 1, t_200_1);
        System.out.printf("| %4d | %3d | %9d |\n", 200, 2, t_200_2);
        System.out.printf("| %4d | %3d | %9d |\n", 200, 3, t_200_3);
        System.out.printf("| %4d | %3d | %9d |\n", 200, 4, t_200_4);
        System.out.printf("| %4d | %3d | %9d |\n", 200, 5, t_200_5);
        System.out.printf("| %4d | %3d | %9d |\n", 200, 6, t_200_6);
        System.out.printf("| %4d | %3d | %9d |\n", 200, 7, t_200_7);
        System.out.printf("| %4d | %3d | %9d |\n", 200, 8, t_200_8);
        System.out.printf("| %4d | %3d | %9d |\n", 200, 9, t_200_9);
        System.out.printf("| %4d | %3d | %9d |\n", 200, 10, t_200_10);
        System.out.printf("| %4d | %3d | %9d |\n", 500, 1, t_500_1);
        System.out.printf("| %4d | %3d | %9d |\n", 500, 2, t_500_2);
        System.out.printf("| %4d | %3d | %9d |\n", 500, 3, t_500_3);
        System.out.printf("| %4d | %3d | %9d |\n", 500, 4, t_500_4);
        System.out.printf("| %4d | %3d | %9d |\n", 500, 5, t_500_5);
        System.out.printf("| %4d | %3d | %9d |\n", 500, 6, t_500_6);
        System.out.printf("| %4d | %3d | %9d |\n", 500, 7, t_500_7);
        System.out.printf("| %4d | %3d | %9d |\n", 500, 8, t_500_8);
        System.out.printf("| %4d | %3d | %9d |\n", 500, 9, t_500_9);
        System.out.printf("| %4d | %3d | %9d |\n", 500, 10, t_500_10);
        System.out.printf("| %4d | %3d | %9d |\n", 1000, 1, t_1000_1);
        System.out.printf("| %4d | %3d | %9d |\n", 1000, 2, t_1000_2);
        System.out.printf("| %4d | %3d | %9d |\n", 1000, 3, t_1000_3);
        System.out.printf("| %4d | %3d | %9d |\n", 1000, 4, t_1000_4);
        System.out.printf("| %4d | %3d | %9d |\n", 1000, 5, t_1000_5);
        System.out.printf("| %4d | %3d | %9d |\n", 1000, 6, t_1000_6);
        System.out.printf("| %4d | %3d | %9d |\n", 1000, 7, t_1000_7);
        System.out.printf("| %4d | %3d | %9d |\n", 1000, 8, t_1000_8);
        System.out.printf("| %4d | %3d | %9d |\n", 1000, 9, t_1000_9);
        System.out.printf("| %4d | %3d | %9d |\n", 1000, 10, t_1000_10);
        System.out.println("+------+-----+-----------+");

        double avg100 = (t_100_1 + t_100_2 + t_100_3 + t_100_4 + t_100_5 +
                t_100_6 + t_100_7 + t_100_8 + t_100_9 + t_100_10) / 10.0;
        double avg200 = (t_200_1 + t_200_2 + t_200_3 + t_200_4 + t_200_5 +
                t_200_6 + t_200_7 + t_200_8 + t_200_9 + t_200_10) / 10.0;
        double avg500 = (t_500_1 + t_500_2 + t_500_3 + t_500_4 + t_500_5 +
                t_500_6 + t_500_7 + t_500_8 + t_500_9 + t_500_10) / 10.0;
        double avg1000 = (t_1000_1 + t_1000_2 + t_1000_3 + t_1000_4 + t_1000_5 +
                t_1000_6 + t_1000_7 + t_1000_8 + t_1000_9 + t_1000_10) / 10.0;

        System.out.println("+------+-------------------+");
        System.out.println("| size | average time (ms) |");
        System.out.println("+------+-------------------+");
        System.out.printf("| %4d | %17f |\n", 100, avg100);
        System.out.printf("| %4d | %17f |\n", 200, avg200);
        System.out.printf("| %4d | %17f |\n", 500, avg500);
        System.out.printf("| %4d | %17f |\n", 1000, avg1000);
        System.out.println("+------+-------------------+");
    }
}
