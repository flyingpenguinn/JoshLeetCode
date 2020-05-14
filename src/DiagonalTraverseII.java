import java.util.*;

/*
LC#1424
Given a list of lists of integers, nums, return all elements of nums in diagonal order as shown in the below images.


Example 1:



Input: nums = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,4,2,7,5,3,8,6,9]
Example 2:



Input: nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
Output: [1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]
Example 3:

Input: nums = [[1,2,3],[4],[5,6,7],[8],[9,10,11]]
Output: [1,4,2,5,3,8,6,9,7,10,11]
Example 4:

Input: nums = [[1,2,3,4,5,6]]
Output: [1,2,3,4,5,6]


Constraints:

1 <= nums.length <= 10^5
1 <= nums[i].length <= 10^5
1 <= nums[i][j] <= 10^9
There at most 10^5 elements in nums.
 */
public class DiagonalTraverseII {
    // array may be uneven. use map to cache the lists.
    // can be used in diagonal traverse I too
    public int[] findDiagonalOrder(List<List<Integer>> a) {
        int m = a.size();
        if (m == 0) {
            return new int[0];
        }
        Map<Integer, LinkedList<Integer>> map = new HashMap<>();
        int maxsum = 0;
        int items = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < a.get(i).size(); j++) {
                int sum = i + j;
                maxsum = Math.max(maxsum, sum);
                map.computeIfAbsent(sum, k -> new LinkedList<>()).offerFirst(a.get(i).get(j));
                items++;
            }
        }
        int[] r = new int[items];
        int ri = 0;
        for (int i = 0; i <= maxsum; i++) {
            LinkedList<Integer> v = map.get(i);
            Iterator<Integer> it = v.iterator();
            while (it.hasNext()) {
                r[ri++] = it.next();
            }
        }
        return r;
    }
}
