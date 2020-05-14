import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
LC#254
Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4.
Write a function that takes an integer n and return all possible combinations of its factors.

Note:

You may assume that n is always positive.
Factors should be greater than 1 and less than n.
Example 1:

Input: 1
Output: []
Example 2:

Input: 37
Output:[]
Example 3:

Input: 12
Output:
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]
Example 4:

Input: 32
Output:
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]
 */
public class FactorsCombination {
    List<List<Integer>> r = new ArrayList<>();

    public List<List<Integer>> getFactors(int n) {
        dog(n, 2, new ArrayList<>());
        return r;
    }

    void dog(int n, int s, List<Integer> cur) {
        if (n == 1) {
            if (!cur.isEmpty()) {
                r.add(new ArrayList<>(cur));
            }
            return;
        }
        if (!cur.isEmpty()) {
            cur.add(n);
            r.add(new ArrayList<>(cur));
            cur.remove(cur.size() - 1);
        }
        // later ones must be bigger than s. only need to go to sqrtn because otherwise there is no fact >i
        for (int i = s; i <= (int) Math.sqrt(n); i++) {
            if (n % i == 0) {
                cur.add(i);
                dog(n / i, i, cur);
                cur.remove(cur.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new FactorsCombination().getFactors(32));
    }
}
