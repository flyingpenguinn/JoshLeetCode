import java.util.*;

public class CountConsistentSubarraysII {
    // Weekly 528 Q4. Gave up in contest; solved myself right after (coach explained the idea).
    // AND == min  <=>  the min m is a submask of every element (bit-as-set view).
    // Fix the representative i (m = a[i]); the condition is now downward closed, so find walls:
    //   wall = nearest element missing some 1-bit of m  -> per bit, nearest index whose bit is 0.
    // Dedupe: the leftmost occurrence of m is the representative, so l > previous index of the same value.
    public long countConsistent(int[] a) {
        int[] zerob = new int[32];
        Arrays.fill(zerob, -1);
        int n = a.length;
        int[] leftwall = new int[n];
        for (int i = 0; i < n; ++i) {
            int v = a[i];
            int pre = -1;
            for (int k = 0; k < 32; ++k) {
                if (((v >> k) & 1) == 0) {
                    zerob[k] = i;
                } else {
                    pre = Math.max(pre, zerob[k]);
                }
            }
            leftwall[i] = pre;
        }
        int[] rightwall = new int[n];
        Arrays.fill(zerob, n);
        for (int i = n - 1; i >= 0; --i) {
            int v = a[i];
            int pre = n;
            for (int k = 0; k < 32; ++k) {
                if (((v >> k) & 1) == 0) {
                    zerob[k] = i;
                } else {
                    pre = Math.min(pre, zerob[k]);
                }
            }
            rightwall[i] = pre;
        }
        Map<Integer, Integer> pre = new HashMap<>();
        long res = 0;
        for (int i = 0; i < n; ++i) {
            int v = a[i];
            int preindex = pre.getOrDefault(v, -1);
            long llimit = Math.max(preindex, leftwall[i]);
            long rlimit = rightwall[i];
            long len = (i - llimit) * (rlimit - i);
            res += len;
            pre.put(v, i);
        }
        return res;
    }

    private static long brute(int[] a) {
        long r = 0;
        for (int i = 0; i < a.length; ++i) {
            int and = -1, mn = Integer.MAX_VALUE;
            for (int j = i; j < a.length; ++j) {
                and &= a[j];
                mn = Math.min(mn, a[j]);
                if (and == mn) {
                    ++r;
                }
            }
        }
        return r;
    }

    public static void main(String[] args) {
        CountConsistentSubarraysII sol = new CountConsistentSubarraysII();
        System.out.println(sol.countConsistent(new int[]{5, 3, 1, 2})); // 6
        System.out.println(sol.countConsistent(new int[]{1, 1, 1})); // 6
        System.out.println(sol.countConsistent(new int[]{0})); // 1
        Random rnd = new Random(528);
        int bad = 0;
        for (int t = 0; t < 5000; ++t) {
            int n = 1 + rnd.nextInt(10);
            int[] a = new int[n];
            for (int i = 0; i < n; ++i) {
                a[i] = rnd.nextInt(16);
            }
            if (sol.countConsistent(a) != brute(a)) {
                ++bad;
            }
        }
        System.out.println("bad=" + bad);
    }
}
