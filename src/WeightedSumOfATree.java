import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeightedSumOfATree {
    private long res = 0;
    private Map<Integer,List<Integer>> t = new HashMap<>();
    public long weightedSum(int[] parent, int[] nums) {
        int pn = parent.length;
        for(int i=0; i<pn; ++i){
            int p = parent[i];
            if(p==-1){
                continue;
            }
            t.computeIfAbsent(p, k-> new ArrayList<>()).add(i);
        }
        int h = dfs1(0);
    //    System.out.println("h="+h);
        dfs2(0, 1, h, nums);
        return res;
    }

    private int dfs1(int i){
        int maxc = 1;
        for(int ne: t.getOrDefault(i, List.of())){
            int cc = dfs1(ne)+1;
            maxc = Math.max(maxc, cc);
        }
        return maxc;
    }

    private void dfs2(int i, int d, int h, int[] nums){
   //     System.out.println(i+" d="+d);
        long cur = 1L*nums[i] *(h-d+1);
        res +=cur;
        for(int ne: t.getOrDefault(i, List.of())){
            dfs2(ne, d+1, h, nums);
        }

    }
}
