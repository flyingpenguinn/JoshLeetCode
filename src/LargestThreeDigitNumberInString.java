public class LargestThreeDigitNumberInString {
    public String largestGoodInteger(String num) {
        int n = num.length();
        int res = -1;
        String resstr = "";
        for(int i=0; i+3<=n; ++i){
            String cur = num.substring(i, i+3);
            boolean bad = false;
            for(int j=1; j<3; ++j){
                if(cur.charAt(j) != cur.charAt(0)){
                    bad = true;
                    break;
                }
            }
            if(!bad){
                int curnum = Integer.valueOf(cur);
                if(curnum>res){
                    res = curnum;
                    resstr = cur;
                }
            }
        }
        return resstr;
    }
}
