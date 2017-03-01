/*
 * Mark Weiman
 * https://github.com/markzz/emu-wn17/blob/COSC311/PROJ-01/src/Main.java
 * COSC 311
 * PROJECT 01
 * WINTER 2017
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class Main {
    /* this code is copied from http://stackoverflow.com/a/9832977/4386974 */
    private static int get_poisson_random(double mean) {
        Random r = new Random();
        double L = Math.exp(-mean);
        int k = 0;
        double p = 1.0;
        do {
            p = p * r.nextDouble();
            k++;
        } while (p > L);
        return k - 1;
    }

    public static void main(String[] argv) throws FileNotFoundException {
        double mean = 0.2;
        long benefit = 0;
        long tmp;
        String csv_data_1 = "";
        String csv_data_2 = "";

        Server s1 = new Server();
        Server s2 = new Server();

        int customer_intake = 0;

        Queue customer_queue = new Queue();

        /* one server */
        for (int i = 0; i < 50; i++) {
            benefit -= 3;
            customer_intake = get_poisson_random(mean);
            for (int j = 0; j < customer_intake; j++) {
                customer_queue.queue(i + 8);
            }

            benefit -= customer_queue.remove_dead(i) * 10;

            if (!s1.is_serving(i) && customer_queue.get_size() != 0) {
                s1.new_customer(i);

                if (customer_queue.get_size() > 0) {
                    benefit++;
                    customer_queue.dequeue();
                }
            }

            csv_data_1 += String.format("%d,%d,%d,%d,%d\n", i+1, customer_queue.get_size(), benefit, customer_queue.total_wait_time(i), s1.get_customers_served());
        }

        s1 = new Server();
        customer_queue = new Queue();
        benefit = 0;
        System.out.println();

        /* two servers */
        for (int i = 0; i < 50; i++) {
            benefit -= 6;
            customer_intake = get_poisson_random(mean);
            for (int j = 0; j < customer_intake; j++) {
                customer_queue.queue(i + 8);
            }

            tmp = customer_queue.remove_dead(i);
            benefit -= tmp * 10;

            if (!s1.is_serving(i) && customer_queue.get_size() != 0) {
                s1.new_customer(i);

                if (customer_queue.get_size() > 0) {
                    benefit++;
                    customer_queue.dequeue();
                }
            }

            if (!s2.is_serving(i) && customer_queue.get_size() != 0) {
                s2.new_customer(i);

                if (customer_queue.get_size() > 0) {
                    benefit++;
                    customer_queue.dequeue();
                }
            }

            csv_data_2 += String.format("%d,%d,%d,%d,%d\n", i+1, customer_queue.get_size(), benefit, customer_queue.total_wait_time(i), s1.get_customers_served() + s2.get_customers_served());
        }


        PrintWriter pw = new PrintWriter(new File("one-server.csv"));
        PrintWriter pw2 = new PrintWriter(new File("two-servers.csv"));

        pw.println("\"Tick\",\"QueueSize\",\"Benefit\",\"WaitTime\",\"Served\"");
        pw2.println("\"Tick\",\"QueueSize\",\"Benefit\",\"WaitTime\",\"Served\"");

        pw.print(csv_data_1);
        pw2.print(csv_data_2);

        pw.close();
        pw2.close();
    }
}
