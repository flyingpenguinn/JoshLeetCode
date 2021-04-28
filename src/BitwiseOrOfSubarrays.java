/*
LC#898
We have an array A of non-negative integers.

For every (contiguous) subarray B = [A[i], A[i+1], ..., A[j]] (with i <= j), we take the bitwise OR of all the elements in B, obtaining a result A[i] | A[i+1] | ... | A[j].

Return the number of possible results.  (Results that occur more than once are only counted once in the final answer.)



Example 1:

Input: [0]
Output: 1
Explanation:
There is only one possible result: 0.
Example 2:

Input: [1,1,2]
Output: 3
Explanation:
The possible subarrays are [1], [1], [2], [1, 1], [1, 2], [1, 1, 2].
These yield the results 1, 1, 2, 1, 3, 3.
There are 3 unique values, so the answer is 3.
Example 3:

Input: [1,2,4]
Output: 6
Explanation:
The possible results are 1, 2, 3, 4, 6, and 7.
 */

import base.ArrayUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BitwiseOrOfSubarrays {
    // "frontier set" technique.
    // "last" is the fontier set. key insight is it will contain at most 32 values because it's the bitwise or result from 0....i-1
    // for each i. r0>=r1>=r2..>=ri-1 and each of them is at least 1 bit in diff. so there can be at most 32 different values for 32 bit integer
    public int subarrayBitwiseORs(int[] a) {
        int n = a.length;
        Set<Integer> set = new HashSet<>();
        Set<Integer> last = new HashSet<>();
        for(int i=0; i<n; i++){
            Set<Integer> cur = new HashSet<>();
            cur.add(a[i]);
            for(int si:last){
                cur.add(si| a[i]);
            }
            set.addAll(cur);
            last = cur;
        }
        return set.size();
    }
}
