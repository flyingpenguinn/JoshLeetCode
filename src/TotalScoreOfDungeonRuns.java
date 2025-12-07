import java.util.PriorityQueue;

public class TotalScoreOfDungeonRuns {
    public long totalScore(int hp, int[] damage, int[] requirement) {
        int n = damage.length;
        PriorityQueue<Long> pq = new PriorityQueue<>();
        long accu = 0;
        long cscore = 0;
        long res = 0;
        for (int i = n - 1; i >= 0; --i) {
            long chp = hp - damage[i];
            long cr = requirement[i];
            accu += damage[i];
            while (!pq.isEmpty() && pq.peek() < accu) {
                pq.poll();
                --cscore;
            }
            if (chp >= cr) {
                pq.offer(chp - cr + accu);
                ++cscore;
            }
            res += cscore;
        }

        return res;
    }
}
