public class CheckKthRootInRange {
    private long solve(long num, long k) {

        if (num < 0) {
            return 0;
        }
        if (k == 1) {
            return num + 1;
        }
        double pow = Math.pow(num, 1.0 / k);
        long base = (long) pow;
        long b1 = base + 1;
        long bm1 = Math.max(0, base - 1);
        if (Math.pow(b1, k) <= num) {
            base = b1;
        }
        else if (Math.pow(base, k) > num) {
            base = bm1;
        }
        return base + 1;
    }

    public int countKthRoots(int l, int r, int k) {
        return (int) (solve(r, k) - solve(l - 1, k));

    }
}
