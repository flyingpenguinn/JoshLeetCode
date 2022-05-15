import java.util.Arrays;

public class MinKeyPresses {
    public int minimumKeypresses(String s) {
        int n = s.length();
        int[] m = new int[26];
        for(int i=0; i<n; ++i){
            char c = s.charAt(i);
            int cind = c-'a';
            ++m[cind];
        }
        Arrays.sort(m);

        int hits = 1;
        int rem = 9;
        int res = 0;
        for(int i=25; i>=0 && m[i] > 0; --i){
            res += hits*m[i];
            --rem;
            if(rem==0){
                ++hits;
                rem = 9;
            }
        }
        return res;
    }
}
