import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class AuthenticationManager {
    // current time only increases, so use a tree map
    private TreeMap<Integer, Integer> tm = new TreeMap<>();
    private Map<String, Integer> m = new HashMap<>();
    private int ttl = 0;
    private int counts = 0;

    public AuthenticationManager(int timeToLive) {
        ttl = timeToLive;
    }

    public void generate(String tokenId, int currentTime) {
        int exp = currentTime + ttl;
        tm.put(exp, tm.getOrDefault(exp, 0) + 1);
        m.put(tokenId, exp);
        counts++;
    }

    public void renew(String tokenId, int currentTime) {
        Integer oldet = m.get(tokenId);
        if (oldet == null || oldet <= currentTime) {
            return;
        }
        tm.put(oldet, tm.get(oldet) - 1);
        int newet = currentTime + ttl;
        tm.put(newet, tm.getOrDefault(newet, 0) + 1);
        m.put(tokenId, newet);
    }

    public int countUnexpiredTokens(int currentTime) {
        while (!tm.isEmpty() && tm.firstKey() <= currentTime) {
            Integer first = tm.firstKey();
            counts -= tm.get(first);
            tm.remove(first);
        }
        return counts;
    }
}
