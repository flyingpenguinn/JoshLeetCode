import java.util.TreeSet;

public class DepthOfBstGivenInsertionOrder {
    // it must be between two existing numbers
    // and it must be child of the deepest node among the two!
    public int maxDepthBST(int[] a) {
        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(a[0]);
        int n = a.length;
        int[] depth = new int[n+1];
        depth[a[0]] = 1;
        int res=1;
        for(int i=1; i<n; i++){
            Integer lower = ts.lower(a[i]);
            Integer higher = ts.higher(a[i]);
            if(lower==null){
                depth[a[i]] = depth[higher]+1;
            }else if(higher == null){
                depth[a[i]] = depth[lower]+1;
            }else{
                depth[a[i]] = Math.max(depth[higher], depth[lower])+1;
            }
            ts.add(a[i]);
            res = Math.max(res, depth[a[i]]);
        }
        return res;
    }
}
