import java.util.*;

public class FindMostCommonResponse {
    public String findCommonResponse(List<List<String>> reps) {
        Map<String, Integer> m = new HashMap<>();
        int maxocc = -1;
        String maxstr = null;
        for (List<String> rep : reps) {
            Set<String> rset = new HashSet<>(rep);
            for (String r : rset) {
                int nocc = m.getOrDefault(r, 0) + 1;
                m.put(r, nocc);
                if (nocc > maxocc) {
                    maxocc = nocc;
                    maxstr = r;
                } else if (nocc == maxocc && r.compareTo(maxstr) < 0) {
                    maxstr = r;
                }
            }
        }
        return maxstr;
    }
}
