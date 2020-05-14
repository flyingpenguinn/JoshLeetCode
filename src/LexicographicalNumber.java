import java.util.ArrayList;
import java.util.List;

/*
LC#386
Given an integer n, return 1 - n in lexicographical order.

For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].

Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.
 */
public class LexicographicalNumber {
    List<Integer> r = new ArrayList<>();

    public List<Integer> lexicalOrder(int n) {
        dol(0, n);
        return r;
    }

    private void dol(int cur, int n) {
        // given cur, we add nothing, add 1...add 9
        if (cur > 0) {
            r.add(cur);
        }
        int start = cur == 0 ? 1 : 0;
        for (int i = start; i <= 9; i++) {
            int next = cur * 10 + i;
            if (next <= n) {
                dol(next, n);
            } else {
                // early break would make it faster...
                break;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new LexicographicalNumber().lexicalOrder(120));
    }
}
