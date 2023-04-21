import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ColorTriangleRed {
    // time = O(n), space = O(n)
    /*
    Split the triangle from in chunks of 4 rows starting from bottom to the top. Paint each chunk according to the following pattern:

1st row: paint the first element.

2nd row: Paint each second element starting from the thrid one.

3rd row: paint the second element only.

4th row: paint each 2nd element in the row starting from the first one.



If there are less than 4 rows left on the top of the triangle, then manully paint the elements with the following pattern:

Always paint the tip of the triangle.

Under the tip, paint left and right elements on each row.

    */
    public int[][] colorRed(int n) {
        List<int[]> res = new ArrayList<>();
        int i = n;
        while (i >= 4) {
            // 4th row
            for (int j = 1; j <= 2 * i - 1; j += 2) {
                res.add(new int[]{i, j});
            }
            --i;
            res.add(new int[]{i, 2});
            --i;
            for (int j = 3; j <= 2 * i - 1; j += 2) {
                res.add(new int[]{i, j});
            }
            --i;
            res.add(new int[]{i, 1});
            --i;
        }
        if (i >= 1) {
            while (i >= 2) {
                res.add(new int[]{i, 1});
                res.add(new int[]{i, 2 * i - 1});
                --i;
            }
            res.add(new int[]{1, 1});
        }
        int[][] rr = new int[res.size()][2];
        for (i = 0; i < res.size(); ++i) {
            rr[i][0] = res.get(i)[0];
            rr[i][1] = res.get(i)[1];
        }
        Arrays.sort(rr, (x, y) -> {
            if (x[0] != y[0]) {
                return Integer.compare(x[0], y[0]);
            } else {
                return Integer.compare(x[1], y[1]);
            }
        });
        return rr;
    }

    public static void main(String[] args) {
        System.out.println(new ColorTriangleRed().colorRed(4));
    }
}
