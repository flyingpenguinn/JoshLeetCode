import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SmallestPalindromicRearrangementII {
    // gist is use prime technique to count

    // Change this limit if desired. Once we exceed this value, we clamp.
    // It just needs to be comfortably above 1e6 to safely compare.
    private static final long LIMIT = 2_000_000_0L;

    // We'll store all primes up to 5000 (since halfLen can be as large as 5000).
    private static List<Integer> primes = new ArrayList<>();
    // factorialPrimeExps[n][pIndex] will store the exponent of the prime at pIndex in n!
    private static int[][] factorialPrimeExps;

    // Precompute once.
    static {
        sievePrimesUpTo(5000);
        factorialPrimeExps = new int[5001][primes.size()];
        buildFactorialPrimeExps(5000);
    }

    public String smallestPalindrome(String s, int k) {
        // Count character frequencies
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }

        // Identify possible center (since s is originally a palindrome, at most one odd count)
        String center = "";
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                center = String.valueOf((char) (i + 'a'));
                freq[i]--;
                break;
            }
        }

        // Half counts and total length of half
        int[] halfCount = new int[26];
        int halfLen = 0;
        for (int i = 0; i < 26; i++) {
            halfCount[i] = freq[i] / 2;
            halfLen += halfCount[i];
        }

        // Compute the total number of distinct permutations of this half distribution
        long totalPerm = countPermutations(halfCount, halfLen);
        if (totalPerm < k) {
            return "";
        }

        // Construct the k-th lexicographically smallest half
        int[] half = new int[halfLen];
        long K = k;

        for (int pos = 0; pos < halfLen; pos++) {
            for (int ch = 0; ch < 26; ch++) {
                if (halfCount[ch] == 0) {
                    continue;
                }
                halfCount[ch]--;

                // Count how many permutations if we fix ch at this position
                long partial = countPermutations(halfCount, halfLen - pos - 1);

                if (partial >= K) {
                    // We keep ch at this position
                    half[pos] = ch;
                    break;
                } else {
                    // Not enough permutations with ch at this position
                    K -= partial;
                    halfCount[ch]++;
                }
            }
        }

        // Build final string: half + center + reversed(half)
        StringBuilder sb = new StringBuilder();
        for (int x : half) {
            sb.append((char) (x + 'a'));
        }
        String left = sb.toString();
        String right = sb.reverse().toString();
        return left + center + right;
    }

    // Returns the clamped number of permutations for a distribution halfCount with total length sumLen.
    private long countPermutations(int[] halfCount, int sumLen) {
        // We'll compute exponent array = factorialPrimeExps[ sumLen ] minus
        // the sum of factorialPrimeExps[ each halfCount[i] ].
        int[] exps = Arrays.copyOf(factorialPrimeExps[sumLen], factorialPrimeExps[sumLen].length);

        for (int i = 0; i < 26; i++) {
            int c = halfCount[i];
            if (c > 0) {
                int[] sub = factorialPrimeExps[c];
                for (int pIndex = 0; pIndex < exps.length; pIndex++) {
                    exps[pIndex] -= sub[pIndex];
                }
            }
        }

        // Exponentiate with clamping
        long result = 1;
        for (int pIndex = 0; pIndex < exps.length; pIndex++) {
            int e = exps[pIndex];
            int prime = primes.get(pIndex);
            while (e-- > 0) {
                // risk of overflow => clamp
                if (result > LIMIT / prime) {
                    return LIMIT + 1;  // clamp
                }
                result *= prime;
            }
        }
        return result;
    }

    // Simple sieve to fill the 'primes' list with all primes up to maxN.
    private static void sievePrimesUpTo(int maxN) {
        boolean[] isPrime = new boolean[maxN + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; i * i <= maxN; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= maxN; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        for (int i = 2; i <= maxN; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
    }

    // Build factorialPrimeExps up to maxN.
    // factorialPrimeExps[n] is an array primeExp[] of length primes.size(), storing
    // the exponent of each prime in n!.
    private static void buildFactorialPrimeExps(int maxN) {
        // factorialPrimeExps[0] = {0,0,0,...}
        for (int i = 1; i <= maxN; i++) {
            // start as copy of factorialPrimeExps[i-1]
            System.arraycopy(factorialPrimeExps[i - 1], 0, factorialPrimeExps[i], 0, primes.size());
            // factor i
            int val = i;
            for (int pIndex = 0; pIndex < primes.size(); pIndex++) {
                int p = primes.get(pIndex);
                if (p > val) {
                    break;
                }
                while (val % p == 0) {
                    factorialPrimeExps[i][pIndex]++;
                    val /= p;
                }
                if (val == 1) {
                    break;
                }
            }
        }
    }


}
