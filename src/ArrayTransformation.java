import base.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

/*
LC#1243
Given an initial array arr, every day you produce a new array using the array of the previous day.

On the i-th day, you do the following operations on the array of day i-1 to produce the array of day i:

If an element is smaller than both its left neighbor and its right neighbor, then this element is incremented.
If an element is bigger than both its left neighbor and its right neighbor, then this element is decremented.
The first and last elements never change.
After some days, the array does not change. Return that final array.



Example 1:

Input: arr = [6,2,3,4]
Output: [6,3,3,4]
Explanation:
On the first day, the array is changed from [6,2,3,4] to [6,3,3,4].
No more operations can be done to this array.
Example 2:

Input: arr = [1,6,3,4,3,5]
Output: [1,4,4,4,4,5]
Explanation:
On the first day, the array is changed from [1,6,3,4,3,5] to [1,5,4,3,4,5].
On the second day, the array is changed from [1,5,4,3,4,5] to [1,4,4,4,4,5].
No more operations can be done to this array.


Constraints:

1 <= arr.length <= 100
1 <= arr[i] <= 100
 */
public class ArrayTransformation {
    public List<Integer> transformArray(int[] a) {
        List<Integer> r = new ArrayList<>();
        for (int ai : a) {
            r.add(ai);
        }
        while (true) {
            boolean changed = false;
            // must remember old value as it's trampl ed on by later values...
            int oldp = r.get(0);
            for (int i = 1; i < r.size() - 1; i++) {
                int oldv = r.get(i);
                if (r.get(i) < oldp && r.get(i) < r.get(i + 1)) {
                    r.set(i, r.get(i) + 1);
                    changed = true;
                } else if (r.get(i) > oldp && r.get(i) > r.get(i + 1)) {
                    r.set(i, r.get(i) - 1);
                    changed = true;
                }
                oldp = oldv;
            }
            if (!changed) {
                break;
            }
        }
        return r;

    }

    public static void main(String[] args) {
        System.out.println(new ArrayTransformation().transformArray(ArrayUtils.read1d("[2,1,2,1,1,2,2,1]")));
    }
}
