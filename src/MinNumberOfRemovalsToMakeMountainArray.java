import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinNumberOfRemovalsToMakeMountainArray {
    // LIS on two sides. note the both != corner case
    public int minimumMountainRemovals(int[] a) {
        int n = a.length;
        List<Integer> lis = new ArrayList<>();
        int[] lis1 = new int[n];
        for (int i = 0; i < n; i++) {
            int pos = Collections.binarySearch(lis, a[i]);
            if (pos < 0) {
                pos = -(pos + 1);
            }
            lis1[i] = pos + 1;
            if (pos < lis.size()) {
                lis.set(pos, a[i]);
            } else {
                lis.add(a[i]);
            }
        }
        //   System.out.println(Arrays.toString(lis1));
        lis.clear();
        int[] lis2 = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int pos = Collections.binarySearch(lis, a[i]);
            if (pos < 0) {
                pos = -(pos + 1);
            }
            lis2[i] = pos + 1;
            if (pos < lis.size()) {
                lis.set(pos, a[i]);
            } else {
                lis.add(a[i]);
            }
        }
        //     System.out.println(Arrays.toString(lis2));
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (lis1[i] != 1 && lis2[i] != 1) {
                int cur = lis1[i] + lis2[i] - 1;
                max = Math.max(max, cur);
            }
        }
        return n - max;
    }
}
