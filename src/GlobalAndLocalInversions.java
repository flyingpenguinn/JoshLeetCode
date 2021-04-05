/*
LC#775

We have some permutation A of [0, 1, ..., N - 1], where N is the length of A.

The number of (global) inversions is the number of i < j with 0 <= i < j < N and A[i] > A[j].

The number of local inversions is the number of i with 0 <= i < N and A[i] > A[i+1].

Return true if and only if the number of global inversions is equal to the number of local inversions.

Example 1:

Input: A = [1,0,2]
Output: true
Explanation: There is 1 global inversion, and 1 local inversion.
Example 2:

Input: A = [1,2,0]
Output: false
Explanation: There are 2 global inversions, and 1 local inversion.
Note:

A will be a permutation of [0, 1, ..., A.length - 1].
A will have length in range [1, 5000].
The time limit for this problem has been reduced.

 */

public class GlobalAndLocalInversions {
    // a number can't deviate from its position too far, must be within local inversion territory
    public boolean isIdealPermutation(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            if (Math.abs(a[i] - i) > 1) {
                return false;
            }
        }
        return true;
    }
}


class GlobalLocalInversionMergeSort {
    // use merge sort to get inversions
    public boolean isIdealPermutation(int[] a) {
        int loc = local(a);
        int glob = glob(a);
        return loc == glob;
    }

    int local(int[] a) {
        int c = 0;
        for (int i = 0; i + 1 < a.length; i++) {
            if (a[i] > a[i + 1]) {
                c++;
            }
        }
        return c;
    }

    int glob(int[] a) {
        return dog(a, 0, a.length - 1);
    }

    int dog(int[] a, int l, int u) {
        if (l >= u) {
            return 0;
        }
        int mid = l + (u - l) / 2;
        int p1 = dog(a, l, mid);
        int p2 = dog(a, mid + 1, u);
        int[] m = new int[u - l + 1];
        int i = l;
        int j = mid + 1;
        int k = 0;
        int c = p1 + p2;
        while (i <= mid && j <= u) {
            if (a[i] > a[j]) {
                // from i to mid all >a[j], an inversion
                int delta = mid - i + 1;
                c += delta;
                m[k++] = a[j++];
            } else {
                m[k++] = a[i++];
            }
        }
        while (i <= mid) {
            m[k++] = a[i++];
        }
        while (j <= u) {
            m[k++] = a[j++];
        }
        for (int ai = 0; ai < m.length; ai++) {
            a[l + ai] = m[ai];
        }
        return c;
    }
}

class GlobalLocalInversionBit {
    // nlogn using BIT
    public boolean isIdealPermutation(int[] a) {
        int n = a.length;
        int local = 0;
        for (int i = 0; i + 1 < n; i++) {
            if (a[i] > a[i + 1]) {
                local++;
            }
        }
        int[] bit = new int[n + 1];
        int global = 0;
        for (int i = n - 1; i >= 0; i--) {
            int lower = query(bit, a[i] + 1);
            global += lower;
            update(bit, a[i] + 1);
        }
        return global == local;
    }

    private int query(int[] bit, int i) {
        int res = 0;
        while (i > 0) {

            res += bit[i];
            i -= i & (-i);
        }
        return res;
    }

    private void update(int[] bit, int i) {
        while (i < bit.length) {

            bit[i]++;
            i += i & (-i);
        }
    }
}