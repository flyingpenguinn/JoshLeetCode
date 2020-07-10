import java.util.*;

/*
LC#15
Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
 */
public class ThreeSum {
    // hold i stable then 2 sum sorted on j and k. o(n2)
    public List<List<Integer>> threeSum(int[] a) {
        Arrays.sort(a);
        int i = 0;
        int n = a.length;
        List<List<Integer>> r = new ArrayList<>();
        while (i < n) {
            while (i < n && i > 0 && a[i] == a[i - 1]) {
                i++;
            }
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                while (j < k && j > i + 1 && a[j] == a[j - 1]) {
                    j++;
                }
                // dont need to move k because if either i or j is bigger k wont be the same
                if (j == k) {
                    break;
                }
                int sum = a[i] + a[j] + a[k];
                if (sum == 0) {
                    List<Integer> ri = new ArrayList<>();
                    ri.add(a[i]);
                    ri.add(a[j]);
                    ri.add(a[k]);
                    r.add(ri);
                    j++;
                    k--;
                } else if (sum < 0) {
                    j++;
                } else {
                    k--;
                }
            }
            i++;
        }
        return r;
    }

    public static void main(String[] args) {
        int[] a = {-2, 0, 0, 2, 2};
        System.out.println(new ThreeSum().threeSum(a));
    }
}

//@SILU: xxsum is usually map or sort
// because here i,j,k order is not important, we can sort
// an order-agnostic map would have worked if we allow duplicate
// below approach is very useful when i,j,k order is fixed to be i<j<k, i.e. order matters
class ThreeSumMapBased {
    // hold middle number stable
    public List<List<Integer>> threeSum(int[] a) {
        Arrays.sort(a);
        Set<List<Integer>> r = new HashSet<>();
        for (int i = 0; i < a.length; i++) {
            Set<Integer> jset = new HashSet<>();
            for (int j = 0; j < i; j++) {
                jset.add(a[j]);
            }
            for (int k = i + 1; k < a.length; k++) {
                int target = 0 - a[i] - a[k];
                if (jset.contains(target)) {
                    List<Integer> res = new ArrayList<>();
                    res.add(target);
                    res.add(a[i]);
                    res.add(a[k]);

                    r.add(res);
                }
            }
        }
        return new ArrayList<>(r);
    }
}
