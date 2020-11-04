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
        List<Integer>[] g = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] path : paths) {
            g[path[0]].add(path[1]);
            g[path[1]].add(path[0]);
        }
        int[] res = new int[n];
        for (int i = 1; i <= n; i++) {
            int[] colors = new int[5];
            for (int ne : g[i]) {
                if (res[ne - 1] != 0) {
                    colors[res[ne - 1]] = 1;
                }
            }
            // degree <=3 so we will always locate a good color
            for (int j = 1; j <= 4; j++) {
                if (colors[j] == 0) {
                    res[i - 1] = j;
                }
            }
        }
        return res;
    }

}
