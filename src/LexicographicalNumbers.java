import java.util.ArrayList;
import java.util.List;

public class LexicographicalNumbers {

    // use logn next number generaton
    List<Integer> r = new ArrayList<>();

    public List<Integer> lexicalOrder(int n) {
        int prev = 1;
        while (prev != -1) {
            r.add(prev);
            int nxt = next(prev, n);
            prev = nxt;
        }
        return r;

    }

    int next(int p, int n) {
        // System.out.println(p);
        if (p * 10 <= n) {
            return p * 10;
        } else {
            int cand = p;
            // 199=> 20
            // 109 => 11
            // 200=> 21
            while (cand > 0 && (cand % 10 == 9 || cand + 1 > n)) {
                cand /= 10;
            }
            // last one must be all 9
            if (cand == 0) {
                return -1;
            } else {
                return cand + 1;
            }
        }
    }
}

class LexicoGraphicalNumberDfs {
    List<Integer> r = new ArrayList<>();

    public List<Integer> lexicalOrder(int n) {
        dfs(0, n, true);
        return r;
    }

    private void dfs(int s, int n, boolean first) {
        if (s > 0) {
            r.add(s);
        }
        // first digit can't be 0
        int start = first ? 1 : 0;
        for (int i = start; i < 10; i++) {
            if(10 * s + i<=n) {
                dfs(10 * s + i, n, false);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new LexicoGraphicalNumberDfs().lexicalOrder(13));
    }
}
