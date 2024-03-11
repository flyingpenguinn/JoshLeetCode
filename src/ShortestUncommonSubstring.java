import java.util.*;

public class ShortestUncommonSubstring {
    private void update(Map<String, Integer> m, String k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    private int Max = (int) 1e9;

    public String[] shortestSubstrings(String[] a) {
        Map<String, Integer> m = new HashMap<>();
        int n = a.length;
        String[] res = new String[n];
        List<String>[] subs = new ArrayList[n];

        Arrays.fill(res, "");
        for (int i = 0; i < n; ++i) {
            String s = a[i];
            int sn = s.length();
            List<String> allsub = new ArrayList<>();
            for (int j = 0; j < sn; ++j) {
                StringBuilder sb = new StringBuilder();
                for (int k = j; k < sn; ++k) {
                    sb.append(s.charAt(k));
                    String scur = sb.toString();
                    allsub.add(scur);
                }
            }
            for (String sub : allsub) {
                update(m, sub, 1);
            }
            subs[i] = allsub;
        }

        //  System.out.println(m);
        for (int i = 0; i < n; ++i) {
            List<String> allsub = subs[i];
            for (String sub : allsub) {
                update(m, sub, -1);
            }
            //  System.out.println(m);
            int minlen = Max;
            String minString = "";
            for (String scur : allsub) {
                if (!m.containsKey(scur)) {
                    if (scur.length() < minlen) {
                        minlen = scur.length();
                        minString = scur;
                    } else if (scur.length() == minlen && scur.compareTo(minString) < 0) {
                        minString = scur;
                    }
                }
            }
            res[i] = minString;
            for (String sub : allsub) {
                update(m, sub, 1);
            }
        }
        return res;
    }
}
