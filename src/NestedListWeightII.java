import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    int flatsum = 0;
    int maxd = 0;

    public int depthSumInverse(List<NestedInteger> a) {
        int sum2 = dfslist(a, 1);
        return (maxd + 1) * flatsum - sum2;
    }

    int dfslist(List<NestedInteger> a, int d) {
        int r = 0;
        for (NestedInteger ai : a) {
            if (ai.isInteger()) {
                int v = ai.getInteger();
                r += v * d;
                flatsum += v;
            } else {
                r += dfslist(ai.getList(), d + 1);
            }
        }
        return r;
    }
}
