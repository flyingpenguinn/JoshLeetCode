import java.util.List;

/*
LC#339
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:

Input: [[1,1],2,[1,1]]
Output: 10
Explanation: Four 1's at depth 2, one 2 at depth 1.
Example 2:

Input: [1,[4,[6]]]
Output: 27
Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27.
 */
public class NestedListWeightI {
    public int depthSum(List<NestedInteger> list) {
        return dfs(list, 1);
    }

    private int dfs(List<NestedInteger> list, int depth) {
        int r = 0;
        if (list == null || list.isEmpty()) {
            return r;
        }
        for (NestedInteger nl : list) {
            if (nl.isInteger()) {
                r += nl.getInteger() * depth;
            } else {
                r += dfs(nl.getList(), depth + 1);
            }
        }
        return r;
    }
}
