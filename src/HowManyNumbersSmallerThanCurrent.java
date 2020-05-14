import base.ArrayUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
LC#1365
Given the array nums, for each nums[i] find out how many numbers in the array are smaller than it. That is, for each nums[i] you have to count the number of valid j's such that j != i and nums[j] < nums[i].

Return the answer in an array.



Example 1:

Input: nums = [8,1,2,2,3]
Output: [4,0,1,1,3]
Explanation:
For nums[0]=8 there exist four smaller numbers than it (1, 2, 2 and 3).
For nums[1]=1 does not exist any smaller number than it.
For nums[2]=2 there exist one smaller number than it (1).
For nums[3]=2 there exist one smaller number than it (1).
For nums[4]=3 there exist three smaller numbers than it (1, 2 and 2).
Example 2:

Input: nums = [6,5,4,8]
Output: [2,1,0,3]
Example 3:

Input: nums = [7,7,7,7]
Output: [0,0,0,0]


Constraints:

2 <= nums.length <= 500
0 <= nums[i] <= 100
 */
public class HowManyNumbersSmallerThanCurrent {
    // typical bit, could have done counting sort...
    public int[] smallerNumbersThanCurrent(int[] a) {
        int n = a.length;
        int[] ca = Arrays.copyOf(a, n);
        Arrays.sort(ca);
        // rank map: 1,3,4 => 1->1, 3->2, 4->3
        Map<Integer, Integer> rm = new HashMap<>();
        int last = 1;

        for (int i = 0; i < n; i++) {

            if (i == 0 || ca[i] > ca[i - 1]) {
                rm.put(ca[i], last++);
            }
        }
        int[] b = new int[n + 1];
        for (int i = 0; i < n; i++) {
            update(b, rm.get(a[i]), 1);
        }
        int[] r = new int[n];
        for (int i = 0; i < n; i++) {
            r[i] = query(b, rm.get(a[i]) - 1);// exclude self
        }
        return r;
    }

    private void update(int[] b, int i, int v) {
        while (i < b.length) {
            b[i] += v;
            i += i & (-i);
        }
    }

    private int query(int[] b, int i) {
        int r = 0;
        while (i > 0) {
            r += b[i];
            i -= i & (-i);
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new HowManyNumbersSmallerThanCurrent().smallerNumbersThanCurrent(ArrayUtils.read1d("[8,1,2,2,3]"))));
    }
}
