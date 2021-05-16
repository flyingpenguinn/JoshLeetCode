public class SumOfSubsetXor {
    public int subsetXORSum(int[] a) {
        int n = a.length;
        int res = 0;
        for(int i=0; i<(1<<n); i++){
            int xors = 0;
            for(int j=0; j<n; j++){
                if(((i>>j)&1)==1){
                    xors ^= a[j];
                }
            }
            res += xors;
        }
        return res;
    }
}
