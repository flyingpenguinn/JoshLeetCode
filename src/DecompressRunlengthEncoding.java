/*
LC#1313
We are given a list nums of integers representing a list compressed with run-length encoding.

Consider each adjacent pair of elements [a, b] = [nums[2*i], nums[2*i+1]] (with i >= 0).  For each such pair, there are a elements with value b in the decompressed list.

Return the decompressed list.



Example 1:

Input: nums = [1,2,3,4]
Output: [2,4,4,4]


Constraints:

2 <= nums.length <= 100
nums.length % 2 == 0
1 <= nums[i] <= 100
 */

import java.util.ArrayList;
import java.util.List;

public class DecompressRunlengthEncoding {
    public int[] decompressRLElist(int[] a) {
        List<Integer> r = new ArrayList<>();
        for (int i = 0; i < a.length; i += 2) {
            int c = a[i];
            for (int j = 0; j < c; j++) {
                r.add(a[i + 1]);
            }
        }
        int[] rr = new int[r.size()];
        for (int i = 0; i < r.size(); i++) {
            rr[i] = r.get(i);
        }
        return rr;
    }
}
