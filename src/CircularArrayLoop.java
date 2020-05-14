import base.ArrayUtils;

/*
LC#457
You are given a circular array nums of positive and negative integers. If a number k at an index is positive, then move forward k steps. Conversely, if it's negative (-k), move backward k steps. Since the array is circular, you may assume that the last element's next element is the first element, and the first element's previous element is the last element.

Determine if there is a loop (or a cycle) in nums. A cycle must start and end at the same index and the cycle's length > 1. Furthermore, movements in a cycle must all follow a single direction. In other words, a cycle must not consist of both forward and backward movements.



Example 1:

Input: [2,-1,1,2,2]
Output: true
Explanation: There is a cycle, from index 0 -> 2 -> 3 -> 0. The cycle's length is 3.
Example 2:

Input: [-1,2]
Output: false
Explanation: The movement from index 1 -> 1 -> 1 ... is not a cycle, because the cycle's length is 1. By definition the cycle's length must be greater than 1.
Example 3:

Input: [-2,1,-1,-2,-2]
Output: false
Explanation: The movement from index 1 -> 2 -> 1 -> ... is not a cycle, because movement from index 1 -> 2 is a forward movement, but movement from index 2 -> 1 is a backward movement. All movements in a cycle must follow a single direction.


Note:

-1000 ≤ nums[i] ≤ 1000
nums[i] ≠ 0
1 ≤ nums.length ≤ 5000


Follow up:

Could you solve it in O(n) time complexity and O(1) extra space complexity?
 */
public class CircularArrayLoop {
    // 3 possibilities: different sign, same position, or found a loop
    // first 2 are not loop. we return and mark immediately if there is no hope to form a loop. if we ever found a loop we return true
    public boolean circularArrayLoop(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            if (Math.abs(a[i]) < done) {
                if (dfs(a, i)) {
                    return true;
                }
            }
        }
        return false;
    }

    int pending = 10000;
    int done = 100000;

    boolean dfs(int[] a, int i) {
        int iv = a[i];
        a[i] += pending * sign(a[i]);
        int n = a.length;
        int j = (i + iv % n + n) % n;
        boolean rt = false;
        if (i != j && iv * a[j] > 0 && Math.abs(a[j]) < done) {
            if (Math.abs(a[j]) >= pending) {
                return true;
            } else {
                rt = dfs(a, j);
            }
        }
        // otherwise found different sign or self, or have failed on this node before, return false
        a[i] *= done * sign(a[i]);
        return rt;
    }

    private int sign(int i) {
        return i > 0 ? 1 : -1;
    }

    public static void main(String[] args) {
        System.out.println(new CircularArrayLoop().circularArrayLoop(ArrayUtils.read1d("[-1,-1,-1]")));
        System.out.println(new CircularArrayLoop().circularArrayLoop(ArrayUtils.read1d("[2,-1,1,2,2]")));
        System.out.println(new CircularArrayLoop().circularArrayLoop(ArrayUtils.read1d("[-1,2]")));
        System.out.println(new CircularArrayLoop().circularArrayLoop(ArrayUtils.read1d("[-2,1,-1,-2,-2]")));
    }
}
