import java.util.Arrays;

public class MinimumQueenMovesToReachTarget {
    public int minQueenMoves(int[] s, int[] t) {
        if(Arrays.equals(s,t)){
            return 0;
        }
        if(s[0]==t[0] || s[1] == t[1]){
            return 1;
        }
        if(s[0]-s[1] == t[0]-t[1]){
            return 1;
        }
        if(s[0]+s[1] == t[0]+t[1]){
            return 1;
        }
        return 2;
    }
}
