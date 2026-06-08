public class SumCompatibleNumberInRangeI {
    public int sumOfGoodIntegers(int n, int k) {
        int res = 0;
        for(int i=Math.max(1, n-k); i<=n+k; ++i){
            if((n&i)==0){
                res += i;
            }
        }
        return res;
    }
}
