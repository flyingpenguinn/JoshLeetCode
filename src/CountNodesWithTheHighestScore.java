import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CountNodesWithTheHighestScore {
    private Set<Integer>[] tree;
    private int[] size;
    long max = 0;
    private int maxCount = 0;
    private int nodes = 0;
    public int countHighestScoreNodes(int[] parents) {
        int n = parents.length;
        size  = new int[n];
        tree  = new HashSet[n];
        for(int i=0; i<n; ++i){
            tree[i] = new HashSet<>();
        }
        for(int i=0; i<n; ++i){
            if(parents[i] != -1){
                tree[parents[i]].add(i);
            }
        }
        nodes = count(0);
        dfs(0);
        return maxCount;
    }

    private int count(int i){
        int res = 1;
        for(int ne: tree[i]){
            res += count(ne);
        }
        size[i] = res;
        return res;
    }

    private void dfs(int i){
        int csize = size[i];
        int other = nodes-csize;
        long cres = other==0?1:other;
        for(int ne: tree[i]){
            dfs(ne);
            cres *= (size[ne]==0?1:size[ne]);

        }
        if(cres>max){
            max = cres;
            maxCount = 1;
        }else if(cres==max){
            ++maxCount;
        }
    }
}
