import java.util.Arrays;

public class ProcessRestrictedFriendRequests {
    private int[] pa;
    private int find(int i){
        if(pa[i]==-1){
            return i;
        }
        int ans = find(pa[i]);
        pa[i] = ans;
        return ans;
    }

    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        pa = new int[n];
        for(int i=0; i<n; ++i){
            pa[i] = -1;
        }
        boolean[] res = new boolean[requests.length];
        int rp = 0;
        for(var req: requests){
            int[] opa= Arrays.copyOf(pa, n);
            int i = req[0];
            int j = req[1];
            int ai = find(i);
            int aj = find(j);
            if(ai==aj){
                res[rp++] = true;
                continue;
            }
            pa[ai] = aj;
            boolean bad = false;
            for(var rest: restrictions){
                int ri = rest[0];
                int rj = rest[1];
                if(find(ri)==find(rj)){
                    bad = true;
                    break;
                }
            }
            if(bad){
                pa=opa;
            }
            res[rp++] = !bad;
        }
        return res;
    }
}
