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
    private List<Integer> res = new ArrayList<>();
    private Set<Integer> stamped = new HashSet<>();
    private int cur = 0;
    private boolean bad = false;
    public int[] movesToStamp(String s, String t) {
        stamp(t.toCharArray(),s);
        if(bad){
            return new int[0];
        }
        Collections.reverse(res);
        int[] rr = new int[res.size()];
        for(int i=0; i<res.size(); i++){
            rr[i] = res.get(i);
        }
        return rr;
    }

    // use t to stamp s
    private void stamp(char[] s, String t){
        if(cur == s.length){
            return;
        }
        int i = 0;
        boolean found = false;
        while(i+t.length()-1<s.length && cur<s.length){
            if(!stamped.contains(i) && startsWith(s, t, i)){
                found = true;
                for(int j=0; j<t.length(); j++){
                    if(s[i+j] != '?'){
                        s[i+j] = '?';
                        cur++;
                    }
                }
                res.add(i);
                stamped.add(i);
            }
            i++;
        }
        if(!found){
            bad = true;
            return;
        }
        stamp(s, t);
    }

    private boolean startsWith(char[] s, String t, int i){
        if(i+t.length()-1>=s.length){
            return false;
        }
        boolean allqs = true;
        for(int j=0; j<t.length(); j++){
            if(s[i+j]==t.charAt(j)){
                allqs = false;
                continue;
            }else if(s[i+j]=='?'){
                continue;
            }
            return false;
        }
        return !allqs;
    }
}