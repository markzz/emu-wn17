import java.util.Arrays;

public class Graph {
    private int[][] matrix;

    public Graph() {
        this.matrix = null;
    }

    public Graph(int size) {
        this.setup(size);
    }

    public void setup(int size) {
        int i;
        this.matrix = new int[size][size];

        for (i = 0; i < this.matrix.length; i++) {
            Arrays.fill(matrix[i], Integer.MAX_VALUE);
            this.matrix[i][i] = 0;
        }
    }

    public int get_size() {
        return this.matrix.length;
    }

    public void set_val(int from, int to, int val) {
        this.matrix[from-1][to-1] = val;
    }

    public int get_val(int from, int to) {
        return this.matrix[from-1][to-1];
    }

    public int[][] dijkstra(int from) {
        /* ret[0] - distances; ret[1] - paths */
        int[][] ret = new int[2][this.get_size()];
        boolean[] b = new boolean[this.get_size()];
        int index;
        int tmp;

        Arrays.fill(ret[0], Integer.MAX_VALUE);
        Arrays.fill(ret[1], from-1);
        Arrays.fill(b, false);

        ret[0][from-1] = 0;

        for (int i = 0; i < b.length; i++) {
            index = 0;
            tmp = Integer.MAX_VALUE;
            for (int j = 0; j < b.length; j++) {
                if ((Integer.compareUnsigned(ret[0][j], tmp) <= 0) && !b[j]) {
                    index = j;
                    tmp = ret[0][j];
                }
            }
            b[index] = true;

            for (int j = 0; j < this.get_size(); j++) {
                tmp = ret[0][index] + this.get_val(index+1, j+1);
                if (Integer.compareUnsigned(tmp, ret[0][j]) < 0) {
                    ret[0][j] = tmp;
                    ret[1][j] = index;
                }
            }
        }

        return ret;
    }

    /**
     * Convert graph to string.
     *
     * @return String in matrix form
     */
    public String to_string() {
        int i, j, max_len = 0;
        String ret = "";

        for (i = 0; i < this.matrix.length; i++) {
            for (j = 0; j < this.matrix.length; j++) {
                if (this.matrix[i][j] == Integer.MAX_VALUE) {
                    continue;
                }

                if (max_len < Integer.toString(this.matrix[i][j]).length()) {
                    max_len = Integer.toString(this.matrix[i][j]).length();
                }
            }
        }

        for (i = 0; i < this.matrix.length; i++) {
            for (j = 0; j < this.matrix[i].length; j++) {
                ret += String.format(String.format("%%%dd ", max_len),
                        this.matrix[i][j] == Integer.MAX_VALUE ? 0 : this.matrix[i][j]);
            }
            ret = ret.substring(0, ret.length() - 1);
            ret += "\n";
        }

        return ret.substring(0, ret.length() - 1);
    }
}
