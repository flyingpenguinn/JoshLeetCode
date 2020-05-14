import base.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int row = matrix.length;
        if (row == 0) {
            return result;
        }
        int col = matrix[0].length;
        int i = 0;
        int j = -1;
        int used = Integer.MAX_VALUE;
        while (result.size() < row * col) {
            while (j + 1 < col && matrix[i][j + 1] != used) {
                result.add(matrix[i][j + 1]);
                matrix[i][++j] = used;
            }

            while (i + 1 < row && matrix[i + 1][j] != used) {
                result.add(matrix[i + 1][j]);
                matrix[++i][j] = used;
            }
            while (j - 1 >= 0 && matrix[i][j - 1] != used) {
                result.add(matrix[i][j - 1]);
                matrix[i][--j] = used;
            }

            while (i - 1 >= 0 && matrix[i - 1][j] != used) {
                result.add(matrix[i - 1][j]);
                matrix[--i][j] = used;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = ArrayUtils.read("[[2,5],[8,4],[0,-1]]");
        System.out.println(new SpiralMatrix().spiralOrder(matrix));
    }
}


class SpiralMatrixWithoutUsed {
    // use size to handle one liner
    // use rs,re for bounds
    // always let next round handle its head
    List<Integer> r = new ArrayList<>();

    public List<Integer> spiralOrder(int[][] m) {
        int rows = m.length;
        if (rows == 0) {
            return r;
        }
        int cols = m[0].length;
        int rs = 0;
        int re = rows - 1;
        int cs = 0;
        int ce = cols - 1;
        int count = rows * cols;
        while (r.size() < count) {
            int i = rs;
            int j = cs - 1;

            while (r.size() < count && j + 1 <= ce) {
                r.add(m[i][++j]);

            }
            ce--;
            while (r.size() < count && i + 1 <= re) {
                r.add(m[++i][j]);

            }
            re--;
            while (r.size() < count && j - 1 >= cs) {
                r.add(m[i][--j]);

            }
            cs++;
            while (r.size() < count && i - 1 >= rs + 1) {   // rs handled already
                r.add(m[--i][j]);
            }
            rs++;
        }
        return r;
    }
}