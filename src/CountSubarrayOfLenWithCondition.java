public class CountSubarrayOfLenWithCondition {
    public int countSubarrays(int[] a){
        int n = a.length;
        int res = 0;
        for(int i=0; i+2<n;++i){
            int v1 = a[i];
            int v2 = a[i+1];
            int v3 = a[i+2];
            if(2*(v1+v3)==v2){
                ++res;
            }
        }
        return res;
    }
}
