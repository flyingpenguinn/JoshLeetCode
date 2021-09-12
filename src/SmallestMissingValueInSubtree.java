import java.util.*;

public class SmallestMissingValueInSubtree {
    // only traversing the path from 1 to root. Use visited to avoid duplications
    private Map<Integer, Set<Integer>> g = new HashMap<>();
    private Set<Integer> visited = new HashSet<>();
    public int[] smallestMissingValueSubtree(int[] p, int[] nums) {
        int n = p.length;
        for(int i=0; i<n; ++i){
            g.computeIfAbsent(p[i], k-> new HashSet<>()).add(i);
        }
        int[] res = new int[n];
        Arrays.fill(res, 1);
        int i = -1;
        for(int j=0; j<n; ++j){
            if(nums[j]==1){
                i = j;
                break;
            }
        }
        int missing = 1;
        while(i != -1){
            dfs(nums, i);
            while(visited.contains(missing)){
                ++missing;
            }
            res[i] = missing;
            i = p[i];
        }
        return res;
    }

    private void dfs(int[] nums, int i){
        visited.add(nums[i]);
        for(int ne: g.getOrDefault(i, new HashSet<>())){
            if(!visited.contains(nums[ne])){
                dfs(nums,ne);
            }
        }
    }
}
