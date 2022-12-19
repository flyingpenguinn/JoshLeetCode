import java.util.ArrayList;
import java.util.List;

public class CycleLengthQueriesOnTree {
    public int[] cycleLengthQueries(int n, int[][] queries) {
        int[] res = new int[queries.length];
        for(int i=0; i<queries.length; ++i){
            int[] q = queries[i];
            int s = q[0];
            int e = q[1];
            res[i] = calc(s,e);
        }
        return res;
    }

    private int calc(int v1, int v2){
        List<Integer> l1 = new ArrayList<>();
        while(v1>0){
            l1.add(v1);
            v1 = v1/2;
        }
        List<Integer> l2 = new ArrayList<>();
        while(v2>0){
            l2.add(v2);
            v2 = v2/2;
        }
        int p1 = 0;
        int p2 = 0;
        while(l1.size()-p1 > l2.size()-p2){
            ++p1;
        }
        while(l2.size()-p2 > l1.size()-p1){
            ++p2;
        }
        while(!l1.get(p1) .equals(l2.get(p2))){
            ++p1;
            ++p2;
        }
        return p1+p2+1;
    }
}
