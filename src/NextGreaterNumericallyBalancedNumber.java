public class NextGreaterNumericallyBalancedNumber {
    public int nextBeautifulNumber(int n) {
        int cand = n+1;
        while(true){
            if(good(cand)){
                return cand;
            }
            ++cand;
        }
    }

    private boolean good(int n){
        int[] count = new int[10];
        while(n>0){
            ++count[n%10];
            n/=10;
        }
        for(int i=0; i<10; ++i){
            if(count[i]==0){
                continue;
            }
            if(count[i] != i){
                return false;
            }
        }
        return true;
    }
}
