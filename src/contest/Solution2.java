package contest;

import base.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution2 {
    private List<Integer> res = new ArrayList<>();
    private int min = (int) 1e9;
    public int[] findPermutation(int[] a) {
        int n = a.length;
        List<Integer> list = new ArrayList<>();
        dfs(a, 0, list, 0, 0);
        int[] rr = new int[res.size()];
        for (int i = 0; i < n; ++i) {
            rr[i] = res.get(i);
        }
        return rr;
    }

    private void dfs(int[] a, int i, List<Integer> list, int st, int csum) {
        int n = a.length;
        if (i == n) {
            csum += Math.abs(list.get(list.size() - 1) - a[list.get(0)]);
            System.out.println(list+" "+csum);
            if (csum < min) {
                min = csum;
                res = new ArrayList<>(list);
            } else if (csum == min && smaller(list, res)) {
                res = new ArrayList<>(list);
            }
            return;
        }
        for (int j = 0; j < n; ++j) {
            if (((st >> j) & 1) == 0) {
                int cur = a[j];
                int cp = 0;
                if (i > 0) {
                    cp = Math.abs(list.get(list.size() - 1) - a[cur]);
                }
                int nst = (st | (1 << j));
                list.add(a[j]);
                dfs(a, i + 1, list, nst, csum + cp);
                list.remove(list.size() - 1);
            }
        }
    }

    private boolean smaller(List<Integer> list, List<Integer> res) {
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) > res.get(i)) {
                return false;
            } else if (list.get(i) < res.get(i)) {
                return true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution2().findPermutation(ArrayUtils.read1d("[2,1,0]"))));
    }
}
