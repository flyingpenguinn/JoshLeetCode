import java.util.*;

/*
LC#625
Given a positive integer a, find the smallest positive integer b whose multiplication of each digit equals to a.

If there is no answer or the answer is not fit in 32-bit signed integer, then return 0.

Example 1
Input:

48
Output:
68
Example 2
Input:

15
Output:
35
 */
public class MinimumFactorization {
    // from max num first then go up to the top
    public int smallestFactorization(int a) {
        if (a == 1) {
            return 1;
        }
        long rt = dos(a);
        return rt >= Integer.MAX_VALUE ? 0 : (int) rt;
    }

    private long dos(int n) {
        if (n == 1) {
            return 0;
        }
        for (int i = 9; i >= 2; i--) {
            if (n % i == 0) {
                long later = dos(n / i);
                long base = 1;
                while (base <= later) {
                    base *= 10;
                }
                // put current i at the lowest position
                return later * 10 + i;
            }
        }
        return 10L + Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        //    System.out.println(new MinimumFactorization().smallestFactorization(25));
        System.out.println(new MinimumFactorization().smallestFactorization(27000000));

        System.out.println(new MinimumFactorization().smallestFactorization(1));
        System.out.println(new MinimumFactorization().smallestFactorization(121));

        System.out.println(new MinimumFactorization().smallestFactorization(48));
        System.out.println(new MinimumFactorization().smallestFactorization(15));
    }
}

class MinimumFactDp {
    Map<Integer, Long> dp = new HashMap<>();

    public int smallestFactorization(int a) {
        if (a == 1) {
            return 1;
        }
        long rt = dos(a);
        if (rt > Integer.MAX_VALUE) {
            return 0;
        }
        return (int) rt;
    }

    long dos(int n) {
        if (n == 1) {
            return 0l;
        }
        if (dp.containsKey(n)) {
            return dp.get(n);
        }
        long min = 10l + Integer.MAX_VALUE;
        for (int j = 2; j <= 9; j++) {
            if (n % j == 0) {
                long later = dos(n / j);
                long base = 1;
                while (base < later) {
                    base *= 10;
                }
                long cur = j * base + later;
                min = Math.min(min, cur);
            }
        }
        dp.put(n, min);
        return min;
    }
}