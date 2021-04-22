import java.util.ArrayList;
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

    public int minimumTotal(List<List<Integer>> t) {
        int n = t.size();
        List<Integer> dp = new ArrayList<>();
        for (int j = 0; j < t.get(n - 1).size(); j++) {
            dp.add(t.get(n - 1).get(j));
        }
        int size = dp.size();
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < size - 1; j++) {
                // because we go reverse it's j depending on j+1. we then need to go from 0
                int cur = t.get(i).get(j) + Math.min(dp.get(j), dp.get(j + 1));
                dp.set(j, cur);
            }
            size--;
        }
        return dp.get(0);
    }
}

class TriangleOfNumbersAnotherway {
    // i going from 0 to n-1. note here j-1 is going to be overriden if we go from 0 to n-1, so we choose j to go from n-1 to 0
    private final int Max = Integer.MAX_VALUE;

    public int minimumTotal(List<List<Integer>> t) {
        int levels = t.size();
        List<Integer> dp = new ArrayList<>();
        dp.add(t.get(0).get(0));
        for (int i = 1; i < t.size(); i++) {
// because we go forward it's j depending on j-1. we then have to go reverse for j
            for (int j = t.get(i).size() - 1; j >= 0; j--) {
                int cur = t.get(i).get(j);
                int way1 = j == dp.size() ? Max : cur + dp.get(j);
                int way2 = j == 0 ? Max : cur + dp.get(j - 1);
                int curmin = Math.min(way1, way2);
                if (j == dp.size()) {
                    dp.add(curmin);
                } else {
                    dp.set(j, curmin);
                }

            }
        }
        int min = Max;
        for (int j = 0; j < dp.size(); j++) {
            min = Math.min(min, dp.get(j));
        }
        return min;
    }
}
