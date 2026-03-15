public class CountCommasInRange {
    private long[] commas = {1000, 1000_000, 1000_000_000, 1000_000_000_000L, 1000_000_000_000_000L};

    public long countCommas(long n) {
        long res = 0;
        for (int i = 0; i < commas.length; ++i) {
            long base = commas[i];
            if (n < base) {
                break;
            }
            res += n - base + 1;
        }
        return res;
    }
}
