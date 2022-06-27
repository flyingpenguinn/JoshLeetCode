public class MaxScoreOfSplicedArray {
    public int maximumsSplicedArray(int[] a, int[] b) {
        return Math.max(solve(a,b), solve(b,a));
    }

    private int solve(int[] a, int[] b){
        int n = a.length;
        int[] v1 = new int[n];
        int sum1 = 0;
        for(int i=0; i<n; ++i){
            v1[i] = b[i]-a[i];
            sum1 += a[i];
        }
        int b1 = kadane(v1);
        int res1 = sum1+b1;
        return res1;
    }

    private int kadane(int[] a){
        int n = a.length;
        int cur = 0;
        int res = 0;
        for(int i=0; i<n; ++i){
            if(cur<0){
                cur = a[i];
            }else{
                cur += a[i];
            }
            res = Math.max(cur, res);
        }
        return res;
    }
}
