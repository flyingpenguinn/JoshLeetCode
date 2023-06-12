public class FindLongestSemiRepetitiveSubstring {
    // two pointer similar to max substring with as most k distinct char, or longest substring without repetition
    public int longestSemiRepetitiveSubstring(String s) {
        int n = s.length();
        char[] a = s.toCharArray();
        int j = 0;
        int count = 0;
        int res = 0;
        for(int i=0; i<n; ++i){
            if(i>0 && a[i]==a[i-1]){
                ++count;
            }
            if(count<2){
                res = Math.max(res, i-j+1);
            }
            while(count>1){
                ++j;
                if(a[j]==a[j-1]){
                    --count;
                }
            }

        }
        return res;
    }
}
