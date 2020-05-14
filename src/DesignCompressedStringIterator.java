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
public class DesignCompressedStringIterator {
    static
    class StringIterator {
        char c = ' ';
        int pending = 0;
        String s;
        int i = 0;

        public StringIterator(String s) {
            this.s = s;

        }

        public char next() {
            if (hasNext()) {
                pending--;
                return c;
            } else {
                return ' ';
            }
        }

        public boolean hasNext() {
            fetch();
            return pending > 0;
        }

        private void fetch() {
            if (pending > 0) {
                return;
            }
            if (i >= s.length()) {
                return;
            }
            char c = s.charAt(i++);
            int j = i;
            while (i < s.length() && Character.isDigit(s.charAt(i))) {
                i++;
            }
            int times = Integer.valueOf(s.substring(j, i));
            pending = times;
            this.c = c;
            //System.out.println(c+" "+times);
        }
    }
}
