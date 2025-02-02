import base.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class RescheduleMeetingForMaxFreeTimeII {
    public int maxFreeTime(int time, int[] st, int[] et) {
        int res = 0;
        int n = st.length;
        int[] maxleft = new int[n];
        int[] maxright = new int[n];
        for(int i=0; i<n; ++i){
            final int cstart = st[i];
            final int pend = i == 0 ? 0 : et[i - 1];
            int diff = cstart - pend;
            maxleft[i] = Math.max((i==0?0: maxleft[i-1]), diff);
        }
        for(int i=n-1; i>=0; --i){
            final int cstart = i==n-1?time:st[i+1];
            final int pend =  et[i ];
            int diff = cstart - pend;
            maxright[i] = Math.max((i==n-1?0: maxright[i+1]), diff);
        }
        for(int i=0; i<n; ++i){
            int preend = (i==0?0:et[i-1]);
            int nextstart = (i==n-1? time: st[i+1]);
            int gap = nextstart - preend;
            int cgap = et[i] - st[i];
            if(i>=1 && maxleft[i-1]>=cgap){
                res = Math.max(res, gap);
            }
            if(i+1<n && maxright[i+1]>=cgap){
                res = Math.max(res, gap);
            }
            int maxcons = gap - cgap;
            res = Math.max(res, maxcons);
        }
        return res;
    }



    public static void main(String[] args) {
      //  System.out.println(new RescheduleMeetingForMaxFreeTimeII().maxFreeTime(5, ArrayUtils.read1d("1,3"), ArrayUtils.read1d("2,5")));
        System.out.println(new RescheduleMeetingForMaxFreeTimeII().maxFreeTime(10, ArrayUtils.read1d("[9,24,45,50,53]"), ArrayUtils.read1d("[15,26,50,53,54]")));
    }

}
