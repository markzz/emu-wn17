import java.util.Arrays;

public class Relation {
    private boolean matrix[][];

    /**
     * Constructor for relation object.
     *
     * @param dimension Length of side of square matrix (dimension x dimension)
     */
    public Relation(int dimension) {
        this.matrix = new boolean[dimension][dimension];
        for (int i = 0; i < this.matrix.length; i++) {
            Arrays.fill(matrix[i], false);
        }
    }

    /**
     * Add path to relation.
     *
     * @param from Starting point
     * @param to Ending point
     */
    public void add(int from, int to) {
        this.matrix[from-1][to-1] = true;
    }

    /**
     * Return if path exists at certain point.
     *
     * @param from Starting point
     * @param to Ending point
     *
     * @return True if path exists; false otherwise
     */
    public boolean get_pos(int from, int to) {
        return this.matrix[from-1][to-1];
    }

    /**
     * Returns the side length of matrix.
     *
     * @return Size of matrix
     */
    public int get_size() {
        return this.matrix.length;
    }

    /**
     * Perform transitive closure.
     *
     * @return New relation of the transitive closure of this relation
     */
    public Relation transitive_closure() {
        Relation ret = new Relation(this.matrix.length);

        for (int i = 1; i <= this.matrix.length; i++) {
            for (int j = 1; j <= this.matrix.length; j++) {
                for (int k = 1; k <= this.matrix.length; k++) {
                    if (this.matrix[j-1][k-1] || ret.get_pos(j, k) || (ret.get_pos(j, i) && ret.get_pos(i, k))) {
                        ret.add(j, k);
                    }
                }
            }
        }

        return ret;
    }

    /**
     * Convert relation to string.
     *
     * @return String in matrix form (index is either 1 or 0)
     */
    public String to_string() {
        String ret = "";
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[i].length; j++) {
                ret += matrix[i][j] ? "1 " : "0 ";
            }
            ret = ret.trim();
            ret += "\n";
        }

        return ret.trim();
    }
}
