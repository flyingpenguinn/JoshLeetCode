import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BalancedKfactorDecomposition {
    private static int LIM = (int) (1e6 + 5);
    private static List<Integer>[] facts;
    private List<Integer> res;
    private int resdiff = LIM + 100;

    protected void initIfNotDone() {
        if (facts == null) {
            facts = new ArrayList[LIM];
            for (int i = 1; i < LIM; ++i) {
                for (int j = i; j < LIM; j += i) {
                    if(facts[j] == null){
                        facts[j] = new ArrayList<>();
                    }
                    facts[j].add(i);
                }
            }
        }
    }

    public int[] minDifference(int n, int k) {
        // not needed here but in case
        // initIfNotDone();
        List<Integer> cur = new ArrayList<>();
        dfs(n, 0, k, cur);
        int[] rres = new int[res.size()];
        for (int i = 0; i < res.size(); ++i) {
            rres[i] = res.get(i);
        }
        return rres;
    }

    private void dfs(int n, int pre, int k, List<Integer> cur) {
        if (k == 1) {
            if (n >= pre) {
                cur.add(n);
                int min = cur.get(0);
                int max = cur.get(cur.size() - 1);
                if (res == null || max - min < resdiff) {
                    resdiff = max - min;
                    res = new ArrayList<>(cur);
                }
                cur.remove(cur.size()-1);
            }
            return;
        }
        List<Integer> cf = new ArrayList<>();
        for(int p=1; p*p<=n; ++p){
            if(n%p==0){
                cf.add(p);
                int other = n/p;
                if(other != p){
                    cf.add(other);
                }
            }
        }

        for (int i = 0; i < cf.size(); ++i) {
            if (cf.get(i) < pre) {
                continue;
            }
            int cand = cf.get(i);
            cur.add(cand);
            dfs(n / cand, cand, k - 1, cur);
            cur.remove(cur.size()-1);
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new BalancedKfactorDecomposition().minDifference(100, 2)));
    }

}
