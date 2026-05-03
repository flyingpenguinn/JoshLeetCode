public class SumOfPrimeBetweenNumberAndReverse {
    public int sumOfPrimesInRange(int n) {
        String str = String.valueOf(n);
        String rev = new StringBuilder(str).reverse().toString();
        int revint = Integer.parseInt(rev);
        int start = Math.min(n, revint);
        int end = Math.max(n, revint);
        int res = 0;
        for (int num = start; num <= end; ++num) {
            if (isprime(num)) {
                res += num;
            }
        }
        return res;
    }

    boolean isprime(int n) {
        if (n == 1) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
