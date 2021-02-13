import base.ArrayUtils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class MinAdjSwapsForKConsecutives {
    // "move to consecutive" can usually be converted to
    // move to center
    // then compensate for the extra moves
    public int minMoves(int[] a, int k) {
        if (k == 1) {
            return 0;
        }
        int n = a.length;
        List<Integer> ones = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (a[i] == 1) {
                ones.add(i);
            }
        }
        int[] sum = new int[ones.size()];
        for (int i = 0; i < ones.size(); i++) {
            sum[i] = (i == 0 ? 0 : sum[i - 1]) + ones.get(i);
        }
        int min = Integer.MAX_VALUE;
        for (int i = k - 1; i < ones.size(); i++) {
            int head = i - k + 1;
            if (k % 2 == 1) {
                int mid = (head + i) / 2;
                int left = getsum(sum, head, mid - 1);
                int right = getsum(sum, mid + 1, i);
                int r1 = right - left;
                int r = (k - 1) / 2;
                int r2 = r * (r + 1);  // 1,2,3...r on two sides
                int cur = r1 - r2;
                min = Math.min(min, cur);
            } else {
                int mid = (head + i) / 2;
                int left = getsum(sum, head, mid - 1);
                int right = getsum(sum, mid + 1, i);
                int r1 = right - left - ones.get(mid); // extra -ones.get(mid) at position i
                int r = (k - 2) / 2;
                int r21 = r * (r + 1);
                int r22 = r + 1;
                int cur = r1 - r21 - r22;  // 1,2,3...r on two sides, plus an extra r+1 on the right
                min = Math.min(min, cur);
            }
        }
        return min;
    }

    // sum from i... j
    private int getsum(int[] sum, int i, int j) {
        return i > j ? 0 : sum[j] - ((i == 0) ? 0 : sum[i - 1]);
    }
}
