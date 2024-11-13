import java.util.Arrays;

public class CounNumberOfFairPairs {
    // may be doable via BIT, but too messy with large range!
    // any "pick two number" problem can be done via sorting
    public long countFairPairs(int[] a, int lower, int upper) {
        Arrays.sort(a);
        int n = a.length;
        long res = 0;
        for (int i = 0; i < n; ++i) {
            int t1 = lower - 1 - a[i];
            int pos1 = binary(a, i + 1, t1);
            int t2 = upper - a[i];
            int pos2 = binary(a, i + 1, t2);
            res += pos2 - pos1;
        }
        return res;
    }

    private int binary(int[] a, int l, int t) {
        int u = a.length - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a[mid] <= t) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }
}

class CountFairPairsTwoPointer {
    // num of pairs sum <= t can be done via 0...n-1 style two pointer
    public long countFairPairs(int[] a, int lower, int upper) {
        int n = a.length;
        Arrays.sort(a);
        long p2 = count(a, upper);
        long p1 = count(a, lower - 1);
        long res = p2 - p1;
        return res;
    }

    private long count(int[] a, int t) {
        long res = 0;
        int n = a.length;
        int j = 0;
        int i = n - 1;
        while (i >= j) {
            while (i > j && a[j] + a[i] <= t) {
                res += (i - j);
                ++j;
            }
            --i;
        }
        return res;
    }
}
