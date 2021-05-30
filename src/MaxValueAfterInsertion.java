public class MaxValueAfterInsertion {

    public String maxValue(String n, int x) {
        if(n.charAt(0) =='-'){
            return solve(n, x, false);
        }
        return solve(n, x, true);
    }

    private String solve(String s, int x, boolean ispos){
        for(int i=0; i<s.length(); i++ ){
            int cind = s.charAt(i)-'0';
            if(ispos && cind < x || !ispos && cind>x){
                return s.substring(0, i)+x+s.substring(i);
            }
        }
        s += x;
        return s;
    }

}
