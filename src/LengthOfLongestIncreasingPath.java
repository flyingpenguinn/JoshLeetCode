import base.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LengthOfLongestIncreasingPath {


    public int maxPathLength(int[][] a, int k) {
        int n = a.length;
        int[][] na = new int[n][2];
        for (int i = 0; i < n; ++i) {
            na[i][0] = a[i][0];
            na[i][1] = a[i][1];
        }
        int v1 = calc1(na, k, n);
        for (int[] ai : a) {
            ai[0] = -ai[0];
            ai[1] = -ai[1];
        }
        int v2 = calc1(a, k, n);
        return v1 + v2 - 1;
    }

    protected int calc1(int[][] a, int k, int n) {
        int[] t = a[k];
        Arrays.sort(a, (x, y) -> {
            if (x[0] != y[0]) {
                return Integer.compare(x[0], y[0]);
            } else {
                return Integer.compare(y[1], x[1]);
            }
        });
        for (int i = 0; i < n; ++i) {
            if (Arrays.equals(a[i], t)) {
                k = i;
                break;
            }
        }
        //   System.out.println(Arrays.deepToString(es));
        List<Integer> res1 = new ArrayList<>();
        res1.add(a[k][1]);
        for (int i = k + 1; i < a.length; i++) {
            int y = a[i][1];
            int pos = binaryFirstNonsmaller(res1, y);
            if (pos == res1.size()) {
                res1.add(y);
            } else if (pos != 0) {
                res1.set(pos, y);
            }
        }
        return res1.size();
    }


    private int binaryFirstNonsmaller(List<Integer> list, int x) {
        int l = 0;
        int u = list.size() - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (list.get(mid) < x) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return l;
    }


    public static void main(String[] args) {
        System.out.println(new LengthOfLongestIncreasingPath().maxPathLength(ArrayUtils.read("[[1,8],[9,8],[9,9]]"), 1));
    }
}
