public class MinimumBishopMovesToReachTarget {
    public int minBishopMoves(int[] s, int[] t) {
        if((s[0]+s[1])%2 != (t[0]+t[1])%2){
            return -1;
        }
        if(s[0]+s[1] == t[0]+t[1]){
            return 1;
        }
        if(s[0]-s[1] == t[0]-t[1]){
            return 1;
        }
        return 2;
    }
}
