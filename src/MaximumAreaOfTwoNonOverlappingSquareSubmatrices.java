public class MaximumAreaOfTwoNonOverlappingSquareSubmatrices {
    private int[][] sum;
    private int getsum(int i, int j, int len){
    
        int rt =  sum[i][j] - sum[i-len][j] - sum[i][j-len]+ sum[i-len][j-len];
     //   System.out.println("i="+j+" j="+j+" len="+len+" rt="+rt);
        return rt;
    }

    public int maxArea(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        sum = new int[m+1][n+1];
        for(int i=1; i<=m; ++i){
            for(int j=1; j<=n; ++j){
                sum[i][j] = sum[i-1][j]+sum[i][j-1] - sum[i-1][j-1] + a[i-1][j-1];
            }
        }
     //   System.out.println(Arrays.deepToString(sum));
        int l = 0;
        int u = Math.min(m,n);
        while(l<=u){
            int mid = l+(u-l)/2;
         //   System.out.println("mid="+mid);
            if(good(a, mid)){
                l = mid+1;
            }else{
                u = mid-1;
            }
        }
        return u*u;
    }

    private boolean good(int[][] a, int t){
        int m = a.length;
        int n = a[0].length;
        int area = t*t;
        int[] cand1 = null;
        int[] cand2 = null;
        for(int i=t; i<=m; ++i){
            for(int j=t; j<=n; ++j){
                if(getsum(i, j, t) == area){
                    cand1 = new int[]{i, j};
                }
            }
        }

        for(int j=t; j<=n; ++j){
            for(int i=t; i<m; ++i){
                if(getsum(i, j, t) == area){
                    cand2 = new int[]{i, j};
                }
            }
        }
        for(int i=t; i<=m; ++i){
            for(int j=t; j<=n; ++j){
        
                if(getsum(i, j, t) == area){
                   
                    if(cand1 != null && (Math.abs(i-cand1[0])>=t 
                       || Math.abs(j-cand1[1])>=t)){
                      return true;
                    }
            
                     if(cand2 != null && (Math.abs(i-cand2[0])>=t 
                       || Math.abs(j-cand2[1])>=t)){
                       return true;
                    }
                }
            }
        }
        
        return false;
    }
}
