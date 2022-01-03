public class CheckIfAllAsBeforeAllBs {
    public boolean checkString(String s) {
        int n = s.length();
        int maxa = -1;
        int minb = n+1;
        for(int i=0; i<n; ++i){
            char sc = s.charAt(i);
            if(sc=='a'){
                maxa = Math.max(maxa, i);
            }else{
                minb = Math.min(minb, i);
            }
        }
        return maxa < minb;
    }
}
