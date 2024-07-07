import java.util.ArrayList;
import java.util.List;

public class GenerateBinaryStringWithoutAdjacentZeros {
    private List<String> res = new ArrayList<>();

    public List<String> validStrings(int n) {
        StringBuilder sb = new StringBuilder();
        dfs(0, n, sb);
        return res;
    }

    private void dfs(int i, int n, StringBuilder sb) {
        if (i == n) {
            res.add(sb.toString());
            return;
        }
        if (i > 0 && sb.charAt(i - 1) == '0') {
            sb.append('1');
            dfs(i + 1, n, sb);
            sb.deleteCharAt(sb.length() - 1);
        } else {
            sb.append('0');
            dfs(i + 1, n, sb);
            sb.deleteCharAt(sb.length() - 1);
            sb.append('1');
            dfs(i + 1, n, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
