public class CountUnfinishedTasksAfterEachShift {
    public int[] countTasks(int[] tasks, int[] shifts) {
        int n = tasks.length;
        int sn = shifts.length;
        int[] res = new int[sn];
        long[] right = new long[n];
        right[n - 1] = tasks[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            right[i] = right[i + 1] + tasks[i];
        }
        long[] left = new long[n];
        left[0] = tasks[0];
        for (int i = 1; i < n; ++i) {
            left[i] = left[i - 1] + tasks[i];
        }
        int cur = 0;
        long currem = 0;
        for (int i = 0; i < sn; ++i) {
            long st = shifts[i];
            if (currem > 0) {
                if (currem > st) {
                    currem -= st;
                    res[i] = n - cur;
                    continue;
                } else {
                    st -= currem;
                    ++cur;
                    currem = 0;
                }
            }

            if (cur == n || st >= right[cur]) {
                res[i] = 0;
                cur = 0;
            } else {
                long cbase = cur == 0 ? 0 : left[cur - 1];
                long lookup = st + cbase;
                int pos = binary(left, cur, lookup);
                long remshift = lookup - (pos == -1 ? 0 : left[pos]);
                currem = tasks[pos + 1] - remshift;
                int rem = n - (pos + 1);
                res[i] = rem;
                cur = pos + 1;
            }
        }
        return res;
    }

    private int binary(long[] a, int l, long lookup) {
        int n = a.length;
        int u = n - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a[mid] <= lookup) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }
}
