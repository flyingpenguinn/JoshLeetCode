import java.util.Arrays;

public class MergeCloseCharactersII {
    public String mergeCharacters(String s, int len) {
        int n = s.length();
        int[] last = new int[26];
        StringBuilder sb = new StringBuilder();
        Arrays.fill(last, -1);
        int removed = 0;
        for(int i=0; i<n; ++i){
            char c= s.charAt(i);
            int cind = s.charAt(i)-'a';
            int realindex = i-removed;
            if(last[cind] != -1 && last[cind]+len>=realindex){
                ++removed;
            }
            else{
                last[cind] = realindex;
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
