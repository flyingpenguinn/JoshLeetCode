import base.ArrayUtils;

/*
LC#330
Given a sorted positive integer array nums and an integer n, add/patch elements to the array such that any number in range [1, n] inclusive can be formed by the sum of some elements in the array. Return the minimum number of patches required.

Example 1:

Input: nums = [1,3], n = 6
Output: 1
Explanation:
Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
So we only need 1 patch.
Example 2:

Input: nums = [1,5,10], n = 20
Output: 2
Explanation: The two patches can be [2, 4].
Example 3:

Input: nums = [1,2,2], n = 5
Output: 0
 */
public class PatchingArray {

    // if we find previous covering 1..n, and we are at k>n+1, we need to cover n+1...k. so just add k into the set and bounce up the range
    // using long vor overflow. time complexity Om + logn
    public int minPatches(int[] a, int n) {
        long high = 0L;
        int r = 0;
        int i = 0;
        while (i < a.length && high < n) {
            if (a[i] > high + 1) {
                r++;
                high += (high + 1);
            } else {
                high += a[i];
                i++;
            }
        }
        while (high < n) {
            r++;
            high += (high + 1);
        }
        return r;
    }


    public static void main(String[] args) {
        System.out.println(new PatchingArray().minPatches(ArrayUtils.read1d("[1,1,4,5,6,6,7,8,9,10,11,13,14,14,14,15,15,17,19,19,19,20,20,20,22,23,24,24,26,29,29,30,30,33,33,36,38,38,38,38,41,42,42,42,44,47,49,49,52,54,55,62,62,62,63,63,64,64,65,68,68,69,73,74,74,75,75,76,77,77,79,79,80,81,81,82,82,85,86,87,88,89,89,92,97,99,99,100]"), 1));
        /*
        System.out.println(new PatchingArray().minPatches(new int[0], 7));
        System.out.println(new PatchingArray().minPatches(ArrayUtils.read1d("1,5,10"), 20));
        System.out.println(new PatchingArray().minPatches(ArrayUtils.read1d("1,3"), 6));
        System.out.println(new PatchingArray().minPatches(ArrayUtils.read1d("1,2,2"), 5));
        */
    }
}
