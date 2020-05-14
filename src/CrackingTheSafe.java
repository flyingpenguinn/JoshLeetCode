import java.util.HashSet;
import java.util.Set;

public class CrackingTheSafe {
    boolean found = false;
    String solution = null;
    int Limit = 0;

    private void doCombination(String last, int n, int k, HashSet<String> generated, StringBuilder sb) {
        if (found) {
            return;
        }
        if (generated.size() == Limit) {
            found = true;
            solution = sb.toString();
            return;
        }

        String nextKey = last.substring(1);
        for (int next = 0; next < k; next++) {
            String ns = nextKey + next;
            if (!generated.contains(ns)) {
                generated.add(ns);
                sb.append(next);
                doCombination(ns, n, k, generated, sb);
                generated.remove(ns);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }


    public String crackSafe(int n, int k) {
        found = false;
        solution = null;
        Limit = (int) Math.pow(k, n);
        if (n == 1) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < k; ++i) {
                sb.append(i);
            }
            return sb.toString();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            sb.append("0");
        }
        String start = sb.toString();
        HashSet<String> generated = new HashSet<>();
        generated.add(start);
        sb = new StringBuilder();
        sb.append(start);
        doCombination(start, n, k, generated, sb);
        return solution;
    }

    public static void main(String[] args) {
        CrackTheSafeEulerPath cts = new CrackTheSafeEulerPath();

        System.out.println(cts.crackSafe(3, 2));
    }
}

class CrackTheSafeEulerPath {
    Set<String> seen;
    StringBuilder ans;

    public String crackSafe(int n, int k) {
        if (n == 1 && k == 1) return "0";
        seen = new HashSet();
        ans = new StringBuilder();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n-1; ++i)
            sb.append("0");
        String start = sb.toString();

        dfs(start, k);
        ans.append(start);
        return new String(ans);
    }

    public void dfs(String node, int k) {
        for (int x = 0; x < k; ++x) {
            String nei = node + x;
            if (!seen.contains(nei)) {
                seen.add(nei);
                dfs(nei.substring(1), k);
                ans.append(x);
            }
        }
    }

}
