import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CamelCaseMatching {
    public List<Boolean> camelMatch(String[] queries, String p) {

        List<Boolean> r = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            String q = queries[i];
            boolean rb = canMatch(q, p, 0, 0);
            r.add(rb);
        }
        return r;
    }

    private boolean canMatch(String q, String p, int i, int j) {
        if (i == q.length()) {
            // can't have extra chars in p
            return j == p.length();
        }
        if (j == p.length()) {
            // extra in q should all be lower case
            while (i < q.length()) {
                if (Character.isUpperCase(q.charAt(i++))) {
                    return false;
                }
            }
            return true;
        }
        if (q.charAt(i) == p.charAt(j)) {
            return canMatch(q, p, i + 1, j + 1);
        } else if (Character.isUpperCase(q.charAt(i))) {
            // if q is upper case then there is no way for it to match p as we can't insert upper case
            return false;
        } else {
            // both lower, or q lower p higher, move q to hope that next in q should match p
            return canMatch(q, p, i + 1, j);
        }
    }

    public static void main(String[] args) {
        String[] array = {"FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack"};
        System.out.println(new CamelCaseMatching().camelMatch(array, "FoBaT"));
    }
}
