public class CountFertilePyramidsInLand {
    public int countPyramids(int[][] a){
        int m = a.length;
        int n = a[0].length;
        int[][] sum = new int[m][n];
        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j){
                if(a[i][j]==0){
                    sum[i][j] = 0;
                }else{
                    sum[i][j] = (j==0?0:sum[i][j-1])+1;
                }
            }
        }
        int res = 0;
        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j){
                if(a[i][j]==1){
                    int k = i;
                    int p = j;
                    int t = 1;
                    while(k<m && p<n && sum[k][p]>=t){
                        ++k;
                        ++p;
                        t += 2;
                    }
                    if(k>i+1){
                        res += k-i-1;
                    }
                    k=i;
                    p = j;
                    t=1;
                    while(k>=0 && p<n && sum[k][p]>=t){
                        --k;
                        ++p;
                        t+=2;
                    }

                    if(k<i-1){
                        res += i-1-k;
                    }
                }
            }
        }
        return res;
    }
}
