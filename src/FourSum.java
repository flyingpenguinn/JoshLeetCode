import base.ArrayUtils;

import java.util.*;

/*
LC#18
Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Note:

The solution set must not contain duplicate quadruplets.

Example:

Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]

 */
public class FourSum {
    public List<List<Integer>> fourSum(int[] a, int t) {
        List<List<Integer>> r = new ArrayList<>();
        if (a == null || a.length == 0) {
            return r;
        }
        Arrays.sort(a);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            if (i > 0 && a[i] == a[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < n; j++) {
                if (j - 1 > i && a[j] == a[j - 1]) {
                    continue;
                }
                int k = j + 1;
                int l = n - 1;
                while (k < l) {
                    if (k - 1 > j && a[k] == a[k - 1]) {
                        k++;
                        continue;
                    }
                    int sum = a[i] + a[j] + a[k] + a[l];
                    if (sum == t) {
                        List<Integer> ri = new ArrayList<>();
                        ri.add(a[i]);
                        ri.add(a[j]);
                        ri.add(a[k]);
                        ri.add(a[l]);
                        r.add(ri);
                        k++;
                    } else if (sum < t) {
                        k++;
                    } else {
                        l--;
                    }
                }
            }
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(new FourSum().fourSum(ArrayUtils.read1d("-1,2,2,-5,0,-1,4"), 3));
    }
}