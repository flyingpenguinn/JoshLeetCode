public class MaxNumberOfNiceDivisors {
    private long mod = 1000000007;

    public int maxNiceDivisors(int input) {

        if (input <= 4) {
            // for 1: single 2
            // for 2: 2,2-> 2 nice divisors
            // for 3: 2,2,2-> 3 nice divisors
            // so result is >= input for sure
            return input;
        }
        long n = input;
        long rem = n % 3;
        long threes = n / 3;
        if (rem == 0) {
            long res = pow3(threes);
            return (int) res;
        } else if (rem == 1) {
            long res = pow3(threes - 1); // due to mod, can't /3 then *4
            res *= 4;
            res %= mod;
            return (int) res;
        } else {
            long res = pow3(threes);
            res *= rem;
            res %= mod;
            return (int) res;
        }
    }

    private long pow3(long n) {
        if (n == 0) {
            return 1L;
        }
        long half = pow3(n / 2);
        long res = half * half;
        res %= mod;
        if (n % 2 == 1) {
            res *= 3L;
            res %= mod;
        }
        return res;
    }
}
