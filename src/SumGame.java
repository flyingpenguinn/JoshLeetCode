public class SumGame {
    public boolean sumGame(String s) {
        char[] a = s.toCharArray();
        int n = a.length;
        int sum1 = 0;
        int q1 = 0;
        int sum2 = 0;
        int q2 = 0;
        for (int i = 0; i < n; ++i) {
            if (i < n / 2) {
                if (a[i] == '?') {
                    ++q1;
                } else {
                    sum1 += a[i] - '0';
                }
            } else {
                if (a[i] == '?') {
                    ++q2;
                } else {
                    sum2 += a[i] - '0';
                }
            }
        }
        if (sum1 > sum2) {
            int tmp = sum2;
            sum2 = sum1;
            sum1 = tmp;
            tmp = q2;
            q2 = q1;
            q1 = tmp;
        }
        if ((q1 + q2) % 2 == 1) {
            return true;
        }
        if (sum1 == sum2) {
            return q1 != q2;
        } else {
            // sum1 <= sum2
            if (q1 <= q2) {
                // can't catch up
                return true;
            } else {
                int dsum = sum2 - sum1;
                int dq = q1 - q2;
                // dq / 2 * 9 is the most Bob can catch up
                // (dq+1)/2 is the most Alice can spice it up to make sum1 out of control
                return dsum > dq / 2 * 9 || dsum < (dq + 1) / 2 * 9;
            }
        }
    }
}
