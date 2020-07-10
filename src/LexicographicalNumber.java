import java.util.ArrayList;
import java.util.List;

/*
LC#386
Given an integer n, return 1 - n in lexicographical order.

For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].

Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.
 */
public class LexicographicalNumber {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> r = new ArrayList<>();
        if (n <= 0) {
            return r;
        }
        dfs(0, r, n);
        return r;
    }

    private void dfs(int cur, List<Integer> r, int n) {
        if (cur > n) {
            return;
        }
        if (cur > 0) {
            r.add(cur);
        }
        int start = cur == 0 ? 1 : 0;
        for (int i = start; i <= 9; i++) {
            dfs(cur * 10 + i, r, n);
        }
    }

    public static void main(String[] args) {
        System.out.println(new LexicographicalNumber().lexicalOrder(120));
    }
}
