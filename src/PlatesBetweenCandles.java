import java.util.Arrays;

public class PlatesBetweenCandles {
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int n = s.length();
        int[] v = new int[n];
        for(int i=0; i<n; ++i){
            if(s.charAt(i)=='|'){
                v[i] = 0;
            }else{
                v[i] = 1;
            }
        }
        int[] right = new int[n];
        Arrays.fill(right, -1);
        for(int i=n-1; i>=0; --i){
            if(v[i]==0){
                right[i] = i;
            }else{
                right[i] = i==n-1?-1: right[i+1];
            }
        }
        int[] left = new int[n];
        Arrays.fill(left, -1);
        for(int i=0; i<n; ++i){
            if(v[i]==0){
                left[i] = i;
            }else{
                left[i] = i==0?-1:left[i-1];
            }
        }
        int[] sum = new int[n];
        for(int i=0; i<n; ++i){
            sum[i] = (i==0?0:sum[i-1])+v[i];
        }
        int[] res = new int[queries.length];
        for(int i=0; i<queries.length; ++i){
            int[] q = queries[i];
            int start = right[q[0]];
            if(start==-1 || start>q[1]){
                continue;
            }
            int end = left[q[1]];
            res[i]= sum[end]-(start==0?0: sum[start-1]);
        }
        return res;
    }
}
