import java.util.Arrays;

public class ShortestDistanceToChar {
    public int[] shortestToChar(String s, char t) {
        int last = -1;
        int n = s.length();
        int[] res = new int[n];
        Arrays.fill(res, Integer.MAX_VALUE);
        for(int i=0; i<n; i++){
            char c = s.charAt(i);
            if(c==t){
                res[i] = 0;
                last = i;
            }else{
                if(last!= -1){
                    res[i] = i-last;
                }
            }
        }
        last = -1;
        for(int i=n-1; i>=0; i--){
            char c = s.charAt(i);
            if(c==t){
                last = i;
            }else{
                if(last!= -1){
                    res[i] = Math.min(res[i], last - i);
                }
            }
        }
        return res;
    }
}
