import java.util.*;

public class NumbersWithConsecutiveDiff {

    Map<Integer, Map<Integer, List<String>>> dp = new HashMap<>();

    // trap is 0 and last+k == last-k
    public int[] numsSameConsecDiff(int n, int k) {
        List<String> r = gen(-1, n, k);
        if (n == 1) {
            r.add("0");
        }
        int[] result = new int[r.size()];
        for (int i = 0; i < r.size(); i++) {
            String v = r.get(i);
            result[i] = Integer.valueOf(v);
        }
        return result;
    }

    private List<String> gen(int last, int n, int k) {
        if (n == 0) {
            List<String> r = new ArrayList<>();
            r.add("");
            return r;
        }
        Map<Integer, List<String>> cached = dp.getOrDefault(last, new HashMap<>());
        if (cached.get(n) != null) {
            return cached.get(n);
        }
        List<String> r = new ArrayList<>();
        if (last == -1) {
            for (int i = 1; i <= 9; i++) {
                List<String> later = gen(i, n - 1, k);
                populateLater(n, k, r, i);
            }
        } else {
            if (last + k <= 9) {
                int num = last + k;
                populateLater(n, k, r, num);
            }
            if (last - k >= 0 && k != 0) {
                int num = last - k;
                populateLater(n, k, r, num);
            }
        }
        List<String> rt = r.isEmpty() ? null : r;
        cached.put(n, rt);
        dp.put(last, cached);
        return rt;
    }

    private void populateLater(int n, int k, List<String> r, int num) {
        List<String> later = gen(num, n - 1, k);
        if (later == null) {
            return;
        }
        for (String l : later) {
            String concat = String.valueOf(num) + l;
            r.add(concat);
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new NumbersWithConsecutiveDiff().numsSameConsecDiff(3, 7)));
    }
}
