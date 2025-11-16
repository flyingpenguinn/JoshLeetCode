public class MinimizeStringLengthAfterBalancedRemovals {
    public int minLengthAfterRemovals(String s) {
        int ca = 0;
        int cb = 0;
        for(char c: s.toCharArray()){
            if(c=='a'){
                ++ca;
            }else{
                ++cb;
            }
        }
        return Math.abs(ca-cb);
    }
}
