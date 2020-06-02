import base.ArrayUtils;

import java.util.HashMap;
import java.util.Map;

/*
LC#697
Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.

Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.

Example 1:
Input: [1, 2, 2, 3, 1]
Output: 2
Explanation:
The input array has a degree of 2 because both elements 1 and 2 appear twice.
Of the subarrays that have the same degree:
[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
The shortest length is 2. So return 2.
Example 2:
Input: [1,2,2,3,1,4,2]
Output: 6
Note:

nums.length will be between 1 and 50,000.
nums[i] will be an integer between 0 and 49,999.
 */
public class DegreeOfArray {

    // we notice that the number with the most occurrence must show up so we note down its start and end

    public int findShortestSubArray(int[] a) {
        Map<Integer, Integer> start = new HashMap<>();
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        int minlen = a.length + 1;
        for (int i = 0; i < a.length; i++) {
            int nv = map.getOrDefault(a[i], 0) + 1;
            start.putIfAbsent(a[i], i);

            if (nv > max) {
                max = nv;
                minlen = i - start.get(a[i]) + 1;
            } else if (nv == max) {
                minlen = Math.min(minlen, i - start.get(a[i]) + 1);
            }
            map.put(a[i], nv);
        }
        return minlen;
    }

    public static void main(String[] args) {
        System.out.println(new DegreeOfArray().findShortestSubArray(ArrayUtils.read1d("1,2,2,3,1")));
    }
}


//@SILU for min/max len/number of subarray of some feature that is monotonous we can use two pointers
// monotonous means this feature expands when end++ and shrinks when start--
// such as: max freq of chars, max freq of any number capped, freq of certain char's appearance, etc
class DegreeOfArrayTwoPointer {
    public int findShortestSubArray(int[] a) {
        Map<Integer, Integer> m = new HashMap<>();
        int n = a.length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            int nv = m.getOrDefault(a[i], 0) + 1;
            m.put(a[i], nv);
            max = Math.max(nv, max);
        }
        int low = 0;
        int high = -1;
        m.clear();
        int curmax = 0;
        int curmaxnum = -1;
        int r = n + 1;
        while (true) {
            if (curmax < max) {
                high++;
                if (high == n) {
                    break;
                }
                int nv = m.getOrDefault(a[high], 0) + 1;
                m.put(a[high], nv);
                if (curmax < nv) {
                    curmax = nv;
                    curmaxnum = a[high];
                }
            } else {
                // curmax >= max
                r = Math.min(r, high - low + 1);
                int nv = m.getOrDefault(a[low], 0) - 1;
                if (nv <= 0) {
                    m.remove(a[low]);
                } else {
                    m.put(a[low], nv);
                }
                if (curmaxnum == a[low]) {
                    // there should just be one maxnum when we reach maxfreq, so we know which one it is
                    // after it's gone, maxfreq must == old one -1
                    curmax--;
                }
                low++;
            }
        }
        return r;
    }
}