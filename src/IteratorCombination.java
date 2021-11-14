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
    class CombinationIterator {
        private int n;
        private String s;
        private int[] dig;
        int chars = 0;
        boolean done = false;

        public CombinationIterator(String str, int len) {
            this.s = str;
            n = len;
            chars = s.length();
            dig = new int[n];
            for (int i = 0; i < n; ++i) {
                dig[i] = i;
            }
        }

        public String next() {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < n; ++i) {
                res.append(s.charAt(dig[i]));
            }
            if (dig[n - 1] + 1 < chars) {
                ++dig[n - 1];
            } else {
                int carry = 1;
                int i = n - 1;
                for (; i >= 0; --i) {
                    dig[i] += carry;
                    if (chars - dig[i] >= n - i) {
                        break;
                    }
                }
                if (i == -1) {
                    done = true;
                } else {
                    ++i;
                    for (; i < n; ++i) {
                        dig[i] = dig[i - 1] + 1;
                    }
                }
            }
            return res.toString();
        }

        public boolean hasNext() {
            return !done;
        }
    }
}
