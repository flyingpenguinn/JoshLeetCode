import base.ArrayUtils;

import java.util.*;

public class FindArrayStateAfterKMultiplicationII {
    // first make sure every number is added at least once
    // then from smallest onward it will be done once
    private long Mod = (long) (1e9 + 7);

    public int[] getFinalState(int[] a, int k, int multi) {
        int n = a.length;
        if (multi == 1) {
            return a;
        }
        PriorityQueue<long[]> pq = new PriorityQueue<>((x, y) -> {
            if (x[0] != y[0]) {
                return Long.compare(x[0], y[0]);
            } else {
                return Long.compare(x[1], y[1]);
            }
        });
        for (int i = 0; i < n; ++i) {
            pq.offer(new long[]{a[i], i});
        }
        Map<Long, Long> count = new HashMap<>();
        // either done or we reached the state where every number is multiplied once
        while (k > 0 && count.size() < n) {
            long[] top = pq.poll();
            long tv = top[0];
            long tindex = top[1];
            count.put(tindex, count.getOrDefault(tindex, 0L) + 1);
            long after = tv * multi;
            pq.offer(new long[]{after, tindex});
            --k;
        }
        long[] res = new long[n];
        while (!pq.isEmpty()) {
            long[] top = pq.poll();
            long tv = top[0];
            long tindex = top[1];
            res[(int) tindex] = tv;
        }
        long rem = k % n;
        long rtimes = k / n;
        for (int i = 0; i < n; ++i) {
            pq.offer(new long[]{res[(int) i], i});
        }
        while (!pq.isEmpty()) {
            long[] top = pq.poll();
            long tindex = top[1];
            count.put(tindex, rtimes);
            if (rem > 0) {
                count.put(tindex, count.getOrDefault(tindex, 0L) + 1);
                --rem;
            }
        }

        for (long i = 0; i < n; ++i) {
            long v = res[(int) i];
            v %= Mod;
            long pow = calcpow(multi, count.getOrDefault(i, 0L));
            long nv = v * pow;
            nv %= Mod;
            res[(int) i] = (int) nv;
        }
        int[] rres = new int[n];
        for (int i = 0; i < n; ++i) {
            rres[i] = (int) res[i];
        }
        return rres;
    }

    private long calcpow(long a, long b) {
        if (b == 0) {
            return 1L;
        }
        long half = calcpow(a, b / 2);
        long res = half * half;
        res %= Mod;
        if (b % 2 == 1) {
            res *= a;
            res %= Mod;
        }
        return res;
    }

    public int[] brute(int[] nums, int k, int multiplier) {
        for (int i = 0; i < k; i++) {
            int minIndex = 0;
            for (int j = 1; j < nums.length; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            nums[minIndex] *= multiplier;
        }
        return nums;
    }

    public static void main(String[] args) {


        final FindArrayStateAfterKMultiplicationII solution = new FindArrayStateAfterKMultiplicationII();
        System.out.println(Arrays.toString(solution.getFinalState(ArrayUtils.read1d("[161209470]"), 56851412, 39846)));
        //System.out.println(Arrays.toString(solution.brute(ArrayUtils.read1d("[1000000000,1,1,1,1,1]"), 1000, 2)));
        System.out.println(Arrays.toString(solution.getFinalState(ArrayUtils.read1d("1,2"), 3, 4)));
        System.out.println(Arrays.toString(solution.brute(ArrayUtils.read1d("1,2"), 3, 4)));
        System.out.println(Arrays.toString(solution.getFinalState(ArrayUtils.read1d("2,1,3,5,6"), 20, 3)));
        System.out.println(Arrays.toString(solution.brute(ArrayUtils.read1d("2,1,3,5,6"), 20, 3)));
    }
}
