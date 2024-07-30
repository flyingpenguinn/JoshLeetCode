import java.util.ArrayDeque;
import java.util.Deque;

public class MinDeletionsToMakeStringBalanced {
    // each time we find b-->a delete that b
    public int minimumDeletions(String s) {
        int bs = 0;
        int n = s.length();
        int res = 0;
        for(int i=0; i<n; ++i){
            if(s.charAt(i)=='a'){
                if(bs>0){
                    --bs;
                    ++res;
                }
            }else if(s.charAt(i) == 'b'){
                ++bs;
            }
        }
        return res;
    }
}
