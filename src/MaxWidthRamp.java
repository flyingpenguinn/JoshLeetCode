import base.ArrayUtils;

import java.util.*;

/*
LC#962
Given an array A of integers, a ramp is a tuple (i, j) for which i < j and A[i] <= A[j].  The width of such a ramp is j - i.

Find the maximum width of a ramp in A.  If one doesn't exist, return 0.



Example 1:

Input: [6,0,8,2,1,5]
Output: 4
Explanation:
The maximum width ramp is achieved at (i, j) = (1, 5): A[1] = 0 and A[5] = 5.
Example 2:

Input: [9,8,1,0,1,9,4,0,4,1]
Output: 7
Explanation:
The maximum width ramp is achieved at (i, j) = (2, 9): A[2] = 1 and A[9] = 1.


Note:

2 <= A.length <= 50000
0 <= A[i] <= 50000

 */
public class MaxWidthRamp {
    int[] a = null;

    // later non smaller ones are useless so we keep a decreasing array
    public int maxWidthRamp(int[] a) {
        this.a = a;
        List<Integer> list = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < a.length; i++) {
            // must be < not <=
            if (list.isEmpty() || a[i] < a[list.get(list.size() - 1)]) {
                list.add(i);
            } else {
                int res = Collections.binarySearch(list, i, new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return Integer.compare(a[o2], a[o1]);
                    }
                });
                // res won't == the length of the listbecause it would be the smallest and we would have added to the list above
                int pos = res >= 0 ? list.get(res) : list.get(-(res + 1));
                int diff = i - pos;
                max = Math.max(max, diff);
            }
        }
        return max;
    }


    public static void main(String[] args) {
        System.out.println(new MaxWidthRamp().maxWidthRamp(ArrayUtils.read1d("6,0,8,2,1,5")));
        System.out.println(new MaxWidthRamp().maxWidthRamp(ArrayUtils.read1d("9,8,1,0,1,9,4,0,4,1")));
    }
}

// we previously used stack to solve min width problem... max width can also be solved by stack in On. similar to "longest well performing interval"
class MaxWidthRampOn {
    // traverse from the end and pop up from the top of the stack when top < number, as later pairs won't make it better
    public int maxWidthRamp(int[] a) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = a.length;
        for (int i = 0; i < n; i++) {
            if (stack.isEmpty() || a[i] < a[stack.peek()]) {
                stack.push(i);
            }
        }
        int max = 0;
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && a[stack.peek()] <= a[i]) {
                max = Math.max(i - stack.pop(), max);
            }
        }
        return max;
    }

}