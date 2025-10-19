public class MinOperationsMakeArrayBeautiful {
    public long minOperations(int[] a) {
        int n = a.length;
        long res = 0;
        for (int i = 1; i < n; ++i) {
            long v2 = a[i];
            long v1 = a[i - 1];
            long times = v2 / v1;
            //   System.out.println(v1+" "+v2+" "+times);
            if (v2 % v1 != 0) {
                long diff = v1 * (times + 1) - v2;
                res += diff;
                a[i] += diff;
            }

        }
        return res;
    }
}
