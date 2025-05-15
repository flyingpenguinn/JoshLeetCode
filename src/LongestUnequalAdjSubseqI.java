import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LongestUnequalAdjSubseqI {
    // I and II are different p problems! for I just greedy
    public List<String> getLongestSubsequence(String[] words, int[] groups) {
        int n = words.length;
        int prev = -1;
        List<String> res = new ArrayList<>();
        for(int i=0; i<n; ++i){
            if(groups[i] != prev){
                res.add(words[i]);
                prev = groups[i];
            }
        }
        return res;
    }
}
