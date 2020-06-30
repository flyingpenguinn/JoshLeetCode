import java.util.Arrays;

/*
LC#1498
Given an array of integers nums and an integer target.

Return the number of non-empty subsequences of nums such that the sum of the minimum and maximum element on it is less or equal than target.

Since the answer may be too large, return it modulo 10^9 + 7.



Example 1:

Input: nums = [3,5,6,7], target = 9
Output: 4
Explanation: There are 4 subsequences that satisfy the condition.
[3] -> Min value + max value <= target (3 + 3 <= 9)
[3,5] -> (3 + 5 <= 9)
[3,5,6] -> (3 + 6 <= 9)
[3,6] -> (3 + 6 <= 9)
Example 2:

Input: nums = [3,3,6,8], target = 10
Output: 6
Explanation: There are 6 subsequences that satisfy the condition. (nums can have repeated numbers).
[3] , [3] , [3,3], [3,6] , [3,6] , [3,3,6]
Example 3:

Input: nums = [2,3,3,4,6,7], target = 12
Output: 61
Explanation: There are 63 non-empty subsequences, two of them don't satisfy the condition ([6,7], [7]).
Number of valid subsequences (63 - 2 = 61).
Example 4:

Input: nums = [5,2,4,1,7,6,8], target = 16
Output: 127
Explanation: All non-empty subset satisfy the condition (2^7 - 1) = 127


Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^6
1 <= target <= 10^6
 */
public class NumberOfSubsequencsSatisfyGivenSum {
    // number of subsequences... we can sort first!
    /*
    We don't care the original elements order,
we only want to know the count of sub sequence.
So we can sort the original A, and the result won't change.

note if the resultant subsequence needs to satisfy some ordering condition, then we can't sort
     */
    int Mod = 1000000007;

    public int numSubseq(int[] a, int t) {
        // check null error out if so
        // basically in each subseq we can sort it....
        Arrays.sort(a);

        int low = 0;
        int high = a.length - 1;
        long r = 0;
        while (low <= high) {
            if (a[low] + a[high] <= t) {
                int len = high - low;
                // key here: 3,3,6: we have low at first 3 and high at 6. here 3,6 can have 1<<2 types of changes
                long cur = modPower2(len);
                r += cur;
                r %= Mod;
                low++;
            } else {
                high--;
            }
        }
        return (int) r;
    }

    private long modPower2(int len) {
        if (len == 0) {
            return 1;
        }
        if (len == 1) {
            return 2;
        }
        long halfp = modPower2(len / 2);
        if (len % 2 == 0) {
            return (halfp * halfp) % Mod;
        } else {
            return (halfp * halfp * 2) % Mod;
        }
    }
}
