/*
LC#604
Design and implement a data structure for a compressed string iterator. It should support the following operations: next and hasNext.

The given compressed string will be in the form of each letter followed by a positive integer representing the number of this letter existing in the original uncompressed string.

next() - if the original string still has uncompressed characters, return the next letter; Otherwise return a white space.
hasNext() - Judge whether there is any letter needs to be uncompressed.

Note:
Please remember to RESET your class variables declared in StringIterator, as static/class variables are persisted across multiple test cases. Please see here for more details.

Example:

StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");

iterator.next(); // return 'L'
iterator.next(); // return 'e'
iterator.next(); // return 'e'
iterator.next(); // return 't'
iterator.next(); // return 'C'
iterator.next(); // return 'o'
iterator.next(); // return 'd'
iterator.hasNext(); // return true
iterator.next(); // return 'e'
iterator.hasNext(); // return false
iterator.next(); // return ' '
 */
public class DsignCompressedStringIter {
    class StringIterator {
        char cur = '*';
        int rem = 0;
        int nc = 0; // char of next
        String cs = null;

        public StringIterator(String cs) {
            this.cs = cs;
        }

        public char next() {
            if (hasNext()) {
                rem--;
                return cur;
            } else {
                return ' ';
            }
        }

        public boolean hasNext() {
            if (rem >= 1) {
                return true;
            } else {
                if (nc == cs.length()) {
                    return false;
                } else {
                    cur = cs.charAt(nc++);
                    rem = 0;

                    while (nc < cs.length() && cs.charAt(nc) >= '0' && cs.charAt(nc) <= '9') {
                        rem = rem * 10 + (cs.charAt(nc) - '0');
                        nc++;
                    }
                    return rem != 0;
                }
            }

        }
    }

}
