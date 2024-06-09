import java.util.ArrayDeque;
import java.util.Deque;

public class FindFirstPlayerWinKInARow {
    public int findWinningPlayer(int[] skills, int k) {
        int n = skills.length;
        int max = -1;
        int maxi = -1;
        for (int i = 0; i < n; ++i) {
            if (skills[i] > max) {
                max = skills[i];
                maxi = i;
            }
        }
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            q.offerLast(i);
        }
        int topindex = q.pollFirst();
        int topwins = 0;
        while (topindex != maxi) {
            int next = q.pollFirst();
            if (skills[topindex] > skills[next]) {
                q.offerLast(next);
                ++topwins;
            } else {
                q.offerLast(topindex);
                topindex = next;
                topwins = 1;
            }
            if (topwins == k) {
                break;
            }
        }
        return topindex;
    }
}
