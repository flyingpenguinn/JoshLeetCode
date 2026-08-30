public class AltAndTabSimulation {
    public int[] simulationResult(int[] a, int[] qs) {
        int n = a.length;
        int[] res = new int[n];
        int ri = 0;
        boolean[] seen = new boolean[n+1];
        for(int i=qs.length-1; i>=0; --i){
            int v = qs[i];
            if(!seen[v]){ res[ri++] = v; seen[v] = true; }
        }
        for(int i=0; i<n; ++i){
            if(!seen[a[i]]){ res[ri++] = a[i]; seen[a[i]] = true; }
        }
        return res;
    }
}
