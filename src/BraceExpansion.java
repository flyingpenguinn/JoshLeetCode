import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BraceExpansion {
    public String[] expand(String s) {
        List<String> list = doExpand(s, 0);
        String[] r = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            r[i] = list.get(i);
        }
        return r;
    }

    private List<String> doExpand(String s, int i) {
        List<String> r = new ArrayList<>();
        if (i == s.length()) {
            r.add("");
            return r;
        }
        if (s.charAt(i) == '{') {
            int closing = s.indexOf('}', i + 1);
            String inside = s.substring(i + 1, closing);
            String[] insidearray = inside.split(",");
            Arrays.sort(insidearray);
            for (int j = 0; j < insidearray.length; j++) {
                List<String> later = doExpand(s, closing+1);
                for (String sl : later) {
                    r.add(insidearray[j] + sl);
                }
            }
        } else {
            List<String> later = doExpand(s, i + 1);
            for (String sl : later) {
                r.add(s.charAt(i) + sl);
            }
        }
        return r;
    }
}
