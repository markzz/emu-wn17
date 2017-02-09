import java.util.Random;
import java.util.Scanner;

public class Main {
    public static Relation generate_relation(double probability, int size, long seed) {
        Relation ret = new Relation(size);
        Random ran = new Random(seed);

        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                if (ran.nextFloat() < probability) {
                    ret.add(i, j);
                }
            }
        }

        return ret;
    }

    public static void main(String[] argv) {
        Scanner stdin = new Scanner(System.in);

        System.out.print("Enter probability (between 0 and 1): ");
        double prob = stdin.nextDouble();
        System.out.print("Enter size of matrix: ");
        int size = stdin.nextInt();
        System.out.print("Enter desired seed: ");
        long seed = stdin.nextLong();

        Relation r = generate_relation(prob, size, seed);
        Relation t = r.transitive_closure();
        int count = 0;

        for (int i = 1; i <= t.get_size(); i++) {
            for (int j = 1; j <= t.get_size(); j++) {
                if (t.get_pos(i, j)) {
                    count++;
                }
            }
        }

        System.out.printf("The average percentage to receive the packet is %.02f%%.\n", (double)count / (t.get_size() * t.get_size()) * 100);
    }
}
