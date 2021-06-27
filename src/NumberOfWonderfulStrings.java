import java.util.HashMap;
import java.util.Map;

public class NumberOfWonderfulStrings {
    // a similar question: num of substrings where every word appears even times
    // here we find the number of bit masks where each digit is different from this one
    public long wonderfulSubstrings(String s) {
        Map<Integer, Integer> m = new HashMap<>();
        int n = s.length();
        int cur = 0;
        long res = 0;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int cind = c - 'a';
            cur ^= (1 << cind);
            int same = m.getOrDefault(cur, 0);
            int diff1 = 0;
            for (int j = 0; j < 10; j++) {
                int other = cur ^ (1 << j);
                diff1 += m.getOrDefault(other, 0);
            }
            int self = 0;
            if (Integer.bitCount(cur) <= 1) {
                self++;
            }
            res += self;
            res += same;
            res += diff1;
            m.put(cur, m.getOrDefault(cur, 0) + 1);
        }
        return res;
    }
}
