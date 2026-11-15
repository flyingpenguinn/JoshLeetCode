import java.util.*;

public class CountConsistentSubarraysI {
    // Weekly 528 Q1 (n <= 100), AC in contest at 4:23 after 1 WA (misread "min of the subarray" as global min).
    // Submitted the Q4 attempt with an int return; n is tiny so the run-length pairing is fast enough.
    // See CountConsistentSubarraysII for the O(32n) submask-wall solution.
    List<int[]> l = new ArrayList<>();

    public int countConsistent(int[] a) {
        int n = a.length;
        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n && a[i] == a[j]) {
                ++j;
            }
            int len = j - i;
            l.add(new int[]{a[i], len});
            i = j;
        }
        int ln = l.size();
        long res = 0;
        int pre0 = -1;
        for (i = 0; i < n; ++i) {
            if (a[i] == 0) {
                long before = i - pre0;
                long after = n - i;
                res += before * after;
                pre0 = i;
            }
        }
        int[] minafter = new int[ln];
        minafter[ln - 1] = l.get(ln - 1)[0];
        for (i = ln - 2; i >= 0; --i) {
            minafter[i] = Math.min(l.get(i)[1], minafter[i + 1]);
        }
        for (i = 0; i < ln; ++i) {
            int andv = (1 << 31) - 1;
            int minv = (int) 1e18;
            long init = l.get(i)[1];
            for (int j = i; j < ln; ++j) {
                int jv = l.get(j)[0];
                int jlen = l.get(j)[1];
                andv &= jv;
                if (andv == 0) {
                    break;
                }
                minv = Math.min(minv, jv);
                if (andv == minv) {
                    if (i != j) {
                        res += init * jlen;
                    } else {
                        res += (init + 1) * init / 2;
                    }
                } else if (andv < minafter[i]) {
                    break;
                }
            }
        }
        return (int) res;
    }

    public static void main(String[] args) {
        System.out.println(new CountConsistentSubarraysI().countConsistent(new int[]{5, 3, 1, 2})); // 6
        System.out.println(new CountConsistentSubarraysI().countConsistent(new int[]{1, 1, 1})); // 6
        System.out.println(new CountConsistentSubarraysI().countConsistent(new int[]{0})); // 1
    }
}
