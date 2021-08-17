import java.util.Arrays;

public class FindIfPathExistsInGraph {
    private int[] pa;
    private int[] size;
    public boolean validPath(int n, int[][] edges, int start, int end) {
        pa = new int[n];
        size = new int[n];
        Arrays.fill(pa, -1);
        Arrays.fill(size, 1);
        for(int[] e: edges){
            union(e[0], e[1]);
        }
        return find(start) == find(end);
    }

    private int find(int i){
        if(pa[i]==-1){
            return i;
        }
        int rt = find(pa[i]);
        pa[i] = rt;
        return rt;
    }

    private void union(int i, int j){
        int ri = find(i);
        int rj = find(j);
        if(ri==rj){
            return;
        }
        if(size[ri] >size[rj]){
            pa[rj] = ri;
            size[ri] += size[rj];
        }else{
            pa[ri] = rj;
            size[rj] += size[ri];
        }
    }
}
