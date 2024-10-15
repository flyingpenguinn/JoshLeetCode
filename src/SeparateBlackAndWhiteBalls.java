public class SeparateBlackAndWhiteBalls {
    public long minimumSteps(String s) {
        char[] c = s.toCharArray();
        int n = s.length();
        long res = 0;
        int i = n-1;
        int j = n-1;
        while(i>=0 && j>=0){
            while(j>=0 && s.charAt(j)=='0'){
                --j;
            }
            if(j>=0){
                long cur = i-j;
                res += cur;
            }
            --i;
            --j;
        }
        return res;
    }
}
