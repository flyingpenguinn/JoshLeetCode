import java.util.Arrays;

public class MinimizeMaxProductDistributedToStore {
    public int minimizedMaximum(int n, int[] a) {
        long l = 1;
        long u = 0;
        for(int ai: a){
            u += ai;
        }
        while(l<=u){
            long mid = l+(u-l)/2;
            if(cando(a, n,mid)){
                u = mid-1;
            }else{
                l = mid+1;
            }
        }
        return (int) l;
    }

    private boolean cando(int[] a, long n, long t){
        long res = 0;
        for(int i=0; i<a.length; ++i){
            long stores = (int) Math.ceil(a[i]*1.0/t);
            res += stores;
            if(res>n){
                return false;
            }
        }
        return true;
    }
}
