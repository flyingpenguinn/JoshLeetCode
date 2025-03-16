import java.util.*;

public class CloestElementQueries {
    public List<Integer> solveQueries(int[] a, int[] qs) {
        int n = a.length;
        Map<Integer,TreeSet<Integer>> m = new HashMap<>();
        for(int i=0; i<n; ++i){
            m.computeIfAbsent(a[i], p-> new TreeSet<>()).add(i);
        }
        int qn = qs.length;
        List<Integer> res = new ArrayList<>();
        for(int i=0; i<qn; ++i){
            int q = qs[i];
            int v = a[q];
            TreeSet<Integer> idx = m.get(v);
            if(idx == null || idx.size()==1){
                res.add(-1);
            }else{
                int cur = 2*n;
                Integer higher = idx.higher(q);
                if(higher != null){
                    int cv = higher - q;
                    cur = Math.min(cur, cv);
                }
                Integer lower = idx.lower(q);
                if(lower!= null){
                    int cv2 = q-lower;
                    cur = Math.min(cur, cv2);
                }
                Integer first = idx.first();
                if(first != null){
                    int cv3 = n-q+first;
                    cur = Math.min(cur, cv3);
                }
                Integer last = idx.last();
                if(last != null){
                    int cv4 = n-last + q;
                    cur = Math.min(cur, cv4);
                }
                res.add(cur);
            }

        }
        return res;
    }
}
