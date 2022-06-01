import base.ArrayUtils;

import java.util.*;

public class ClosestNodeToPathInTree {
    // bfs from one node to all nodes in On
    // dfs to get path between 2 nodes On
    private Map<Integer, List<Integer>> tree = new HashMap<>();
    public int[] closestNode(int n, int[][] edges, int[][] query) {
        for(int[] e: edges){
            int v1 = e[0];
            int v2 = e[1];
            tree.computeIfAbsent(v1, k-> new ArrayList<>()).add(v2);
            tree.computeIfAbsent(v2, k-> new ArrayList<>()).add(v1);
        }
        int[] res = new int[query.length];
        int ri = 0;
        for(int[] q: query){
            int v1 = q[0];
            int v2 = q[1];
            int v3 = q[2];
            int[] dist = shortest(v3, n);
            List<Integer> vs = new ArrayList<>();
            dfs(v1, v2, -1, vs);
            int mind = (int) 1e9;
            int mini = -1;
            for(int i=0; i<vs.size(); ++i){
                int v = vs.get(i);
                if(dist[v] < mind){
                    mind = dist[v];
                    mini = v;
                }
            }
            res[ri++] = mini;
        }
        return res;
    }

    private boolean dfs(int v, int v2, int p, List<Integer> vs ) {
        vs.add(v);
        if(v==v2){
            return true;
        }
        for(int ne: tree.getOrDefault(v, new ArrayList<>())){
            if(ne==p){
                continue;
            }
            boolean ner = dfs(ne, v2, v, vs);
            if(ner){
                return true;
            }
        }
        vs.remove(vs.size()-1);
        return false;
    }

    private int[] shortest(int v, int n) {
        Deque<int[]> q = new ArrayDeque<>();
        Set<Integer> seen = new HashSet<>();
        int[] dist = new int[n];
        q.offer(new int[]{v, 0});
        seen.add(v);
        while(!q.isEmpty()){
            int[] top = q.poll();
            int i = top[0];
            int d = top[1];
            dist[i] = d;
            for(int ne: tree.getOrDefault(i, new ArrayList<>())){
                if(!seen.contains(ne)){
                    seen.add(ne);
                    q.offer(new int[]{ne, d+1});
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new ClosestNodeToPathInTree().closestNode(7, ArrayUtils.read("[[0,1],[0,2],[0,3],[1,4],[2,5],[2,6]]"), ArrayUtils.read("[[5,3,4],[5,3,6]]"))));
    }
}
