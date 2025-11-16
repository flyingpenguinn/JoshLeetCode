public class MaximizeExpressionOfThreeElements {
    public int maximizeExpressionOfThree(int[] a) {
        int n = a.length;
        int max = (int)-1e9;
        for(int i=0; i<n; ++i){
            for(int j=0; j<n; ++j){
                for(int k=0; k<n; ++k){
                    if(i==j || j==k || i==k){
                        continue;
                    }
                    int cur = a[i]+a[j]-a[k];
                    max = Math.max(max, cur);
                }
            }
        }
        return max;
    }
}
