public class CountAsterisks {
    public int countAsterisks(String s) {
        int n = s.length();
        int bars = 0;
        int res = 0;
        for(int i=0; i<n; ++i){
            char c = s.charAt(i);
            if(c=='|'){
                ++bars;
            }else if(c=='*'){
                if(bars%2==0){
                    ++res;
                }
            }
        }
        return res;
    }
}
