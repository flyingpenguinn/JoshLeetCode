public class CountIntegersWithEvenDigitSum {
    public int countEven(int num) {
        int res = 0;
        for (int i = 1; i <= num; ++i) {
            int sum = 0;
            int t = i;
            while (t > 0) {
                sum += t % 10;
                t /= 10;
            }
            if (sum % 2 == 0) {
                ++res;
            }
        }
        return res;
    }
}
