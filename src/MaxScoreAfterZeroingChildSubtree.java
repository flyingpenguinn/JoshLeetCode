import java.util.*;

public class MaxScoreAfterZeroingChildSubtree {
    // Weekly 528 Q2, AC at 21:00 after 1 WA.
    // dfs returns the subtree sum AFTER all operations inside it. Zeroing child c gains -dfs(c).
    // op = 0: zero the best child only if the gain is positive; op = 1: must zero one, take the best gain anyway.
    // "Any order" is a distractor: once a subtree is zeroed it stays 0.
    // WA lesson: first version used the ORIGINAL subtree sum sub[i] instead of score[i] + sum of children's dfs values,
    // so the children's own operations were lost. Parent must use the child's DP value, not its raw data.
    private List<Integer>[] t;

    public int maxScore(int[] parent, int[] op, int[] score) {
        int n = parent.length;
        t = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            t[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; ++i) {
            int cp = parent[i];
            if (cp == -1) {
                continue;
            }
            t[cp].add(i);
        }
        return dfs2(0, -1, op, score);
    }

    private int dfs2(int i, int pa, int[] op, int[] score) {
        int cop = op[i];
        int maxbenefit = (int) -2e9;
        int ccount = 0;
        int subsum = score[i];
        for (int ne : t[i]) {
            if (ne == pa) {
                continue;
            }
            ++ccount;
            int vne = dfs2(ne, i, op, score);
            subsum += vne;
            maxbenefit = Math.max(maxbenefit, -vne);
        }
        int res = subsum;
        if (ccount == 0) {
            return res;
        }
        if (cop == 0) {
            if (maxbenefit > 0) {
                res += maxbenefit;
            }
        } else {
            res += maxbenefit;
        }
        return res;
    }

    public static void main(String[] args) {
        MaxScoreAfterZeroingChildSubtree sol = new MaxScoreAfterZeroingChildSubtree();
        System.out.println(sol.maxScore(new int[]{-1, 0, 0, 0, 1, 2, 3, 3}, new int[]{1, 1, 0, 0, 1, 1, 0, 1},
                new int[]{5, 6, 1, 2, 3, 2, 3, -3})); // 16
        System.out.println(sol.maxScore(new int[]{-1, 0, 0}, new int[]{0, 0, 0}, new int[]{1, 2, 3})); // 6
        System.out.println(sol.maxScore(new int[]{-1}, new int[]{1}, new int[]{-10})); // -10
    }
}
