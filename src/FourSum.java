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
        System.out.println(new FourSumMapBased().fourSum(ArrayUtils.read1d("0,0,0"), 0));
    }
}

class FourSumMapBased {
    // do a two sum on n^2 pairs. n^2*k^2 in avg, n^4 in the worst case
// sort the list to avoid duplicated numbers.but even without sorting, we won't give duplicated indexes (no index 1 2 vs 2 1)
    public List<List<Integer>> fourSum(int[] a, int t) {
        // check null error out if needed

        Map<Integer, List<List<Integer>>> m = new HashMap<>();
        Set<List<Integer>> set = new HashSet<>();
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = a[i] + a[j];
                List<List<Integer>> pre = m.getOrDefault(t - sum, new ArrayList<>());
                for (List<Integer> preItem : pre) {
                    int pi = preItem.get(0);
                    int pj = preItem.get(1);
                    if (i != pi && i != pj && j != pi && j != pj) {
                        List<Integer> list = Arrays.asList(a[i], a[j], a[pi], a[pj]);
                        Collections.sort(list);
                        set.add(list);
                    }
                }
                m.computeIfAbsent(sum, k -> new ArrayList<>()).add(List.of(i, j));
            }
        }
        return new ArrayList<>(set);
    }
}

class FourSumKsumBased {
    // a generic solution for ksum, n^(k-1) complexity
    public List<List<Integer>> fourSum(int[] a, int t) {
        Arrays.sort(a);
        List<List<Integer>> r = new ArrayList<>();
        ksum(a, 0, t, 4, r, new ArrayList<>());
        return r;
    }

    // a sorted , k>=2
    private void ksum(int[] a, int start, int t, int k, List<List<Integer>> r, List<Integer> found) {
        int n = a.length;
        if (k == 2) {
            // two sum no dupe allowed
            int i = start;
            int j = n - 1;
            while (i < j) {
                while (i > start && i < j && a[i] == a[i - 1]) {
                    i++;
                }
                if (i == j) {
                    break;
                }
                if (a[i] + a[j] == t) {
                    List<Integer> list = new ArrayList<>(found);
                    list.add(a[i]);
                    list.add(a[j]);
                    r.add(list);
                    i++;
                    j--;
                } else if (a[i] + a[j] < t) {
                    i++;
                } else {
                    j--;
                }
            }
        } else {
            for (int i = start; i < n; i++) {
                while (i > start && i < n && a[i] == a[i - 1]) {
                    i++;
                }
                if (i == n) {
                    break;
                }
                found.add(a[i]);
                ksum(a, i + 1, t - a[i], k - 1, r, found);
                found.remove(found.size() - 1);
            }
        }
    }
}