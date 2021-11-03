import java.util.Arrays;

public class TwoBestNonOverlappingEvents {
    public int maxTwoEvents(int[][] events) {
        int n = events.length;
        Arrays.sort(events, (x, y)->Integer.compare(x[0], y[0]));
        int[] right = new int[n+1];
        for(int i=n-1; i>=0; --i){
            right[i] = Math.max(right[i+1], events[i][2]);
        }
        int res = 0;
        for(int i=0; i<n; ++i){
            int index = binary(events, events[i][1]+1);
            int cur = events[i][2];
            cur += right[index];
            res = Math.max(res, cur);
        }
        return res;
    }

    private int binary(int[][] events, int st){
        int n = events.length;
        int l = 0;
        int u = n-1;
        while(l<=u){
            int mid = l+(u-l)/2;
            if(events[mid][0]>=st){
                u = mid-1;
            }else{
                l = mid+1;
            }
        }
        return l;
    }
}
