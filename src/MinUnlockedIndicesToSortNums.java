import base.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MinUnlockedIndicesToSortNums {
    public int minUnlockedIndices(int[] a, int[] l) {
        int n = a.length;
        int[] na = Arrays.copyOf(a, n);
        Arrays.sort(na);
        int[] p = new int[4];
        p[2] = -1;
        p[3] = -1;
        boolean seen3 = false;
        for (int i = 0; i < n; ++i) {
            if (na[i] == 2 && p[2] == -1) {
                p[2] = i;
            }
            if (na[i] == 3 && p[3] == -1) {
                p[3] = i;
            }
            if (a[i] == 3) {
                seen3 = true;
            }
            if (a[i] == 1 && seen3) {
                return -1;
            }
        }
        List<int[]> ints = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            int v = a[i];
            if (v > 1) {
                int start = i;
                int end = p[v];
                if (end > start) {
                    ints.add(new int[]{i, p[v]});
                }
                ++p[v];
            }
        }
        if (ints.isEmpty()) {
            return 0;
        }
        Collections.sort(ints, (x, y) -> Integer.compare(x[0], y[0]));
        int[] psum = new int[n];
        psum[0] = (l[0]);
        for (int i = 1; i < n; ++i) {
            psum[i] = psum[i - 1] + (l[i]);
        }
        int start = ints.get(0)[0];
        int end = ints.get(0)[1];
        int res = 0;
        for (int i = 1; i < ints.size(); ++i) {
            int cstart = ints.get(i)[0];
            int cend = ints.get(i)[1];
            if (cstart > end) {
                int cur = getv(psum, end - 1) - getv(psum, start - 1);
                res += cur;
                start = cstart;
                end = cend;
            } else {
                end = Math.max(end, cend);
            }
        }
        int last = getv(psum, end - 1) - getv(psum, start - 1);
        res += last;
        return res;

    }

    private int getv(int[] psum, int i) {
        return i == -1 ? 0 : psum[i];
    }

    public static void main(String[] args) {
        System.out.println(new MinUnlockedIndicesToSortNums().minUnlockedIndices(ArrayUtils.read1d("[1,2,1,2,3,2]"), ArrayUtils.read1d("[1,0,1,1,0,1]")));
        System.out.println(new MinUnlockedIndicesToSortNums().minUnlockedIndices(ArrayUtils.read1d("[1,2,1,1,3,2,2]"), ArrayUtils.read1d("[1,0,1,1,0,1,0]")));
        System.out.println(new MinUnlockedIndicesToSortNums().minUnlockedIndices(ArrayUtils.read1d("[1,2,1,2,3,2,1]"), ArrayUtils.read1d("[0,0,0,0,0,0,0]")));
        System.out.println(new MinUnlockedIndicesToSortNums().minUnlockedIndices(ArrayUtils.read1d("[2,2,1]"), ArrayUtils.read1d("[0,1,0]")));
    }
}
