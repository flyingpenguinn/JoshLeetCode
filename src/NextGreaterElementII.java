import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/*
LC#503
Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.

Example 1:
Input: [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2;
The number 2 can't find next greater number;
The second 1's next greater number needs to search circularly, which is also 2.
Note: The length of given array won't exceed 10000.
 */
public class NextGreaterElementII {
    public int[] nextGreaterElements(int[] a) {
        int n = a.length;
        int[] r = new int[n];
        Arrays.fill(r, -1);
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < 2 * n; i++) {
            while (!dq.isEmpty() && dq.peekFirst() <= i - n) {
                dq.pollFirst();
            }
            while (!dq.isEmpty() && a[dq.peekLast()] < a[i % n]) {
                int lastindex = dq.pollLast();
                r[lastindex] = a[i % n];
            }
            if(i<n){
                dq.offerLast(i);
            }
        }
        return r;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1};
        System.out.println(Arrays.toString(new NextGreaterElementII().nextGreaterElements(nums)));
    }
}
