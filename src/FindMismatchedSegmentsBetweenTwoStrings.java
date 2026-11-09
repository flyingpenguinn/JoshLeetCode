import java.util.ArrayList;
import java.util.List;

public class FindMismatchedSegmentsBetweenTwoStrings {
    public List<List<String>> findMismatchedSegments(String s, String t) {
        int n = s.length();
        int i = 0;
        List<List<String>> res = new ArrayList<>();
        while(i<n){
            if(s.charAt(i) == t.charAt(i)){
                ++i;
                continue;
            }
            int j = i;
            StringBuilder sb = new StringBuilder();
            StringBuilder tb = new StringBuilder();
            while(j<n && s.charAt(j) != t.charAt(j)){
                sb.append(s.charAt(j));
                tb.append(t.charAt(j));
                ++j;
            }
            List<String> cseg = List.of(sb.toString(), tb.toString());
            res.add(cseg);
            i = j;
        }
        return res;
    }
}
