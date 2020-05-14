import java.util.List;

/*
LC#120
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle

[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:

Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.

Accepted
209,624
Submissions
505,879
Seen this question in a real interview before?

Yes

No
Contributor
LeetCode

Problems

Pick One

Prev
/146

Next

 */
public class TriangleOfNumbers {

    public int minimumTotal(List<List<Integer>> triangle) {
        int rows = triangle.size();
        List<Integer> dp = triangle.get(rows - 1);
        for (int i = rows - 2; i >= 0; i--) {
            List<Integer> row = triangle.get(i);
            for (int j = 0; j <= i; j++) {
                // choose from j and j+1
                int vj = Math.min(dp.get(j), dp.get(j + 1)) + row.get(j);
                dp.set(j, vj);
            }
        }
        return dp.get(0);
    }
}
