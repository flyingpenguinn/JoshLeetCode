import java.util.HashMap;
import java.util.TreeMap;

public class ShiftingLettersII {
    public String shiftingLetters(String s, int[][] shifts) {
        int n = s.length();
        char[] sc = s.toCharArray();
        int[] m = new int[n+1];
        for(int i=0; i<shifts.length; ++i){
            int sf = shifts[i][2]==1? 1: -1;
            m[shifts[i][0]] += sf;
            m[shifts[i][1]+1] -= sf;
        }
        int cur = 0;
        for(int i=0; i<n; ++i){
            cur += m[i];
            int cind = sc[i]-'a';
            int rshift = cur%26;
            cind += rshift;
            if(cind<0){
                cind +=26;
            }
            if(cind>=26){
                cind %= 26;
            }
            sc[i] = (char)('a'+cind);
        }
        return new String(sc);
    }

}
