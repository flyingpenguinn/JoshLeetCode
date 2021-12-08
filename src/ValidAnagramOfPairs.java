import java.util.*;

public class ValidAnagramOfPairs {
    // Euler path ! or when there is a circuit, euler circuit
    private Map<Integer, Deque<Integer>> g = new HashMap<>();
    private List<Integer> res = new ArrayList<>();
    public int[][] validArrangement(int[][] pairs) {
        Map<Integer,Integer> ind = new HashMap<>();
        Map<Integer,Integer> outd = new HashMap<>();
        for(int[] p: pairs){
            g.computeIfAbsent(p[0], k-> new ArrayDeque<>()).offer(p[1]);
            outd.put(p[0], outd.getOrDefault(p[0], 0)+1);
            ind.put(p[1], ind.getOrDefault(p[1], 0)+1);
        }
        int start = -1;
        int end = -1;
        for(int ik: ind.keySet()){

            if(ind.getOrDefault(ik, 0)-outd.getOrDefault(ik, 0)==1){
                end = ik;
                break;
            }
        }
        for(int ok: outd.keySet()){
            if(outd.getOrDefault(ok, 0)-ind.getOrDefault(ok, 0)==1){
                start = ok;
                break;
            }
        }
        if(start==-1){
            start = outd.keySet().iterator().next();
        }
        dfs(start, end);
        Collections.reverse(res);
        int[][] rr = new int[res.size()-1][2];
        for(int i=0; i<res.size()-1; ++i){
            rr[i][0] = res.get(i);
            rr[i][1] = res.get(i+1);
        }
        return rr;
    }

    private void dfs(int i, int end){

        Deque<Integer> nes = g.getOrDefault(i, new ArrayDeque<>());
        while(!nes.isEmpty()){
            int ne = nes.poll();
            dfs(ne, end);
        }
        res.add(i);
    }
}
