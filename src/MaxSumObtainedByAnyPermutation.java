import java.util.Arrays;

public class MaxSumObtainedByAnyPermutation {
    // assign max number to biggest repeat
    public int maxSumRangeQuery(int[] a, int[][] reqs) {
        int n = a.length;
        int[] repeats = new int[n];
        for (int[] req : reqs) {
            repeats[req[0]]++;
            if (req[1] + 1 < n) {
                repeats[req[1] + 1]--;
            }
        }
        for (int i = 1; i < n; i++) {
            repeats[i] += repeats[i - 1];
        }
        Arrays.sort(repeats);
        Arrays.sort(a);
        long res = 0;
        // dont need to find interval, each number repeated how many times we know it..
        for (int i = 0; i < n; i++) {
            res += repeats[i] * a[i];
            res %= mod;
        }
        return (int) res;
    }

    private int mod = 1000000007;
}
