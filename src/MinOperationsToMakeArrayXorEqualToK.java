public class MinOperationsToMakeArrayXorEqualToK {
    public int minOperations(int[] a, int k) {
        int n = a.length;
        int res = 0;
        for (int i = 0; i < 32; ++i) {
            int ti = ((k >> i) & 1);
            int ones = 0;
            int zeros = 0;
            for (int ai : a) {
                int ci = ((ai >> i) & 1);
                if (ci == 1) {
                    ++ones;
                } else {
                    ++zeros;
                }
            }
            ones = ones % 2;
            int cur = ones ^ zeros;
            //   System.out.println(i+" "+ones+" "+zeros+" "+cur+" "+ti);
            if (ones != ti) {
                ++res;
            }
        }
        return res;
    }
}
