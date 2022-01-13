import java.util.HashMap;
import java.util.Map;

public class LongestPalindromeByConcatTwoLetterWords {
    public int longestPalindrome(String[] words) {
        Map<String, Integer> m = new HashMap<>();
        int res = 0;
        int unpaired = 0;
        for(String s: words){
            String rev = new StringBuilder(s).reverse().toString();
            if(s.equals(rev)){
                if(m.containsKey(rev)){
                    res += 4;
                    update(m, rev, -1);
                    --unpaired;
                }else{
                    update(m, rev, 1);
                    ++unpaired;
                }
            }else{
                if(m.containsKey(rev)){
                    res +=4;
                    update(m, rev, -1);
                }else{
                    update(m, s, 1);
                }
            }

        }
        if(unpaired>0){
            res +=2;
        }
        return res;
    }

    private void update(Map<String,Integer> m, String k, int d){
        int nv = m.getOrDefault(k, 0)+d;
        if(nv<=0){
            m.remove(k);
        }else{
            m.put(k, nv);
        }
    }
}
