import base.ArrayUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SumOfMatrixAfterQueries {
    public long matrixSumQueries(int n, int[][] queries) {
        Set<Integer> seenr = new HashSet<>();
        Set<Integer> seenc = new HashSet<>();
        int qn = queries.length;
        long res = 0;
        for(int i=qn-1; i>=0; --i){
            int[] q = queries[i];
            int type = q[0];
            int index = q[1];
            int v = q[2];
            long gain = 0;
            if(type==0){
                if(seenr.contains(index)){
                    continue;
                }
                gain = (n-seenc.size())*v;
                seenr.add(index);
            }else{
                if(seenc.contains(index)){
                    continue;
                }
                gain = (n-seenr.size())*v;
                seenc.add(index);
            }
            res += gain;
         //   System.out.println(res);
        }
        return res;
    }

    public long matrixSumQueries2(int n, int[][] queries) {
        long[][] a = new long[n][n];
        int sum = 0;
        for(int[] q: queries){
            int type = q[0];
            int index = q[1];
            int nv = q[2];
            if(type==0){
                for(int j=0; j<n; ++j){
                    a[index][j] = nv;
                }
            }else{
                for(int i=0; i<n; ++i){
                    a[i][index] = nv;
                }
            }
            sum = 0;
            for(int i=0; i<n; ++i){
                for(int j=0; j<n; ++j){
                    sum += a[i][j];
                }
            }
            System.out.println(sum);
        }
        return sum;
    }

    public static void main(String[] args) {
        //System.out.println(new SumOfMatrixAfterQueries().matrixSumQueries(3, ArrayUtils.read("[[0,0,1],[1,2,2],[0,2,3],[1,0,4]]")));
        System.out.println(new SumOfMatrixAfterQueries().matrixSumQueries(8, ArrayUtils.read("[[0,6,30094],[0,7,99382],[1,2,18599],[1,3,49292],[1,0,81549],[1,1,38280],[0,0,19405],[0,4,30065],[1,4,60826],[1,5,9241],[0,5,33729],[0,1,41456],[0,2,62692],[0,3,30807],[1,7,70613],[1,6,9506],[0,5,39344],[1,0,44658],[1,1,56485],[1,2,48112],[0,6,43384]]")));
    }
}
