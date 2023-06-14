import java.util.*;

public class MaxSumQueries {
    public int[] maximumSumQueries(int[] a, int[] b, int[][] qs) {
        int n = a.length;
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for(int i=0; i<n;++i){
            int cv = tm.getOrDefault(a[i], 0);
            if(cv<b[i]){
                tm.put(a[i], b[i]);
            }
        }

        int qn = qs.length;
        int[][] nqs = new int[qn][3];
        for(int i=0; i<qn; ++i){
            nqs[i][0] = qs[i][0];
            nqs[i][1] = qs[i][1];
            nqs[i][2] = i;
        }
        Arrays.sort(nqs, (x,y)-> Integer.compare(x[0], y[0]));
        int[] res = new int[n];
        return res;
    }
}
