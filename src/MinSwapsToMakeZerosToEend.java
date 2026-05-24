public class MinSwapsToMakeZerosToEend {
    public int minimumSwaps(int[] a) {
        int n = a.length;
        int zeros = 0;
        for(int ai: a){
            zeros += ai==0?1:0;
        }
        int res = 0;
        for(int i=n-1; i>=n-zeros; --i){
            if(a[i] != 0){
                ++res;
            }
        }
        return res;
    }
}
