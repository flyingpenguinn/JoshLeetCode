import java.util.HashSet;
import java.util.Set;

public class UniqueThreeDigitEventNumbers {
    private Set<Integer> num = new HashSet<>();
    public int totalNumbers(int[] ds) {
        int n = ds.length;
        solve(0, 0, ds, 0);
        return num.size();
    }

    private void solve(int i, int st, int[] ds, int cur){
        if(i==3){
            if(cur%2 == 0 && cur>=100){
                num.add(cur);
            }
            return;
        }
        int n = ds.length;
        for(int j=0; j<n; ++j){

            if(((st>>j)&1)==0){
                int nst = st|(1<<j);
                solve(i+1, nst, ds, cur *10 + ds[j]);
            }
        }
    }
}
