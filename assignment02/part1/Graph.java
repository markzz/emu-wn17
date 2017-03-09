import java.util.Arrays;

public class Graph {
    private int[][] matrix;

    public Graph(int size) {
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

    public Graph shortest_paths_graph() {
        int i, j, k;
        Graph ret = new Graph(this.matrix.length);

        for (i = 1; i <= this.matrix.length; i++) {
            for (j = 1; j <= this.matrix.length; j++) {
                ret.set_val(i, j, this.matrix[i-1][j-1]);
            }
        }

        for (k = 1; k <= this.matrix.length; k++) {
            for (i = 1; i <= this.matrix.length; i++) {
                for (j = 1; j <= this.matrix.length; j++) {
                    /* because large integers can cause overflow errors,
                     * we have to compare UNSIGNED integers.
                     */
                    if (Integer.compareUnsigned(ret.get_val(i, j),
                            ret.get_val(i, k) + ret.get_val(k, j)) > 0) {
                        ret.set_val(i, j, ret.get_val(i, k) + ret.get_val(k, j));
                    }
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

        /* This block is to help make the strings look pretty.
         * Damn efficiency to hell!
         */
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
