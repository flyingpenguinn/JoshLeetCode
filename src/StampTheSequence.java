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
    // we dont really need to stamp the same position twice
    // the ability to stamp somewhere is always good: we have more ? to match with. so there is no backtracking: we always move forward!
    // time complexity n-m * n: we execute the inner most for loop at most n times together with the for loop at the while line
    // abc must be revealed before ab?. but later ones' sequences can't be determined. the ones with more ? may be matched later in the stamping
    public int[] movesToStamp(String stamp, String target) {
        int mark = 0;
        Set<Integer> stamped = new HashSet<>();
        List<Integer> res = new ArrayList<>();
        char[] t = target.toCharArray();
        while (mark != t.length) {
            boolean found = false;
            for (int i = 0; i + stamp.length() - 1 < t.length; i++) {
                if (!stamped.contains(i) && match(t, stamp, i)) {
                    found = true;
                    stamped.add(i);
                    res.add(i);
                    int j = i;
                    for (; j < i + stamp.length(); j++) {
                        if (t[j] != '?') {
                            t[j] = '?';
                            mark++;
                        }
                    }
                    i = j;
                }
            }
            if (!found) {
                return new int[0];
            }
        }
        int[] r = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            r[i] = res.get(res.size() - 1 - i);
        }
        return r;
    }

    private boolean match(char[] t, String s, int i) {
        boolean converted = false;
        for (int j = i; j < i + s.length(); j++) {
            if (t[j] == '?') {
                continue;
            }
            if (t[j] == s.charAt(j - i)) {
                converted = true;
                continue;
            }
            return false;
        }
        return converted;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(Arrays.toString(new StampTheSequence().movesToStamp("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb")));
        System.out.println(System.currentTimeMillis() - start);

    }
}

class StampTheSequenceRecursion {
    private List<Integer> res = new ArrayList<>();
    private Set<Integer> stepped = new HashSet<>();

    public int[] movesToStamp(String s, String t) {
        dfs(s.toCharArray(), t.toCharArray(), 0, new ArrayList<>());
        int[] rt = new int[res.size()];
        for (int i = res.size() - 1; i >= 0; i--) {
            rt[rt.length - 1 - i] = res.get(i);
        }
        return rt;
    }

    private void dfs(char[] s, char[] t, int done, List<Integer> cur) {
        if (done == t.length) {
            res = new ArrayList<>(cur);
            return;
        }
        boolean found = false;
        int mj = -1;
        for (int i = 0; i <= t.length - s.length; i++) {
            if (stepped.contains(i)) {
                continue;
            }
            if (match(t, i, s)) {
                mj = i;
                break;
            }
        }
        if (mj == -1) {
            return;
        }
        int newdone = done;
        char[] nt = Arrays.copyOf(s, s.length);
        for (int j = mj; j < mj + s.length; j++) {
            nt[j - mj] = t[j];
            if (t[j] != '?') {
                newdone++;
                t[j] = '?';
            }
        }
        stepped.add(mj);
        cur.add(mj);
        dfs(s, t, newdone, cur);
        cur.remove(cur.size() - 1);
        stepped.remove(mj);
        for (int j = mj; j < mj + s.length; j++) {
            t[j] = nt[j - mj];
        }
    }

    private boolean match(char[] t, int i, char[] s) {
        boolean converted = false;
        for (int j = i; j < i + s.length; j++) {
            if (t[j] == '?') {
                continue;
            }
            if (t[j] == s[j - i]) {
                converted = true;
                continue;
            }
            return false;
        }
        return converted;
    }
}