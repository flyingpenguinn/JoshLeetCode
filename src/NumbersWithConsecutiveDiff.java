import java.util.*;

public class NumbersWithConsecutiveDiff {
    // trap is when n==1, 0 is a good result, and last+k could == last-k (k==0)
    private List<Integer> list = new ArrayList<>();

    public int[] numsSameConsecDiff(int n, int k) {
        int start = n == 1 ? 0 : 1;
        for (int first = start; first <= 9; first++) {
            dfs(first, 1, first, n, k);
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    // we are at ith position, last number picked is last. the whole number now==cur
    private void dfs(int last, int i, long cur, int n, int k) {
        if (cur > Integer.MAX_VALUE) {
            return;
        }
        if (i == n) {
            list.add((int) cur);
            return;
        }
        if (last + k <= 9) {
            int next = last + k;
            dfs(next, i + 1, cur * 10L + next, n, k);
        }
        if (last - k >= 0 && k > 0) {
            int next = last - k;
            dfs(next, i + 1, cur * 10L + next, n, k);
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new NumbersWithConsecutiveDiff().numsSameConsecDiff(3, 7)));
    }
}
