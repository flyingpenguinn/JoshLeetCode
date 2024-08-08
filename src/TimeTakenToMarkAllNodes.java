import java.util.ArrayList;
import java.util.List;

public class TimeTakenToMarkAllNodes {
    // basically farthest one from each node as root. rerooting
    private List<Integer>[] t;

    // max dist from i to any node in its subtree
    private int dfs(int i, int p, int[] d1, int[] d2){
        for(int ne: t[i]){
            if(ne == p){
                continue;
            }
            int dne = ne%2==1? 1:2;
            int rne = dfs(ne, i, d1, d2) + dne;
            if(rne > d1[i]){
                d2[i] = d1[i];
                d1[i] = rne;
            }else if(rne > d2[i]){
                d2[i] = rne;
            }
        }
        //  System.out.println(i+" d1="+d1[i]);
        return d1[i];
    }

    // pdist is the max dist from p to any other node in the tree, and these nodes are not in the subtree of i
    private void dfs2(int i, int p, int pdist, int[] d1, int[] d2, int[] res){
        //  System.out.println(i+" pdist="+pdist);
        res[i] = Math.max(pdist, d1[i]);
        // for i the max dist is either from pdist or from its own subtree
        // pdist already factored in dist from p to i
        for(int ne: t[i]){
            if(ne == p){
                continue;
            }
            int dne2i = i%2==1?1:2;
            int di2ne = ne%2==1?1:2;
            int pdistne1 = pdist + dne2i;
            //for ne possibility one is from parent pdist to others not in i's subtree
            int pdistne2 = 0;
            // another is from ne go through i and go to another subtree of i. here we see which way we should go- either d1 or d2, depending on whether ne is in the subtreethat made d1
            if(d1[i] == di2ne + d1[ne]){
                // pick the dist that is not from the ne subtree
                pdistne2 = d2[i] + dne2i;
            }else{
                pdistne2 = d1[i] + dne2i;
            }
            int pdistne = Math.max(pdistne1, pdistne2);
            //  System.out.println(ne+" pdistne1="+pdistne1+" pdistne2="+pdistne2);
            dfs2(ne, i, pdistne, d1, d2, res);
        }
    }


    public int[] timeTaken(int[][] edges) {
        int n = edges.length+1;
        t = new ArrayList[n];
        for(int i=0; i<n; ++i){
            t[i] = new ArrayList<>();
        }
        for(int[] e: edges){
            int v1 = e[0];
            int v2 = e[1];
            t[v1].add(v2);
            t[v2].add(v1);
        }
        int[] d1 = new int[n];  // max dist if we root on this node
        int[] d2 = new int[n];  // 2nd max dist if we root on this

        dfs (0, -1, d1, d2);
        int[] res = new int[n];
        dfs2(0, -1, 0, d1, d2, res);
        return res;

    }
}


