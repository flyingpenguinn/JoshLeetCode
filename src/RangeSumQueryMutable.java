import base.ArrayUtils;

/*
LC#307
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.

Example:

Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
Note:

The array is only modifiable by the update function.
You may assume the number of calls to update and sumRange function is distributed evenly.
 */
//TODO: rewrite as segment tree
public class RangeSumQueryMutable {
    // typical BIT implementation!
    static class NumArray {

        int[] a;
        int[] oa;

        public NumArray(int[] in) {
            int n = in.length;
            this.a = new int[n + 1];
            this.oa = in;
            // use u to update itself
            for (int i = 0; i < n; i++) {
                u(i + 1, oa[i], 0);
            }
        }


        public void update(int i, int val) {
            //from 1
            u(i + 1, val, oa[i]);
            oa[i] = val;
        }

        void u(int i, int v, int ov) {
            int d = v - ov;
            while (i < a.length) {
                a[i] += d;
                i += i & (-i);
            }
        }

        public int sumRange(int i, int j) {
            return p(j + 1) - p(i);
        }

        int p(int i) {
            int r = 0;
            while (i > 0) {
                r += a[i];
                i -= i & (-i);
            }
            return r;
        }
    }

    public static void main(String[] args) {
        NumArray nm = new NumArray(ArrayUtils.read1d("1,2,3,4,5,6"));
        nm.update(2, 1);
        nm.sumRange(0, 5);
    }
}
