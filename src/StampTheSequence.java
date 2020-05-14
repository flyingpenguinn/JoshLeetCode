import java.util.*;

/*
LC#936
You want to form a target string of lowercase letters.

At the beginning, your sequence is target.length '?' marks.  You also have a stamp of lowercase letters.

On each turn, you may place the stamp over the sequence, and replace every letter in the sequence with the corresponding letter from the stamp.  You can make up to 10 * target.length turns.

For example, if the initial sequence is "?????", and your stamp is "abc",  then you may make "abc??", "?abc?", "??abc" in the first turn.  (Note that the stamp must be fully contained in the boundaries of the sequence in order to stamp.)

If the sequence is possible to stamp, then return an array of the index of the left-most letter being stamped at each turn.  If the sequence is not possible to stamp, return an empty array.

For example, if the sequence is "ababc", and the stamp is "abc", then we could return the answer [0, 2], corresponding to the moves "?????" -> "abc??" -> "ababc".

Also, if the sequence is possible to stamp, it is guaranteed it is possible to stamp within 10 * target.length moves.  Any answers specifying more than this number of moves will not be accepted.



Example 1:

Input: stamp = "abc", target = "ababc"
Output: [0,2]
([1,0,2] would also be accepted as an answer, as well as some other answers.)
Example 2:

Input: stamp = "abca", target = "aabcaca"
Output: [3,0,1]


Note:

1 <= stamp.length <= target.length <= 1000
stamp and target only contain lowercase letters.
 */
public class StampTheSequence {
    // work backward. set every matching substring to ???. ? matches to anything
    // we dont really need to stamp the sme position twice
    // time complexity n-m * n-m * m
    List<Integer> r = null;
    boolean[] added;


    public int[] movesToStamp(String s, String t) {
        int n = t.length();
        added = new boolean[n];
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        dos(sc, tc, 0, 0, new ArrayList<>());
        if (r == null) {
            return new int[0];
        }
        int[] rr = new int[r.size()];
        for (int i = 0; i < rr.length; i++) {
            rr[i] = r.get(i);
        }
    //    System.out.println("dos = " + doscount + " while = " + whilecount + " sicount = " + sicount);
        return rr;
    }

    int doscount = 0;
    int whilecount = 0;
    int sicount = 0;

    void dos(char[] sc, char[] tc, int steps, int xs, List<Integer> cur) {
        doscount++;

        //System.out.println(Arrays.toString(tc));
        if (xs == tc.length) {
            r = new ArrayList<>(cur);
            Collections.reverse(r);
            return;
        }
        int tn = tc.length;
        int sn = sc.length;
        if (steps == tn - sn + 1) {
            return;
        }
        int j = 0;
        int xsd = 0;
        while (j + sn - 1 < tn) {
            whilecount++;
            if (match(sc, tc, j)) {
                int ch = stamp(tc, j, j + sn - 1);
                if (ch != 0) {
                    xsd += ch;
                    cur.add(j);
                    added[j] = true;
                }
            }
            j++;
        }
        if (xsd == 0) {
            return;
        }
        dos(sc, tc, steps + 1, xs + xsd, cur);
    }


    boolean match(char[] s, char[] t, int j) {
        if (added[j]) {
            return false;
        }
        int sn = s.length;
        int i = 0;
        boolean hasc = false;
        while (i < sn) {
            sicount++;
            if (s[i] == t[j] || t[j] == '?') {
                if (t[j] != '?') {
                    hasc = true;
                }
                i++;
                j++;
                continue;
            }

            return false;
        }

        return hasc;
    }

    int stamp(char[] t, int i, int j) {
        int r = 0;
        for (int k = i; k <= j; k++) {
            if (t[k] != '?') {
                r++;
                t[k] = '?';
            }
        }
        return r;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(Arrays.toString(new StampTheSequence().movesToStamp("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb")));
        System.out.println(System.currentTimeMillis() - start);

    }
}
