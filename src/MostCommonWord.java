import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MostCommonWord {

    public String mostCommonWord(String p, String[] banned) {
        if (p == null) {
            return null;
        }
        Set<String> bs = new HashSet<>();
        if (banned != null) {
            for (String s : banned) {
                bs.add(s);
            }
        }
        Map<String, Integer> freq = new HashMap<>();
        String res = null;
        int highest = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= p.length(); i++) {
            // don't forget to process the last word...
            char ci = i == p.length() ? '#' : p.charAt(i);
            if (Character.isLetter(ci)) {
                sb.append(ci);
            } else {
                if (sb.length() != 0) {
                    String w = sb.toString().toLowerCase();
                    if (!bs.contains(w)) {
                        Integer cf = freq.getOrDefault(w, 0) + 1;
                        freq.put(w, cf);
                        if (cf > highest) {
                            highest = cf;
                            res = w;
                        }
                    }
                    sb = new StringBuilder();
                } else {
                    continue;
                }
            }
        }
        return res;
    }
}
