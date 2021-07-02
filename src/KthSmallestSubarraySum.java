public class KthSmallestSubarraySum {
    int kthSmallestSubarraySum(int[] a, int k) {
        int n = a.length;
        int l = 1;
        int u = 1000_000_000;
        while(l<=u){
            int m = l+(u-l)/2;
            int cnt = count(a, m);
            if(cnt>=k){
                u = m-1;
            }else{
                l = m+1;
            }
        }
        return l;
    }

    int count(int[] a, int t){
        int n = a.length;
        int cur=0;
        int res=0;
        int j=0;
        for(int i=0; i<n; i++){
            cur += a[i];
            while(cur > t){
                cur -= a[j++];
            }
            //  j...i
            res += (i-j+1);
        }
        return res;
    }
}
