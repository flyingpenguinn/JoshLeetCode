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
        int n = a.length;
        Arrays.sort(a);
        int i = 0;
        List<List<Integer>> r = new ArrayList<>();
        while (i < n) {
            while (i > 0 && i < n && a[i] == a[i - 1]) {
                i++;
            }
            if (i == n) {
                break;
            }
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                while (j > i + 1 && j < k && a[j] == a[j - 1]) {
                    j++;
                }
                if (j == k) {
                    break;
                }
                int cursum = a[i] + a[j] + a[k];
                if (cursum == 0) {
                    List<Integer> item = new ArrayList<>();
                    item.add(a[i]);
                    item.add(a[j]);
                    item.add(a[k]);
                    r.add(item);
                    j++;
                    k--;
                } else if (cursum < 0) {
                    j++;
                } else { //>0
                    k--;
                }
            }
            i++;
        }
        return r;
    }

    public static void main(String[] args) {
        int[] a = {-1, 0, 1, 2, -1, -4};
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
