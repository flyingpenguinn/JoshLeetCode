public class CountSquareSumTrplets {
    public int countTriples(int n) {
        int res = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int c = i * i + j * j;
                double sqrt = Math.sqrt(1.0 * c);
                int nsqrt = (int) sqrt;
                if (Math.abs(sqrt - nsqrt) < 0.00001 && nsqrt <= n && nsqrt >= 1) {
                    res++;
                }
            }
        }
        return res;
    }
}
