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
    private List<List<Integer>> r = new ArrayList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        dfs(1,n,k,new ArrayList<>());
        return r;
    }

    private void dfs(int i, int n, int k, List<Integer> cur){
        if(n<0 || k<0){
            return;
        }
        if(i==10){
            if(n==0 && k==0){
                r.add(new ArrayList<>(cur));
            }
            return;
        }
        dfs(i+1, n, k, cur);
        cur.add(i);
        dfs(i+1, n-i, k-1, cur);
        cur.remove(cur.size()-1);
    }
}