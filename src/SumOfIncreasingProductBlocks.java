public class SumOfIncreasingProductBlocks {
    public int sumOfBlocks(int n) {
        long cur = 0;
        long mod = (long) (1e9 + 7);
        long res = 0;
        for (int i = 1; i <= n; ++i) {
            long row = 1;
            long ncur = cur;
            //   System.out.println("i="+i+" cur="+cur);
            for (long j = cur + 1; j <= cur + i; ++j) {
                //      System.out.println("j="+j);
                row *= j;
                row %= -mod;
                ++ncur;
            }

            res += row;
            res %= mod;
            cur = ncur;
        }
        return (int) res;
    }
}
