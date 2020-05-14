import java.util.Arrays;

/*
LC#204
Count the number of prime numbers less than a non-negative number, n.

Example:

Input: 10
Output: 4
Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 */
public class CountPrimes {
    // n/2+n/3+...=o(nlgn) using euler formula
    public int countPrimes(int n) {
        boolean[] np = new boolean[n + 1];
        // checking up to sqrtn is enough
        for (int i = 2; i * i <= n; i++) {
            if (np[i]) {
                continue;
            }
            // i-1...2 already excluded
            for (int j = i; j * i <= n; j++) {
                np[j * i] = true;
            }
        }
        int r = 0;
        for (int i = 2; i <= n - 1; i++) {
            if (!np[i]) {
                r++;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(new CountPrimes().countPrimes(10));
    }
}
