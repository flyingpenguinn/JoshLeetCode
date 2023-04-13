import java.util.Arrays;

public class MinimizeMaxDiffInPairs {
    // greedily check the result
    // another way of checking is to use sliding window for all js that a[j]-a[i]<=mid then figure out how many numbesr are there


    public int minimizeMax(int[] a, int p) {
        Arrays.sort(a);
        int n = a.length;
        int l = 0;
        int u = a[n - 1] - a[0];
        while (l <= u) {
            int mid = l + (u - l) / 2;
            int cnt = 0;
            int i = 0;
            while (i + 1 < n) {
                // we can greedily pick this and ditch a[i+2]-a[i+1] because otherwise we lose a pair of a[i], a[i+1] anyway
                if (a[i + 1] - a[i] <= mid) {
                    ++cnt;
                    i += 2;
                } else {
                    ++i;
                }
            }
            if (cnt >= p) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}

class MinMaxDiffPairs2 {
    public int minimizeMax(int[] a, int p) {
        Arrays.sort(a);
        int n = a.length;
        int l = 0;
        int u = a[n - 1] - a[0];
        while (l <= u) {
            int mid = l + (u - l) / 2;
            int cnt = 0;
            int i = 0;
            int j = 0;
            while (i < n && cnt < p) {
                j = i + 1;
                while (j < n && a[j] - a[i] <= mid) {
                    ++j;
                }
                int nums = j - i;
                cnt += nums / 2;
                if (nums % 2 == 0) {
                    i = j;
                } else {

                    i = Math.max(i + 1, j - 1);
                }
            }
            if (cnt >= p) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }
}


