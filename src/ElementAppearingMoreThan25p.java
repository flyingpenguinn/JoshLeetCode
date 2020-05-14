import java.util.HashMap;
import java.util.Map;

/*
LC#1287
Given an integer array sorted in non-decreasing order, there is exactly one integer in the array that occurs more than 25% of the time.

Return that integer.



Example 1:

Input: arr = [1,2,2,6,6,6,6,7,10]
Output: 6


Constraints:

1 <= arr.length <= 10^4
0 <= arr[i] <= 10^5
 */
public class ElementAppearingMoreThan25p {
    // binary search at n/4 points...
    public int findSpecialInteger(int[] a) {
        int n = a.length;
        int thres = n / 4;
        // sorted!
        for (int i = 0; i < n; i += n / 4) {
            int s = start(a, a[i]);
            int e = end(a, a[i]);
            if (e - s + 1 > thres) {
                return a[i];
            }
        }
        return -1;
    }

    int start(int[] a, int v) {
        int l = 0;
        int u = a.length - 1;
        while (l <= u) {
            int m = l + (u - l) / 2;
            if (a[m] >= v) {
                u = m - 1;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    int end(int[] a, int v) {
        int l = 0;
        int u = a.length - 1;
        while (l <= u) {
            int m = l + (u - l) / 2;
            if (a[m] <= v) {
                l = m + 1;
            } else {
                u = m - 1;
            }
        }
        return u;
    }
}
