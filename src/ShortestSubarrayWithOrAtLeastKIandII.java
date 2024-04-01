public class ShortestSubarrayWithOrAtLeastKIandII {
    public int minimumSubarrayLength(int[] a, int t) {
        int n = a.length;
        int[] bits = new int[32];
        int j = 0;
        int res = n+1;
        for(int i=0; i<n; ++i){
            int v = a[i];
            for(int k=0; k<32; ++k){
                bits[k] += (((v>>k)&1));
            }
            while(j<=i && good(bits, t)){
                int cur = i-j+1;
                res = Math.min(res, cur);
                int hv = a[j];
                for(int k=0; k<32; ++k){
                    bits[k] -= (((hv>>k)&1));
                }
                ++j;
            }

        }
        return res>n? -1: res;
    }

    private boolean good(int[] bits, int t){
        for(int k=31; k>=0; --k){
            int ti = ((t>>k)&1);
            if(bits[k]>=1 && ti==0){
                return true;
            }else if(bits[k]== 0 && ti>=1){
                return false;
            }
        }
        return true;
    }
}
