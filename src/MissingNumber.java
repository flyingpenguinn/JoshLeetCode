import base.ArrayUtils;

/*
LC#268
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

Example 1:

Input: [3,0,1]
Output: 2
Example 2:

Input: [9,6,4,2,3,5,7,0,1]
Output: 8
Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
 */
public class MissingNumber {
    public int missingNumber(int[] a) {
        long sum = 0;
        int n = a.length;
        for (int i = 0; i < n; i++) {
            sum += a[i];
        }
        long expected = n * (n + 1) / 2;
        return (int) (expected - sum);
    }

    public static void main(String[] args) {
        System.out.println(new MissingNumberCyclicSwap().missingNumber(ArrayUtils.read1d("3,0,1")));
    }
}


class MissingNumberCyclicSwap {
    // 0 to n  in the array
    public int missingNumber(int[] a) {
        int n = a.length;
        int i = 0;
        while (i < n) {
            if (a[i] == i || a[i] == n) {
                i++;
            } else if (a[i] < n) {
                swap(a, i, a[i]);
            }
        }
        for (i = 0; i < n; i++) {
            if (a[i] != i) {
                return i;
            }
        }
        // if 0..n-1 are there, then it's n
        return n;
    }


    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}

class MissingNumberHashMap {
    // use array a as hashmap
    public int missingNumber(int[] a) {
        int n = a.length;
        // all +1 to avoid 0...
        for (int i = 0; i < n; i++) {
            a[i]++;
        }
        for (int i = 0; i < n; i++) {
            int abs = Math.abs(a[i]);
            if (abs - 1 < n) {
                a[abs - 1] = -Math.abs(a[abs - 1]);
            }
        }
        for (int i = 0; i < n; i++) {
            if (a[i] > 0) {
                return i;
            }
        }
        return n;
    }
}

class MissingNumberXor {
    // index 0 to n-1, numbers 0 to n missing i. i will be the remnant...
    public int missingNumber(int[] a) {
        int r = 0;
        for (int i = 0; i < a.length; i++) {
            r = r ^ i ^ a[i];
        }
        return r;
    }
}
