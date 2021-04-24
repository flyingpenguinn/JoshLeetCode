import base.ArrayUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SortItemsByGroupRespectingDependencies {
    // hierarchical topo sorting. as long as group1  has one item -> group 2, the whole group 1 must appear before group2
    // make -1 groups extra standalone single member groups
    // topo sort groups first then within each group sort the items in the same manner
    // beware of empty groups and empty items, stil need to include them!
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> es) {
        Map<Integer, Set<Integer>> gg = new HashMap<>();
        Map<Integer, Set<Integer>> ig = new HashMap<>();
        int[] i2g = new int[n];
        Map<Integer, Set<Integer>> g2i = new HashMap<>();
        for(int i=0; i<n; i++){
            ig.put(i, new HashSet<>());
        }
        findgroup(group, i2g, g2i,gg);
        for(int i=0; i<es.size(); i++){
            List<Integer> frs = es.get(i);
            for(int fr: frs){
                int to = i;
                ig.computeIfAbsent(fr,k-> new HashSet<>()).add(to);
                int fg = i2g[fr];
                int tg = i2g[to];
                gg.computeIfAbsent(fg,k-> new HashSet<>()).add(tg);
            }
        }
        List<Integer> glist = topo(gg);
        if(glist==null){
            return new int[0];
        }
        List<Integer> res = new ArrayList<>();
        for(int i=0; i<glist.size(); i++){
            int gn = glist.get(i);
            Map<Integer, Set<Integer>> subg = subg(gn, ig, g2i.get(gn), i2g);
            List<Integer> ilist = topo(subg);
            if(ilist == null){
                return new int[0];
            }else{
                res.addAll(ilist);
            }
        }
        int[] rr = new int[n];
        for(int i=0; i<n; i++){
            rr[i] = res.get(i);
        }
        return rr;
    }

    private List<Integer> topo(Map<Integer,Set<Integer>> g){
        List<Integer> res = new ArrayList<>();
        Map<Integer,Integer> color = new HashMap<>();
        for(int k: g.keySet()){
            if(!color.containsKey(k)){
                boolean bad = dfs(k, g, res, color);
                if(bad){
                    return null;
                }
            }
        }
        Collections.reverse(res);
        return res;
    }

    // return bad or not
    private boolean dfs(int i, Map<Integer, Set<Integer>> g, List<Integer> res, Map<Integer,Integer> color){
        color.put(i, 1);
        for(int ne: g.getOrDefault(i, new HashSet<>())){
            if(ne==i){
                continue;
            }
            if(!color.containsKey(ne)){
                boolean later = dfs(ne, g, res, color);
                if(later){
                    return true;
                }
            }else if(color.get(ne) == 1){
                return true;
            }
        }
        color.put(i, 2);
        res.add(i);
        return false;
    }

    private Map<Integer, Set<Integer>> subg(int gn, Map<Integer,Set<Integer>> g, Set<Integer> nodes, int[] i2g){
        Map<Integer, Set<Integer>> res = new HashMap<>();
        for(int n: nodes){
            res.put(n, new HashSet<>());
            Set<Integer> nexts= g.getOrDefault(n, new HashSet<>());
            for(int ne: nexts){
                if(i2g[ne] == gn){
                    res.get(n).add(ne);
                }
            }
        }
        return res;
    }

    private void findgroup(int[] group, int[] i2g, Map<Integer, Set<Integer>> g2i,Map<Integer,Set<Integer>> gg){
        int n = group.length;
        int maxseen = 0;
        for(int i=0; i<n; i++){
            if(group[i] != -1){
                int gn = group[i];
                i2g[i] = gn;
                maxseen = Math.max(maxseen, gn);
                g2i.computeIfAbsent(gn,k-> new HashSet<>()).add(i);
                gg.put(gn, new HashSet<>());
            }
        }
        maxseen++;
        for(int i=0; i<n;i++){
            if(group[i] == -1){
                i2g[i] = maxseen;
                g2i.computeIfAbsent(maxseen, k-> new HashSet<>()).add(i);
                gg.put(maxseen, new HashSet<>());
                maxseen++;
            }
        }
    }


    public static void main(String[] args) throws IOException {
        /*
        String file = "E:\\dev\\project\\JoshLeet\\tests\\sol.txt";
        BufferedReader reader = new BufferedReader(new FileReader(new File(file)));
        String n = reader.readLine();
        String m = reader.readLine();
        String groups = reader.readLine();
        String dep = reader.readLine();
        System.out.println(Arrays.toString(new SortItemsByGroupRespectingDependencies().sortItems(Integer.valueOf(n),Integer.valueOf(m), ArrayUtils.read1d(groups), ArrayUtils.readAsListUneven(dep))));

        */

        System.out.println(Arrays.toString(new SortItemsByGroupRespectingDependencies().sortItems(8, 2, ArrayUtils.read1d("[-1,-1,1,0,0,1,0,-1]"), ArrayUtils.readAsListUneven("[[],[6],[5],[6],[3,6],[],[],[]]"))));
        System.out.println(Arrays.toString(new SortItemsByGroupRespectingDependencies().sortItems(8, 2, ArrayUtils.read1d("[-1,-1,1,0,0,1,0,-1]"), ArrayUtils.readAsListUneven("[[],[6],[5],[6],[3],[],[4],[]]"))));
        System.out.println(Arrays.toString(new SortItemsByGroupRespectingDependencies().sortItems(5, 5, ArrayUtils.read1d("[2,0,-1,3,0]"), ArrayUtils.readAsListUneven("[[2,1,3],[2,4],[],[],[]]"))));

    }
}
