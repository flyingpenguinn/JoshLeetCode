import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllNumbersDisappearedInAnArrayIII {
    public int[][] findDisappearedNumbers(int[] a, int lower, int upper) {
        int n = a.length;
        List<int[]> res = new ArrayList<>();
        Arrays.sort(a);
        if(lower<a[0]){
            norm(lower, a[0]-1, lower, upper, res);
        }
        for(int i=0; i+1<n; ++i){
            if(a[i]>=upper){
                break;
            }
            norm(a[i]+1, a[i+1]-1, lower, upper, res);
        }
        if(a[n-1]<upper){
            norm(a[n-1]+1, upper, lower, upper, res);
        }
        int[][] rr = new int[res.size()][2];
        for(int i=0; i<res.size(); ++i){
            rr[i][0] = res.get(i)[0];
            rr[i][1] = res.get(i)[1];
        }
        return rr;
    }

    private void norm(int start, int end, int low, int upper, List<int[]> res){
        int rs = Math.max(start, low);
        int re = Math.min(end, upper);
        if(rs>re){
            return;
        }
        res.add(new int[]{rs, re});
    }
}
