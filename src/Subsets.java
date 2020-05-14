import java.util.*;

/*
LC#78
Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
 */
public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        return doSubSet(nums, 0);
    }

    private List<List<Integer>> doSubSet(int[] nums, int i) {
        if (i == nums.length) {
            List<Integer> empty = new ArrayList<>();
            List<List<Integer>> r = new ArrayList<>();
            r.add(empty);
            return r;
        } else {
            List<List<Integer>> without = doSubSet(nums, i + 1);
            List<List<Integer>> r = new ArrayList<>();
            r.addAll(without);
            for (List<Integer> w : without) {
                List<Integer> nw = new ArrayList<>(w);
                nw.add(nums[i]);
                r.add(nw);
            }
            return r;
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3};
        System.out.println(new Subsets().subsets(array));
    }
}

// classical backtracking no memoization friendly nature
class SubsetPureBacktrack {
    List<List<Integer>> r = new ArrayList<>();

    public List<List<Integer>> subsets(int[] a) {
        dos(0, a, new HashSet<>());
        return r;
    }

    void dos(int i, int[] a, Set<Integer> picked) {
        if (i == a.length) {
            r.add(new ArrayList<>(picked));
            return;
        }
        dos(i + 1, a, picked);
        picked.add(a[i]);
        dos(i + 1, a, picked);
        picked.remove(a[i]);
    }
}

class SubsetBitManipulation {
    public List<List<Integer>> subsets(int[] a) {
        int n = a.length;
        List<List<Integer>> r = new ArrayList<>();
        for (int i = 0; i < (1 << n); i++) {
            r.add(calc(i, a));
        }
        return r;
    }

    List<Integer> calc(int status, int[] a) {
        int i = a.length - 1;
        List<Integer> r = new ArrayList<>();
        while (status > 0) {
            int bit = (status & 1);
            if (bit == 1) {
                r.add(a[i]);
            }
            status >>= 1;
            i--;
        }
        return r;
    }
}