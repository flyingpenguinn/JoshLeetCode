import java.util.HashMap;
import java.util.Map;

public class CountCesarCipherPairs {
    public long countPairs(String[] ws) {
        int n = ws.length;
        Map<String, Long> seen = new HashMap<>();
        long res = 0;
        for (int i = 0; i < n; ++i) {
            String cv = ws[i];
            int sn = cv.length();
            for (int k = 0; k < 26; ++k) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < sn; ++j) {
                    int cind = cv.charAt(j) - 'a';
                    int ncind = (cind + k) % 26;
                    char nc = (char) ('a' + ncind);
                    sb.append(nc);
                }
                String csb = sb.toString();
                res += seen.getOrDefault(csb, 0L);
            }
            seen.put(cv, seen.getOrDefault(cv, 0L) + 1);
        }
        return res;
    }
}
