import java.util.List;

public class MaxNumOfAlloys {
    public int maxNumberOfAlloys(int n, int k, int budget, List<List<Integer>> a, List<Integer> s, List<Integer> c) {
        int res = 0;
        for (int i = 0; i < k; ++i) {
            List<Integer> comp = a.get(i);
            int cando = solve(comp, s, c, budget);
            res = Math.max(cando, res);
        }
        return res;
    }

    private int solve(List<Integer> comp, List<Integer> s, List<Integer> c, int budget) {
        int n = comp.size();
        long l = 0;
        long u = (long) 1e9;
        while (l <= u) {
            long mid = l + (u - l) / 2;
            long exp = 0;
            for (int i = 0; i < n; ++i) {
                long delta = comp.get(i) * mid - s.get(i);
                //   System.out.println("mid="+mid+" delta="+delta);
                if (delta > 0) {
                    exp += c.get(i) * delta;
                }
                if (exp > budget) {
                    break;
                }
            }
            if (exp > budget) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return (int) u;
    }
}
