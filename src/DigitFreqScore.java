public class DigitFreqScore {
    public int digitFrequencyScore(int n) {
        int[] digs = new int[10];
        while (n > 0) {
            int dig = n % 10;
            ++digs[dig];
            n /= 10;
        }
        int res = 0;
        for (int i = 0; i < 10; ++i) {
            res += digs[i] * i;
        }
        return res;
    }
}
