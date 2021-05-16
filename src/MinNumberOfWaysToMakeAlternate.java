public class MinNumberOfWaysToMakeAlternate {
    public int minSwaps(String s) {
        int zeros = 0;
        int ones = 0;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i)=='0'){
                zeros++;
            }else{
                ones++;
            }
        }
        if(zeros==ones){
            return Math.min(solve(s, '0'), solve(s, '1'));
        }else if(zeros == ones+1){
            return solve(s, '0');
        }else if(ones == zeros+1){
            return solve(s, '1');
        }else{
            return -1;
        }
    }

    private int solve(String s, char start){
        int i = 0;
        char v = start;
        int res=0;
        while(i<s.length()){
            if(s.charAt(i)!= v){
                res++;
            }
            v= v=='0'?'1':'0';
            i++;
        }
        return res/2;
    }
}
