import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CountBeautifulSubstringsIandII {
    public int beautifulSubstrings(String s, int k) {
        int n = s.length(), v = 0, l;
        for (l = 1; l * l % (k * 4) > 0; ++l) ;
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        HashMap<Integer, Integer>[] seen = new HashMap[l];
        for (int i = 0; i < l; i++) {
            seen[i] = new HashMap<>();
        }
        seen[l - 1].put(0, 1);
        long res = 0;
        for (int i = 0; i < n; i++) {
            v += vowels.contains(s.charAt(i)) ? 1 : -1;
            int c = seen[i % l].getOrDefault(v, 0);
            res += c;
            seen[i % l].put(v, c + 1);
        }
        return (int) res;

    }
}
