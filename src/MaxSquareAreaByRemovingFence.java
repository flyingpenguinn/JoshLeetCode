import java.util.*;

public class MaxSquareAreaByRemovingFence {
    private long Mod = (long) (1e9 + 7);

    public int maximizeSquareArea(int m, int n, int[] h, int[] v) {
        List<Long> hl = init(m, h);
        List<Long> vl = init(n, v);
        Collections.sort(hl);
        Collections.sort(vl);
        Set<Long> hset = build(hl);
        Set<Long> vset = build(vl);
        long res = -1;
        for (long hk : hset) {
            if (vset.contains(hk)) {
                long cur = hk * hk;
                res = Math.max(res, cur);
            }
        }
        return (int) (res % Mod);
    }

    protected List<Long> init(int m, int[] h) {
        List<Long> hl = new ArrayList<>();
        hl.add(1L);
        hl.add(Long.valueOf(m));
        for (int hi : h) {
            hl.add((long) hi);
        }
        return hl;
    }

    protected Set<Long> build(List<Long> hl) {
        Set<Long> hset = new HashSet<>();
        for (int i = 0; i < hl.size(); ++i) {
            for (int j = i - 1; j >= 0; --j) {
                long diff = hl.get(i) - hl.get(j);
                hset.add(diff);
            }
        }
        return hset;
    }
}
