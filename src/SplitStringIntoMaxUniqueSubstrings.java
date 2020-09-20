import java.util.HashSet;
import java.util.Set;

public class SplitStringIntoMaxUniqueSubstrings {
    private int max = 1;

    public int maxUniqueSplit(String s) {
        dfs(s, 0, new HashSet<>());
        return max;
    }

    private void dfs(String s, int i, Set<String> seen) {
        if (i == s.length()) {
            //   System.out.println(seen);
            max = Math.max(max, seen.size());
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int j = i; j < s.length(); j++) {
            sb.append(s.charAt(j));
            String str = sb.toString();
            if (!seen.contains(str)) {
                seen.add(str);
                dfs(s, j + 1, seen);
                seen.remove(str);
            }
        }
    }
}
