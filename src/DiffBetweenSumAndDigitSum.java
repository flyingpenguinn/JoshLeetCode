public class DiffBetweenSumAndDigitSum {
    public int differenceOfSum(int[] a) {
        int n = a.length;
        int sum = 0;
        int dsum = 0;
        for (int ai : a) {
            sum += ai;
            dsum += digits(ai);
        }
        return Math.abs(sum - dsum);
    }

    private int digits(int n) {
        int res = 0;
        while (n > 0) {
            res += n % 10;
            n /= 10;
        }
        return res;
    }
}
