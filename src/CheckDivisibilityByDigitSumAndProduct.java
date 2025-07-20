public class CheckDivisibilityByDigitSumAndProduct {
    public boolean checkDivisibility(int n) {
        long sum1 = 0;
        long p1 = 1;
        String sn = String.valueOf(n);
        for (char c : sn.toCharArray()) {
            int cind = c - '0';
            sum1 += cind;
            p1 *= cind;
        }
        long sum = sum1 + p1;
        return n % sum == 0;
    }
}
