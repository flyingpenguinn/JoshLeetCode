public class CheckIfNumberHasEqualDigitValueAndCount {
    public boolean digitCount(String num) {
        int n = num.length();
        if(n>10){
            return false;
        }
        int[] digs = new int[10];
        for(int i=0; i<n; ++i){
            int cind = num.charAt(i)-'0';
            ++digs[cind];
        }

        for(int i=0; i<n; ++i){
            if(digs[i] != (num.charAt(i)-'0')){
                return false;
            }
        }
        return true;
    }
}
