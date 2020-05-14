import base.ArrayUtils;

import java.util.HashMap;
import java.util.Map;

/*
LC#1218
Given an integer array arr and an integer difference, return the length of the longest subsequence in arr which is an arithmetic sequence such that the difference between adjacent elements in the subsequence equals difference.



Example 1:

Input: arr = [1,2,3,4], difference = 1
Output: 4
Explanation: The longest arithmetic subsequence is [1,2,3,4].
Example 2:

Input: arr = [1,3,5,7], difference = 1
Output: 1
Explanation: The longest arithmetic subsequence is any single element.
Example 3:

Input: arr = [1,5,7,8,5,3,4,2,1], difference = -2
Output: 4
Explanation: The longest arithmetic subsequence is [7,5,3,1].


Constraints:

1 <= arr.length <= 10^5
-10^4 <= arr[i], difference <= 10^4
 */
public class LongestArithmeticSubseqWithGivenDiff {
    public int longestSubsequence(int[] a, int diff) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = a.length;
        int max = 1;
        for (int i = n - 1; i >= 0; i--) {
            int next = a[i] + diff;
            if (map.containsKey(next)) {
                int nl = map.get(next) + 1;
                map.put(a[i], nl);
                max = Math.max(max, nl);
            } else {
                map.put(a[i], 1);
            }

        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new LongestArithmeticSubseqWithGivenDiff().longestSubsequence(ArrayUtils.read1d("1,5,7,8,5,3,4,2,1"), -2));
        System.out.println(new LongestArithmeticSubseqWithGivenDiff().longestSubsequence(ArrayUtils.read1d("1,3,5,7"), 1));
        System.out.println(new LongestArithmeticSubseqWithGivenDiff().longestSubsequence(ArrayUtils.read1d("1,2,3,4"), 1));
    }
}
