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
    // "frontier set" technique
    public int subarrayBitwiseORs(int[] a) {
        Set<Integer> all = new HashSet<>();
        Set<Integer> cur = new HashSet<>();
        for (int i = 0; i < a.length; i++) {
            Set<Integer> ncur = new HashSet<>();
            // cur is at most 32 in size because each new value must have at least ONE more 1 than the previous
            ncur.add(a[i]);
            for (int c : cur) {
                ncur.add(c | a[i]);
            }

            all.addAll(ncur);
            cur = ncur;
        }
        return all.size();
    }

    public static void main(String[] args) {
        int[] a = new int[33];
        for (int i = 0; i < 30; i++) {
            a[i] = 1 << i;
        }
        a[31] = 9;
        a[32] = 5;

        System.out.println(new BitwiseOrSubarraysSol2().subarrayBitwiseORs(a));
    }
}

// surprisingly effective but complexity is dubious
class BitwiseOrSubarraysSol2 {
    public int subarrayBitwiseORs(int[] a) {
        int n = a.length;
        Map<Integer, Integer> all = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int curn = 0;
            int j = i;
            for (; j < n; j++) {
                curn |= a[j];
                // if i...j is the same as i-k...j+k we dont need any bigger j to be checked they will be the same
                // in the worst case each i+1 and i's biggest value differ by one digit...and are disjoint. the ony possible case is 1,2,4,8...2^32
                int ext = all.getOrDefault(curn, -1);
                if (ext >= j) {
                    break;
                }
                all.put(curn, j);
            }
            System.out.println("breaking at " + (j - i + 1));
        }
        return all.size();
    }
}
