import java.util.Arrays;

public class WidestVerticalArea {
    public int maxWidthOfVerticalArea(int[][] ps) {
        if(ps.length==0){
            return 0;
        }
        Arrays.sort(ps, (x, y) -> Integer.compare(x[0], y[0]));
        int max = 0;
        int last = ps[0][0];
        for(int i=1; i<ps.length;i++){
            if(ps[i][0]==ps[i-1][0]){
                continue;
            }
            int cur = ps[i][0]-last;
            max = Math.max(max, cur);
            last= ps[i][0];
        }
        return max;
    }
}
