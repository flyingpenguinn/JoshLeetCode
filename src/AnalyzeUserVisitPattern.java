import base.ArrayUtils;

import java.util.*;

public class AnalyzeUserVisitPattern {
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        int n = username.length;
        // use treemap to manage sorting of timestamp
        Map<String, TreeMap<Integer, String>> userwebsites = new HashMap<>();
        // avoid one user visiting one website lots of times triggering bunches of fake +1
        Map<List<String>, Set<String>> visitors = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String user = username[i];
            TreeMap<Integer, String> webmap = userwebsites.get(user);
            if (webmap == null) {
                webmap = new TreeMap<>();
                userwebsites.put(user, webmap);
            }
            webmap.put(timestamp[i], website[i]);
        }
        for (String user : userwebsites.keySet()) {
            TreeMap<Integer, String> webmap = userwebsites.get(user);
            List<String> weblist = new ArrayList<>();
            for (Integer t : webmap.keySet()) {
                weblist.add(webmap.get(t));
            }
            int webn = weblist.size();
            // better doing this after work is done you never know if there is a smaller number afterwards
            for (int j = 0; j < webn; j++) {
                for (int k = j + 1; k < webn; k++) {
                    for (int l = k + 1; l < webn; l++) {
                        List<String> triple = new ArrayList<>();
                        triple.add(weblist.get(j));
                        triple.add(weblist.get(k));
                        triple.add(weblist.get(l));
                        Set<String> cur = visitors.get(triple);
                        if (cur == null) {
                            cur = new HashSet<>();
                            visitors.put(triple, cur);
                        }
                        cur.add(user);
                    }
                }
            }
        }
        int max = -1;
        List<String> maxtri = null;
        for (List<String> tri : visitors.keySet()) {
            int count = visitors.get(tri).size();
            if (count > max) {
                max = count;
                maxtri = tri;
            } else if (count == max && smaller(tri, maxtri)) {
                max = count;
                maxtri = tri;
            }
        }
        return maxtri;
    }

    private boolean smaller(List<String> l1, List<String> l2) {
        int n = l1.size();
        for (int i = 0; i < n; i++) {
            int cmp = l1.get(i).compareTo(l2.get(i));
            if (cmp != 0) {
                return cmp < 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String[] users = {"h", "eiy", "cq", "h", "cq", "txldsscx", "cq", "txldsscx", "h", "cq", "cq"};
        int[] times = {527896567, 334462937, 517687281, 134127993, 859112386, 159548699, 51100299, 444082139, 926837079, 317455832, 411747930};
        String[] webs = {"hibympufi", "hibympufi", "hibympufi", "hibympufi", "hibympufi", "hibympufi", "hibympufi", "hibympufi", "yljmntrclw", "hibympufi", "yljmntrclw"};
        System.out.println(new AnalyzeUserVisitPattern().mostVisitedPattern(users, times, webs));
    }
}
