public class LongetBinarySubsequenceNoBiggerThank {
    public int longestSubsequence(String s, int k) {
        int n = s.length();
        long cur = 0;
        int res = 0;
        for(int i=n-1; i>=0; --i){
            int v = s.charAt(i)-'0';
            if(v==0){
                ++res;
            }else{
                if(res<=31 && cur + (1L<<res) <=k){
                    cur += (1<<res);
                    ++res;
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new LongetBinarySubsequenceNoBiggerThank().longestSubsequence("1011", 281854076));
    }
}
