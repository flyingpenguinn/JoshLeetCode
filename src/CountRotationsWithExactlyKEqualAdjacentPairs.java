public class CountRotationsWithExactlyKEqualAdjacentPairs {
    private int count(String s){
        int res = 0;
        int n = s.length();
        for(int i=0; i+1<n; ++i){
            if(s.charAt(i) == s.charAt(i+1)){
                ++res;
            }
        }
        return res;
    }
    public int countRotations(String s, int k) {
        int n = s.length();
        int res = 0;
        for(int len=0; len<=n-1; ++len){
            int end = len-1;
            String sub = s.substring(0, end+1);
            String later = s.substring(end+1);
            String cur = later + sub;
          //  System.out.println(sub+" "+later+" "+cur);
            int score = count(cur);
            if(score==k){
                ++res;
            }
        }
        return res;
    }
}
