public class SumOfDigitDiffAllPairs {
    public long sumDigitDifferences(int[] a) {
        int n = a.length;
        long[][] digs = new long[12][10];
        for (int i = 0; i < n; ++i) {
            String bin = String.valueOf(a[i]);
            for (int j = 0; j < bin.length(); ++j) {
                int dig = bin.charAt(j) - '0';
                ++digs[j][dig];
            }
        }
        long res = 0;
        for (int i = 0; i < n; ++i) {
            String bin = String.valueOf(a[i]);
            for (int j = 0; j < bin.length(); ++j) {
                int dig = bin.charAt(j) - '0';
                long cc = digs[j][dig];
                //    System.out.println(a[i]+" "+j+" cc="+cc);
                long other = n - cc;
                res += other;
            }
        }
        return res / 2;
    }
}
