import java.util.ArrayList;
import java.util.List;

/*
LC#1286

Design an Iterator class, which has:

A constructor that takes a string characters of sorted distinct lowercase English letters and a number combinationLength as arguments.
A function next() that returns the next combination of length combinationLength in lexicographical order.
A function hasNext() that returns True if and only if there exists a next combination.


Example:

CombinationIterator iterator = new CombinationIterator("abc", 2); // creates the iterator.

iterator.next(); // returns "ab"
iterator.hasNext(); // returns true
iterator.next(); // returns "ac"
iterator.hasNext(); // returns true
iterator.next(); // returns "bc"
iterator.hasNext(); // returns false


Constraints:

1 <= combinationLength <= characters.length <= 15
There will be at most 10^4 function calls per test.
It's guaranteed that all calls of the function next are valid.
 */
public class IteratorCombination {
    static class CombinationIterator {
        int[] cur;
        char[] cs;
        boolean cached = true;


        public CombinationIterator(String characters, int combinationLength) {
            cs = characters.toCharArray();
            cur = new int[combinationLength];
            for (int i = 0; i < combinationLength; i++) {
                cur[i] = i;
            }
        }

        // basically a +1
        public String next() {
            if (cached) {
                cached = false;
                return output(cur);
            }
            int n = cur.length;
            int cn = cs.length;
            for (int i = n - 1; i >= 0; i--) {
                int fromBack = n - 1 - i;
                // cur[n-] has limit as cn-1
                // cur[n-2] has limit as cn-2
                if (cur[i] < cn - 1 - fromBack) {
                    cur[i]++;
                    for (int j = i + 1; j < n; j++) {
                        cur[j] = cur[j - 1] + 1;
                    }
                    return output(cur);
                }
            }
            return null;
        }

        private String output(int[] cur) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < cur.length; i++) {
                sb.append(cs[cur[i]]);
            }
            return sb.toString();
        }

        public boolean hasNext() {
            if (cached) {
                return true;
            }
            String next = next();
            if (next != null) {
                cached = true;
            }
            return next != null;
        }
    }

    public static void main(String[] args) {
        CombinationIterator iterator = new CombinationIterator("chpqi", 3); // creates the iterator.
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
}
