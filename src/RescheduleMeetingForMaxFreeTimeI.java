import java.util.ArrayList;
import java.util.List;

public class RescheduleMeetingForMaxFreeTimeI {
    // 7k ac! w gotta do better here
    public int maxFreeTime(int time, int k, int[] st, int[] et) {
        int n = st.length;
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i <= n; ++i) {
            final int cstart = i == n ? time : st[i];
            final int pend = i == 0 ? 0 : et[i - 1];
            int diff = cstart - pend;
            a.add(diff);
        }
        int len = Math.min(k + 1, a.size());
        //  System.out.println(a);
        int sum = 0;
        for (int i = 0; i < len - 1; ++i) {
            sum += a.get(i);
        }
        int maxsum = 0;
        for (int i = len - 1; i < a.size(); ++i) {
            sum += a.get(i);
            maxsum = Math.max(maxsum, sum);
            int head = i - (k + 1) + 1;
            sum -= a.get(head);

        }
        return maxsum;
    }
}
