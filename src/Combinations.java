import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
LC#77
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

Example:

Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
 */
public class Combinations {
    List<List<Integer>> r = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        doc(1, n, k, new ArrayList<>());
        return r;
    }

    void doc(int i, int n, int k, List<Integer> cur) {
        // dont need to continue,cant swap with >n
        if (k == 0) {
            r.add(new ArrayList<>(cur));
            return;
        }
        // need to continue but no more
        if (i > n) {
            return;
        }
        doc(i + 1, n, k, cur);
        cur.add(i);
        doc(i + 1, n, k - 1, cur);
        cur.remove(cur.size() - 1);
    }

    public static void main(String[] args) {
        System.out.println(new CombinationsDpFriendly().combine(4, 2));
    }
}

class CombinationsDpFriendly {


    public List<List<Integer>> combine(int n, int k) {
        return doCombine(n, k, 1);
    }

    private List<List<Integer>> doCombine(int n, int k, int i) {
        List<List<Integer>> r = new ArrayList<>();
        if (k == 0) {
            List<Integer> empty = new ArrayList<>();
            r.add(empty);
            return r;
        }

        // can't put this before k==0: otherwise n won't be included because i>n when we dfs from n
        if (i > n) {
            return r;
        }

        List<List<Integer>> without = doCombine(n, k, i + 1);
        r.addAll(without);
        List<List<Integer>> with = doCombine(n, k - 1, i + 1);
        for (List<Integer> w : with) {
            w.add(i);
            r.add(w);
        }
        return r;
    }


}
