public class RLEIteratorRunner {
}


class RLEIterator {
    int[] a = null;
    int cur = 0;

    public RLEIterator(int[] a) {
        this.a = a;
    }

    public int next(int n) {
        while (cur < a.length && cur + 1 < a.length) {
            if (a[cur] >= n) {
                a[cur] -= n;
                return a[cur + 1];
            } else {
                n -= a[cur];
                a[cur] = 0;

                cur += 2;

            }
        }
        return -1;
    }
}

/**
 * Your RLEIterator object will be instantiated and called as such:
 * RLEIterator obj = new RLEIterator(A);
 * int param_1 = obj.next(n);
 */

class RLEIteratorBinarySearch {
    int[] a = null;
    // long in case overflows
    long[] count = null;
    long cur = 0;

    public RLEIteratorBinarySearch(int[] a) {
        this.a = a;
        this.count = new long[a.length / 2 + 1];
        count[0] = 0;
        for (int i = 0; i < a.length; i += 2) {
            count[1 + i / 2] = (i > 0 ? count[i / 2] : 0) + a[i];
        }
        // System.out.println(Arrays.toString(count));
    }


    public int next(int n) {
        cur += n;

        int l = 0;
        int u = count.length - 1;
        // last smaller. if its 0,3 we want to get 0 when n=3
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (count[mid] < cur) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        if (u == -1 || 2 * u + 1 >= a.length) {
            return -1;
        }
        //   System.out.println(u);
        return a[2 * u + 1];
    }
}