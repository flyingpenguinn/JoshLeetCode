import base.ListNode;

import java.util.Arrays;

public class SpiralMatrixIv {
    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] res = new int[m][n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(res[i], -1);
        }
        int i = 0;
        int j = 0;
        ListNode p = head;
        while (p != null) {
            while (p != null && j < n && res[i][j] == -1) {
                res[i][j] = p.val;
                p = p.next;
                ++j;
            }
            --j;
            ++i;
            while (p != null && i < m && res[i][j] == -1) {
                res[i][j] = p.val;
                p = p.next;
                ++i;
            }
            --i;
            --j;
            while (p != null && j >= 0 && res[i][j] == -1) {
                res[i][j] = p.val;
                p = p.next;
                --j;
            }
            ++j;
            --i;
            while (p != null && i >= 0 && res[i][j] == -1) {
                res[i][j] = p.val;
                p = p.next;
                --i;
            }
            ++i;
            ++j;
        }
        return res;
    }
}
