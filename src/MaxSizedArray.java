public class MaxSizedArray {
    // turn or problem to bit summing problem
    public int maxSizedArray(long s) {

        int l = 0;
        int u = 1300;
        while (l <= u) {
            int mid = l + (u - l) / 2;

            long sum = getsum(mid);
            //   System.out.println("mid="+mid+" sum="+sum);
            if (sum <= s) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }

    private long getsum(int n) {
        // each number will contribute to bits in the or result
        int res = 0;
        int[] count = new int[32];
        for (int d = 0; d < 32; ++d) {
            for (int i = 0; i < n; ++i) {
                if (((i >> d) & 1) == 1) {
                    res += (1 << d);
                    ++count[d];
                }
            }
        }
        long cv = 0;
        // the number that is or with all others contribute extra points
        for (int j = 0; j < n; ++j) {
            int cur = res;
            for (int d = 0; d < 32; ++d) {
                if ((((j >> d) & 1) == 1)) {
                    int diff = n - count[d];
                    cur += (1 << d) * diff;
                }

            }
            cv += cur;
        }
        cv *= n * (n - 1) / 2;
        return cv;
    }

    public static void main(String[] args) {
        System.out.println(new MaxSizedArray().maxSizedArray(2));
    }
}
