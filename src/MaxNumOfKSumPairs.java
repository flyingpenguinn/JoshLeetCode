import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaxNumOfKSumPairs {
    // sort and count works best when we cant reuse an index. otherwise we will have to do the same trick as in 3sum multiplicity
    public int maxOperations(int[] a, int k) {
        Arrays.sort(a);
        int i = 0;
        int j = a.length - 1;
        int res = 0;
        while (i < j) {
            int sum = a[i] + a[j];
            if (sum == k) {
                res++;
                i++;
                j--;
            } else if (sum < k) {
                i++;
            } else {
                j--;
            }
        }
        return res;
    }
}

class MaxNumOfKsumPairsUsePre {
    // note the res++. this is counting unique pairs we can't reuse an index
    public int maxOperations(int[] a, int k) {
        Map<Integer, Integer> m = new HashMap<>();
        int n = a.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            int diff = k - a[i];
            Integer pre = m.get(diff);
            if (pre == null) {
                m.put(a[i], m.getOrDefault(a[i], 0) + 1);
            } else {
                res++;
                if (pre == 1) {
                    m.remove(diff);
                } else {
                    m.put(diff, pre - 1);
                }
            }
        }
        return res;
    }
}

class MaxNumOfKSumPairsCountingSum {
    public int maxOperations(int[] a, int t) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < a.length; i++) {

            m.put(a[i], m.getOrDefault(a[i], 0) + 1);

        }
        int res = 0;
        for (int k : m.keySet()) {
            // avoid dupes
            if (k > t / 2) {
                continue;
            }
            int other = t - k;
            if (k != other) {
                res += Math.min(m.get(k), m.getOrDefault(other, 0));
                // min because we cant reuse index. not * like the usual counting
            } else {
                res += m.get(k) / 2;
            }
        }
        return res;
    }
}