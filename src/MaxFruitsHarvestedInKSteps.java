import base.ArrayUtils;

import static java.lang.Math.*;

public class MaxFruitsHarvestedInKSteps {
    // you can go left and then right, or right then left
    // why the hell did you not consider right->left to start with???
    public int maxTotalFruits(int[][] a, int start, int k) {
        int n = a.length;
        int end = max(a[n - 1][0], start);
        int[] fs = new int[end + 1];
        for (int i = 0; i < n; ++i) {
            fs[a[i][0]] += a[i][1];
        }
        int[] psum = new int[end + 1];
        for (int i = 0; i <= end; ++i) {
            psum[i] = (i == 0 ? 0 : psum[i - 1]) + fs[i];
        }
        int res = 0;
        for (int step = 0; step <= min(k, start); ++step) {
            int leftsteps = step;
            int leftend = max(0, start - leftsteps);
            int left = gap(psum, leftend, start);
            int rightsteps = max(0, k - 2 * leftsteps);
            int rightend = min(start + rightsteps, end);
            int right = gap(psum, start + 1, rightend);
            int cur = left + right;
            res = max(cur, res);
        }
        for (int step = 0; step <= min(k, end - start); ++step) {
            int rightsteps = step;
            int rightend = min(start + rightsteps, end);
            int right = gap(psum, start + 1, rightend);
            int leftsteps = max(0, k - 2 * rightsteps);
            int leftend = max(0, start - leftsteps);
            int left = gap(psum, leftend, start);
            int cur = left + right;
            res = max(cur, res);
        }
        return res;
    }

    private int gap(int[] psum, int i, int j) {
        return psum[j] - (i == 0 ? 0 : psum[i - 1]);
    }

    public static void main(String[] args) {
        System.out.println(new MaxFruitsHarvestedInKSteps().maxTotalFruits(ArrayUtils.read("[[0,7],[7,4],[9,10],[12,6],[14,8],[16,5],[17,8],[19,4],[20,1],[21,3],[24,3],[25,3],[26,1],[28,10],[30,9],[31,6],[32,1],[37,5],[40,9]]"),
                21, 30));
    }

}
