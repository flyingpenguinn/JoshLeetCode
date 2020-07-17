import java.util.*;

/*
LC#364
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

Example 1:

Input: [[1,1],2,[1,1]]
Output: 8
Explanation: Four 1's at depth 1, one 2 at depth 2.
Example 2:

Input: [1,[4,[6]]]
Output: 17
Explanation: One 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17.
 */
public class NestedListWeightII {
    // 1x + 2y + 3z = (3 + 1) * (x + y + z) - (3x + 2y + z);
    // alternatively can use a map but this is more clever...

    public int depthSumInverse(List<NestedInteger> nestedList) {
        int[] res = dfs(nestedList, 1);
        System.out.println(Arrays.toString(res));
        return (res[2] + 1) * res[1] - res[0];
    }

    // weited sum, flat sum, and max depth
    // current level is d. all the items in this list share the same depth
    private int[] dfs(List<NestedInteger> list, int d) {
        int weighted = 0;
        int flat = 0;
        int maxd = d; // at this leel the height is at least d already
        for (NestedInteger nl : list) {
            if (nl.isInteger()) {
                weighted += nl.getInteger() * d;
                flat += nl.getInteger();
            } else {
                int[] next = dfs(nl.getList(), d + 1);
                weighted += next[0];
                flat += next[1];
                maxd = Math.max(maxd, next[2]);
            }
        }
        return new int[]{weighted, flat, maxd};
    }
}
