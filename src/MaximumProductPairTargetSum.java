public class MaximumProductPairTargetSum {
    public int[] maxProductPair(int[] a, int t) {
        int n = a.length;
        int maxp = -100000;
        int[] maxres = new int[]{-1, -1};
        for(int i=0; i<n; ++i){
            for(int j=0; j<n; ++j){
                if(i==j){
                    continue;
                }
                if(a[i]+a[j]==t && a[i]>a[j]){
                    int cp = a[i]*a[j];
                    if(cp>maxp){
                        maxp = cp;
                        maxres = new int[]{i, j};
                    }
                }
            }
        }
        return maxres;
    }
}
