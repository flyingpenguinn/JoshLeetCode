import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class MinDeletionsToMakeCharFreqUnique {
    // focus on each char how many times we need to delete it
    public int minDeletions(String s) {
        int[] f = new int[26];
        for(int i=0; i<s.length(); i++){
            f[s.charAt(i)-'a']++;
        }
        Set<Integer> seen = new HashSet<>();
        int res = 0;
        for(int i=0; i<26; i++){
            while(f[i]>0 && seen.contains(f[i])){
                f[i]--;
                res++;
            }
            if(f[i]>0){
                seen.add(f[i]);
            }
        }
        return res;
    }
}
