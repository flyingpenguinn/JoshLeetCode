public class GcdOddEvenSums {
    private int gcd(int a, int b) {
        if (a < b) {
            return gcd(b, a);
        }
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public int gcdOfOddEvenSums(int n) {
        int sum1 = 0;
        for (int i = 1; i <= 2 * n - 1; i += 2) {
            sum1 += i;
        }
        int sum2 = 0;
        for (int i = 2; i <= 2 * n; i += 2) {
            sum2 += i;
        }
        // System.out.println(sum1+" "+ sum2);
        return gcd(sum1, sum2);
    }
}
