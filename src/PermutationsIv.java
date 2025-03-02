import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PermutationsIv {

    // This constant is used to clamp/limit counts in our DP to avoid overflow
    // and to ensure we can handle k up to 1e15 without generating huge numbers.
    private static final long MAX_COUNT = 2_000_000_000_000_000_000L;

    // dp[o][e][p] = number of alternating permutations possible
    // if we have 'o' unused odd numbers, 'e' unused even numbers, and
    // the last chosen element had parity 'p':
    //   p = 0 => last was even => must pick an odd next
    //   p = 1 => last was odd => must pick an even next
    //   p = 2 => nothing chosen yet => can pick odd or even
    private long[][][] dp;

    // We'll store the available odd and even numbers separately, sorted.
    private List<Integer> odds, evens;

    // Returns the k-th alternating permutation of {1,2,...,n} in ascending order,
    // or an empty array if there are fewer than k valid permutations.
    public int[] permute(int n, long k) {
        // Separate the numbers into odds and evens, each sorted.
        odds = new ArrayList<>();
        evens = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if ((i & 1) == 1) {
                odds.add(i);
            } else {
                evens.add(i);
            }
        }
        Collections.sort(odds);
        Collections.sort(evens);

        // Initialize our DP cache, which is a 3D array
        dp = new long[odds.size() + 1][evens.size() + 1][3];
        for (int i = 0; i <= odds.size(); i++) {
            for (int j = 0; j <= evens.size(); j++) {
                Arrays.fill(dp[i][j], -1L);
            }
        }

        // We'll build the result step by step
        List<Integer> ans = new ArrayList<>();

        // 'lastP' tracks the parity of the previously chosen element:
        // 0 => even, 1 => odd, 2 => haven't chosen any yet
        int lastP = 2;

        // o and e track how many odds and evens remain.
        int o = odds.size(), e = evens.size();

        // We need to pick exactly n elements.
        for (int step = 0; step < n; step++) {
            // If we haven't chosen any element yet (lastP == 2),
            // we can pick from either the odds list or the evens list,
            // in ascending order (like a merge of the two sorted lists).
            if (lastP == 2) {
                boolean found = false;
                int iOdd = 0, iEven = 0;
                // Merge-like traversal of odds and evens in ascending order
                while (iOdd < odds.size() || iEven < evens.size()) {
                    int val;
                    boolean pickIsOdd;
                    if (iEven >= evens.size()) {
                        // no more evens, must pick an odd
                        val = odds.get(iOdd);
                        pickIsOdd = true;
                    } else if (iOdd >= odds.size()) {
                        // no more odds, must pick an even
                        val = evens.get(iEven);
                        pickIsOdd = false;
                    } else {
                        // both are available, pick whichever is smaller
                        if (odds.get(iOdd) < evens.get(iEven)) {
                            val = odds.get(iOdd);
                            pickIsOdd = true;
                        } else {
                            val = evens.get(iEven);
                            pickIsOdd = false;
                        }
                    }
                    // Suppose we pick 'val'. How many permutations follow from that choice?
                    long ways = pickIsOdd
                            ? countWays(o - 1, e, 1) // now last chosen parity is 1
                            : countWays(o, e - 1, 0); // last chosen parity is 0
                    // If ways >= k, that means the k-th permutation is among those ways,
                    // so we finalize this pick and continue.
                    if (ways >= k) {
                        ans.add(val);
                        if (pickIsOdd) {
                            odds.remove(iOdd);
                            o--;
                            lastP = 1;
                        } else {
                            evens.remove(iEven);
                            e--;
                            lastP = 0;
                        }
                        found = true;
                        break;
                    } else {
                        // otherwise, skip those 'ways' permutations and decrement k
                        k -= ways;
                        if (pickIsOdd) {
                            iOdd++;
                        } else {
                            iEven++;
                        }
                    }
                }
                // If not found, that means we've exhausted all possibilities => fewer than k permutations
                if (!found) {
                    return new int[0];
                }
            }
            // If the last chosen number was even, we must pick an odd next.
            else if (lastP == 0) {
                if (odds.isEmpty()) {
                    return new int[0];
                }
                boolean found = false;
                // Try each odd in ascending order
                for (int i = 0; i < odds.size(); i++) {
                    long ways = countWays(o - 1, e, 1);
                    if (ways >= k) {
                        ans.add(odds.get(i));
                        odds.remove(i);
                        o--;
                        lastP = 1;
                        found = true;
                        break;
                    } else {
                        k -= ways;
                    }
                }
                if (!found) {
                    return new int[0];
                }
            }
            // If the last chosen number was odd, we must pick an even next.
            else {
                if (evens.isEmpty()) {
                    return new int[0];
                }
                boolean found = false;
                for (int i = 0; i < evens.size(); i++) {
                    long ways = countWays(o, e - 1, 0);
                    if (ways >= k) {
                        ans.add(evens.get(i));
                        evens.remove(i);
                        e--;
                        lastP = 0;
                        found = true;
                        break;
                    } else {
                        k -= ways;
                    }
                }
                if (!found) {
                    return new int[0];
                }
            }
        }

        // Convert list to array
        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }
        return res;
    }

    // Returns the number of valid alternating permutations if we have 'o' odds left,
    // 'e' evens left, and the parity of the last chosen element is 'p'.
    private long countWays(int o, int e, int p) {
        // If we have invalid counts, no permutations
        if (o < 0 || e < 0) return 0;
        // If we've computed this state before, use it
        if (dp[o][e][p] != -1) return dp[o][e][p];
        // If we have no elements left, there's exactly 1 way to finish (the empty arrangement)
        if (o == 0 && e == 0) {
            dp[o][e][p] = 1;
            return 1;
        }
        long r;
        // last = even => must pick odd
        if (p == 0) {
            if (o == 0) r = 0;
            else {
                // We have 'o' choices of odd numbers, each leads to the same future state
                long sub = countWays(o - 1, e, 1);
                r = mult(o, sub);
            }
        }
        // last = odd => must pick even
        else if (p == 1) {
            if (e == 0) r = 0;
            else {
                long sub = countWays(o, e - 1, 0);
                r = mult(e, sub);
            }
        }
        // p == 2 => no last chosen => we can pick odd or even
        else {
            long pickOdd = 0, pickEven = 0;
            if (o > 0) {
                pickOdd = mult(o, countWays(o - 1, e, 1));
            }
            if (e > 0) {
                pickEven = mult(e, countWays(o, e - 1, 0));
            }
            r = add(pickOdd, pickEven);
        }
        dp[o][e][p] = r;
        return r;
    }

    // Multiply two counts safely, capping at MAX_COUNT to avoid overflow.
    private long mult(long a, long b) {
        if (a == 0 || b == 0) return 0;
        if (a > MAX_COUNT || b > MAX_COUNT) return MAX_COUNT;
        if (a > Long.MAX_VALUE / b) return MAX_COUNT;
        long r = a * b;
        return (r > MAX_COUNT) ? MAX_COUNT : r;
    }

    // Add two counts safely, capping at MAX_COUNT.
    private long add(long a, long b) {
        long r = a + b;
        return (r > MAX_COUNT) ? MAX_COUNT : r;
    }
}

