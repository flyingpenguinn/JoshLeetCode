import base.ArrayUtils;

import java.util.Arrays;
import java.util.TreeSet;

public class TwoSumLessThanK {
    public int twoSumLessThanK(int[] a, int k) {
        TreeSet<Integer> ts = new TreeSet<>();
        int max = -1;
        for (int i = 0; i < a.length; i++) {
            Integer ext = ts.lower(k - a[i]);
            if (ext != null) {
                max = Math.max(max, ext + a[i]);
            }
            ts.add(a[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new TwoSumLessThanKTwoPointers().twoSumLessThanK(ArrayUtils.read1d("[34,23,1,24,75,33,54,8]"), 60));
    }
}

class TwoSumLessThanKBruteForce {
    public int twoSumLessThanK(int[] a, int k) {
        int max = -1;
        for (int i = 1; i < a.length; i++) {
            if (a[i] >= k) {
                continue;
            }
            for (int j = 0; j < i; j++) {
                int sum = a[i] + a[j];
                if (sum < k) {
                    max = Math.max(max, sum);
                }
            }
        }
        return max;
    }
}

class TwoSumLessThanKTwoPointers {
    public int twoSumLessThanK(int[] a, int k) {
        Arrays.sort(a);
        int i = 0;
        int j = a.length - 1;
        int max = -1;
        while (i < j) {
            int sum = a[i] + a[j];
            if (sum < k) {
                max = Math.max(max, sum);
                i++;
            } else {
                j--;
            }
        }

        return max;
    }
}