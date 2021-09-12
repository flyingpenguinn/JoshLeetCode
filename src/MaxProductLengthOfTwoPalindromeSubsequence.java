public class MaxProductLengthOfTwoPalindromeSubsequence {
    public int maxProduct(String s) {
        int n = s.length();
        int[] palin = new int[1<<n];
        for(int st=0; st<(1<<n); ++st){
            String str = form(st, s);
            if(ispalin(str)){
                palin[st] = str.length();
            }
        }
        int res = 0;
        for(int st1=0; st1<(1<<n); ++st1){
            if(palin[st1]==0){
                continue;
            }
            for(int st2= 0; st2<(1<<n); ++st2){
                if( (st1&st2)==0 && palin[st2]>0){
                    int cur = palin[st1]*palin[st2];
                    res = Math.max(res, cur);
                }
            }
        }
        return res;
    }

    private boolean ispalin(String str){
        int i = 0;
        int j = str.length()-1;
        while(i<j){
            if(str.charAt(i) != str.charAt(j)){
                return false;
            }
            ++i;
            --j;
        }
        return true;
    }

    private String form(int st, String s){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<s.length(); ++i){
            if(((st>>i)&1)==1){
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
