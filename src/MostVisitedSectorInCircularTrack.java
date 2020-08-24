import java.util.*;

public class MostVisitedSectorInCircularTrack {
    // just need to check start and end
    // like a clock. if from <=to, just this section
    // otherwise it's like 2,4 when n=5. it would be 1...2 plus 4...5
    public List<Integer> mostVisited(int n, int[] rounds) {
        int from = rounds[0];
        int to = rounds[rounds.length - 1];
        List<Integer> res = new ArrayList<>();
        if (from <= to) {
            for (int i = from; i <= to; i++) {
                res.add(i);
            }
        } else {
            for (int i = 1; i <= to; i++) {
                res.add(i);
            }
            for (int i = from; i <= n; i++) {
                res.add(i);
            }
        }
        return res;
    }
}
