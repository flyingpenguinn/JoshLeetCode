/*
LC#844
Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.

Example 1:

Input: S = "ab#c", T = "ad#c"
Output: true
Explanation: Both S and T become "ac".
Example 2:

Input: S = "ab##", T = "c#d#"
Output: true
Explanation: Both S and T become "".
Example 3:

Input: S = "a##c", T = "#a#c"
Output: true
Explanation: Both S and T become "c".
Example 4:

Input: S = "a#c", T = "b"
Output: false
Explanation: S becomes "c" while T becomes "b".
Note:

1 <= S.length <= 200
1 <= T.length <= 200
S and T only contain lowercase letters and '#' characters.
Follow up:

Can you solve it in O(N) time and O(1) space?
 */
public class BackspaceStringComparison {
    // can also go backward and count the #s
    public boolean backspaceCompare(String s, String t) {
        int sn = s.length();
        int tn = t.length();
        int i = sn-1;
        int j = tn-1;
        int counts = 0;
        int countt = 0;
        while(i>=0 || j>=0){
            while(i>=0 && (counts>0 || s.charAt(i)=='#')){
                if(s.charAt(i)=='#'){
                    ++counts;
                }else if(counts>0){
                    --counts;
                }
                --i;
            }
            while(j>=0 && (countt>0 || t.charAt(j)=='#')){
                if(t.charAt(j)=='#'){
                    ++countt;
                }else if(countt>0){
                    --countt;
                }
                --j;
            }
            char si = i==-1? '*': s.charAt(i);
            char sj = j==-1? '*': t.charAt(j);
            if(si!=sj){
                return false;
            }
            --i;
            --j;
        }
        return i<0 && j<0;
    }


    public static void main(String[] args) {
        System.out.println(new BackspaceStringComparison().backspaceCompare("nzp#o#g", "b#nzp#o#g"));
    }
}
