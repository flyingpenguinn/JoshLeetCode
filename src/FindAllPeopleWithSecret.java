import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllPeopleWithSecret {
    private int[] pa;
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        pa = new int[n];
        for(int i=0; i<n; ++i){
            pa[i] = -1;
        }
        Arrays.sort(meetings, (x,y)->Integer.compare(x[2], y[2]));
        int i = 0;
        union(0, firstPerson);
        while(i<meetings.length){
            int j = i;
            while(j<meetings.length && meetings[j][2] == meetings[i][2]){
                ++j;
            }
            List<Integer> unioned = new ArrayList<>();
            while(i<j){
                int a = meetings[i][0];
                int b = meetings[i][1];
                union(a, b);
                unioned.add(a);
                unioned.add(b);
                ++i;
            }
            for(int ui: unioned){
                // key: if they actually dont connect to 0, they dont know what's happening yet
                if(!same(0, ui)){
                    pa[ui] = -1;
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        for(int j=0; j<n; ++j){
            if(same(0, j)){
                res.add(j);
            }
        }
        return res;
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
        pa[ri] = rj;
    }

    private boolean same(int i, int j){
        return find(i)==find(j);
    }

}
