import java.util.ArrayList;
import java.util.List;

/*
LC#216
Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Note:

All numbers will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: k = 3, n = 7
Output: [[1,2,4]]
Example 2:

Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class CombinationSumIII {
    // two ways: for each slot k, pick start to 9
    public List<List<Integer>> combinationSum3(int k, int n) {
        return doc(0, k, n, 1);
    }

    List<List<Integer>> doc(int i, int k, int t, int s) {
        // num must start from s
        List<List<Integer>> r = new ArrayList<>();
        if (i == k) {
            if (t == 0) {
                r.add(new ArrayList<>());
            }
            return r;
        }
        for (int j = s; j <= 9; j++) {
            List<List<Integer>> later = doc(i + 1, k, t - j, j + 1);// avoid dup by forcing orders on numbers
            for (List<Integer> li : later) {
                li.add(j);
                r.add(li);
            }
        }

        return r;
    }

}

class CombinationSumIIIpicknum {
    // or for each 1 to 9 pick/non pick
    public List<List<Integer>> combinationSum3(int k, int n) {
        return dom(9, k, n);
    }

    List<List<Integer>> dom(int i, int k, int n) {
        List<List<Integer>> r = new ArrayList<>();
        if (n == 0 && k == 0) {
            r.add(new ArrayList<>());
            return r;
        }
        if (n == 0 || k == 0 || i == 0) {
            return r;
        }
        List<List<Integer>> without = dom(i - 1, k, n);
        r.addAll(without);
        List<List<Integer>> with = new ArrayList<>();
        if (n - i >= 0) {
            with = dom(i - 1, k - 1, n - i);
            for (List<Integer> wi : with) {
                wi.add(i);
                r.add(wi);
            }
        }
        return r;
    }
}