public class MaxActiveSectionAfterTradeI {
    public int maxActiveSectionsAfterTrade(String s) {
        s = "1"+s+"1";
        int n = s.length();
        int i = 0;
        int res = 0;
        for(char c: s.toCharArray()){
            if(c=='1'){
                ++res;
            }
        }
        res -= 2;
        int pre = -1;
        int maxflip = 0;
        while(i<n){
            if(s.charAt(i) == '0'){
                int j = i;
                while(j<n && s.charAt(j)=='0'){
                    ++j;
                }
                int clen = j-i;
                if(pre != -1){
                    int flip = pre + clen;
                    maxflip = Math.max(flip, maxflip);
                }
                pre = clen;
                i = j;
            }else {
                ++i;
            }

        }
        return res + maxflip;
    }
}
