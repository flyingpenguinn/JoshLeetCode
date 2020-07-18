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
        if(a==null || a.isEmpty()){
            return new int[0];
        }
        Map<Integer,List<Integer>> m = new HashMap<>();
        int maxDiag = 0;
        int count = 0;
        for(int i=0; i<a.size(); i++){
            for(int j=0; j<a.get(i).size(); j++){
                int diag = i+j;
                count++;
                maxDiag = Math.max(maxDiag, diag);
                m.computeIfAbsent(diag, k-> new ArrayList<>()).add(a.get(i).get(j));
            }
        }
        int[] r = new int[count];
        int ri = 0;
        for(int i=0; i<=maxDiag; i++){
            List<Integer> list = m.get(i);
            if(list != null){
                for(int j = list.size()-1; j>=0; j--){
                    r[ri++] = list.get(j);
                }
            }
        }
        return r;
    }
}
