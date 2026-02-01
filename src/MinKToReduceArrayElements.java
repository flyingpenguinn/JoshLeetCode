public class MinKToReduceArrayElements {
    public int minimumK(int[] a) {
        int n = a.length;
        long l = 1;
        long u = 0;
        for(int ai: a){
            u += ai;
        }
        while(l<=u){
            long mid = l+(u-l)/2;
            long res = 0;
            for(int i=0; i<n; ++i){
                long times = (long)(Math.ceil(a[i]*1.0/mid));
                res += times;
            }
            if(res<=mid*mid){
                u = mid-1;
            }else{
                l = mid+1;
            }
        }
        return (int)l;
    }
}
