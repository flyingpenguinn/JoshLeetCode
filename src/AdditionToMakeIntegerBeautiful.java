public class AdditionToMakeIntegerBeautiful {
    public long makeIntegerBeautiful(long n, int target) {
        int csum = sumdigs(n);
        if (csum <= target) {
            return 0;
        }
        long base = 1;
        while (n * 10 >= base) {
            long mod = n % base;
            long diff = base - mod;
            long cur = n + diff;
            if (sumdigs(cur) <= target) {
                return diff;
            }
            base *= 10;
        }
        return -1;
    }

    private int sumdigs(long n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }
}
