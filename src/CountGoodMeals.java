import java.util.HashMap;
import java.util.Map;

public class CountGoodMeals {
    private int Mod = 1000000007;

    public int countPairs(int[] a) {
        int n = a.length;
        Map<Integer, Integer> m = new HashMap<>();

        int res = 0;
        for (int i = 0; i < n; i++) {
            int cur = a[i];
            for (int sum = 1; sum <= (1 << 21); sum *= 2) {
                int diff = sum - cur;
                Integer num = m.get(diff);
                if (num != null) {
                    //     System.out.println(sum +" "+cur+" "+num);
                    res += num;
                    res %= Mod;
                }
            }
            m.put(cur, m.getOrDefault(cur, 0) + 1);
        }
        return res;
    }
}
