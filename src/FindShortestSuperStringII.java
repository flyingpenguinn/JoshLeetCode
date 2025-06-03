import java.util.HashMap;
import java.util.Map;

public class FindShortestSuperStringII {
    public String shortestSuperstring(String s1, String s2) {
        if (s1.contains(s2)) {
            return s1;
        }
        if (s2.contains(s1)) {
            return s2;
        }
        Map<String, String> suf1 = new HashMap<>();
        Map<String, String> suf2 = new HashMap<>();
        int n1 = s1.length();
        int n2 = s2.length();
        for (int i = 0; i <= n1; ++i) {
            suf1.put(s1.substring(i), s1.substring(0, i));
        }
        for (int i = 0; i <= n2; ++i) {
            suf2.put(s2.substring(i), s2.substring(0, i));
        }
        // System.out.println("suf1 "+suf1);
        //  System.out.println("suf2 "+suf2);
        String res = s1 + s2;
        for (int i = 0; i <= n1; ++i) {
            String pref = s1.substring(0, i);
            if (suf2.containsKey(pref)) {
                //    System.out.println("In s2 found s1 pref "+pref);
                String cur = suf2.get(pref) + s1;
                if (cur.length() < res.length()) {
                    res = cur;
                }
            }
        }
        for (int i = 0; i <= n2; ++i) {
            String pref = s2.substring(0, i);
            if (suf1.containsKey(pref)) {
                //   System.out.println("In s1 found s2 pref "+pref);
                String cur = suf1.get(pref) + s2;
                if (cur.length() < res.length()) {
                    res = cur;
                }
            }
        }
        return res;
    }
}
