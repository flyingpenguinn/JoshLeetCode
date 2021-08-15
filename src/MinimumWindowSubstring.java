import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
LC#76
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
Note:

If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class MinimumWindowSubstring {
    // can use a better checking mechanism like note down the gap of tm and sm
    // note the diff from min window subsequence- there we need to include a subseq while where we include a bag
    // sliding window find min template. this counts the min window ENDING at each i
    private int max = (int) 1e9;
    boolean cover(Map<Character,Integer> sm, Map<Character,Integer> tm){
        for(char tk: tm.keySet()){
            if(sm.getOrDefault(tk,0)<tm.get(tk)){
                return false;
            }
        }
        return true;
    }
    public String minWindow(String s, String t) {
        Map<Character,Integer> tm = new HashMap<>();
        for(char tc: t.toCharArray()){
            tm.put(tc, tm.getOrDefault(tc, 0)+1);
        }
        int n = s.length();
        Map<Character,Integer> sm = new HashMap<>();
        int res = max;
        int resj=-1;
        int j=0;
        for(int i=0; i<n; i++){
            char ic = s.charAt(i);
            sm.put(ic, sm.getOrDefault(ic, 0)+1);
            while(cover(sm, tm)){
                if(i-j+1<res){
                    res = i-j+1;
                    resj = j;
                }
                char jc = s.charAt(j);
                sm.put(jc, sm.get(jc)-1);
                ++j;
            }
        }
        return res>=max? "": s.substring(resj, resj+res);
    }
}
