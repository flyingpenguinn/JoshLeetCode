import base.ArrayUtils;

import java.util.*;

public class AllAncestorsOfNode {
    private Map<Integer, Set<Integer>> res = new HashMap<>();
    private Map<Integer, List<Integer>> g = new HashMap<>();
    private Set<Integer> seen = new HashSet<>();
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        for(int[] e: edges){
            int start = e[0];
            int end = e[1];
            g.computeIfAbsent(end, k-> new ArrayList<>()).add(start);
        }
        for(int k: g.keySet()){
            if(!seen.contains(k)){
                dfs(k, new ArrayList<>());
            }
        }
        List<List<Integer>> rr = new ArrayList<>();
        for(int i=0; i<n; ++i){
            List<Integer> ri = new ArrayList<>(res.getOrDefault(i, new HashSet<>()));
            Collections.sort(ri);
            rr.add(ri);
        }
        return rr;
    }

    private void dfs(int i, List<Integer> parents){
        // System.out.println(i+" "+parents);
        seen.add(i);
        for(int p: parents){
            System.out.println(p+" "+" "+i);
            res.computeIfAbsent(p, k-> new HashSet<>()).add(i);
        }
        parents.add(i);

        for(int ne: g.getOrDefault(i, new ArrayList<>())){
            if(!seen.contains(ne)){
                dfs(ne, parents);
            }else{
                Set<Integer> cne = res.getOrDefault(ne, new HashSet<>());

                cne.add(ne);
                System.out.println(i+" "+ne+" "+cne);
                res.computeIfAbsent(i, k->new HashSet<>()).addAll(cne);
            }
        }
        parents.remove(parents.size()-1);
    }

    public static void main(String[] args) {
        System.out.println(new AllAncestorsOfNode().getAncestors(8, ArrayUtils.read("[[0,3],[0,4],[1,3],[2,4],[2,7],[3,5],[3,6],[3,7],[4,6]]")));
    }
}
