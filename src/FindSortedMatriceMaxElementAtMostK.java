import base.ArrayUtils;

import java.util.ArrayDeque;
import java.util.Deque;

public class FindSortedMatriceMaxElementAtMostK {
    // calc mono stack length, on row
    // transpose into histogram count sum of areas- this is 2nd step in count matrice of all ones
    public long countSubmatrices(int[][] a, int k) {
        int m = a.length;
        int n = a[0].length;
        long[][] na = new long[m][n];
        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j){
                if(a[i][j]>k){
                    continue;
                }
                if(j>0 && a[i][j]<=a[i][j-1] && a[i][j-1]<=k){
                    na[i][j] = na[i][j-1]+1;
                }else{
                    na[i][j] = 1;
                }
            }
        }
        //  System.out.println(Arrays.deepToString(na));
        long[][] nna = transpose(na);
        //  System.out.println(Arrays.deepToString(nna));
        long res = 0;
        for(int i=0; i<n; i++){
            res += count(nna[i]);
        }
        return res;
    }

    private long[][] transpose(long[][] a){
        int m = a.length;
        int n = a[0].length;
        long[][] res = new long[n][m];
        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j){
                res[j][i] = a[i][j];
            }
        }
        return res;
    }


    private long count(long[] h){
        int n = h.length;
        long[] dp = new long[n];
        long res = 0;
        Deque<Integer> st = new ArrayDeque<>();
        for(int i=0; i<n; i++){
            while(!st.isEmpty() && h[st.peek()]>=h[i]){
                st.pop();
            }
            //pre is the first <
            int pre = st.isEmpty()? -1: st.peek();
            // pre+1...i
            long cur = (pre==-1?0:dp[pre]) + h[i]*(i-pre);
            dp[i]= cur;
            res += cur;
            st.push(i);
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new FindSortedMatriceMaxElementAtMostK().countSubmatrices(ArrayUtils.read(
                "[[2,4],[3,3],[6,4]]"), 4));
    }
}
