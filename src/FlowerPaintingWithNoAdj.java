import java.util.ArrayList;
import java.util.List;

/*
LC#1042
You have N gardens, labelled 1 to N.  In each garden, you want to plant one of 4 types of flowers.

paths[i] = [x, y] describes the existence of a bidirectional path from garden x to garden y.

Also, there is no garden that has more than 3 paths coming into or leaving it.

Your task is to choose a flower type for each garden such that, for any two gardens connected by a path, they have different types of flowers.

Return any such a choice as an array answer, where answer[i] is the type of flower planted in the (i+1)-th garden.  The flower types are denoted 1, 2, 3, or 4.  It is guaranteed an answer exists.



Example 1:

Input: N = 3, paths = [[1,2],[2,3],[3,1]]
Output: [1,2,3]
Example 2:

Input: N = 4, paths = [[1,2],[3,4]]
Output: [1,2,1,2]
Example 3:

Input: N = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
Output: [1,2,3,4]
 */
public class FlowerPaintingWithNoAdj {
    // greedy coloring of a graph. the optimal coloring is NPC
    // cant just max smaller ones as there could be holes
    public int[] gardenNoAdj(int n, int[][] paths) {
        List<Integer>[] dp = new ArrayList[n];
        for (int[] p : paths) {
            int min = Math.min(p[0], p[1]);
            int max = Math.max(p[0], p[1]);
            if (dp[max - 1] == null) {
                dp[max - 1] = new ArrayList<>();
            }
            dp[max - 1].add(min - 1);


        }
        //System.out.println(Arrays.toString(dp));
        int[] r = new int[n];
        for (int i = 0; i < n; i++) {
            if (dp[i] == null) {
                r[i] = 1;
            } else {
                boolean[] taken = new boolean[5];
                for (int j = 0; j < dp[i].size(); j++) {
                    int ci = dp[i].get(j);
                    taken[r[ci]] = true;
                }
                for (int k = 1; k <= 4; k++) {
                    if (!taken[k]) {
                        r[i] = k;
                        break;
                    }
                }
            }
        }
        return r;
    }
}
