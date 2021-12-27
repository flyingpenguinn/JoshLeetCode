import java.util.HashMap;
import java.util.Map;

public class ExecutionOfAllSuffixExecutions {
    private Map<Character, int[]> m = new HashMap<>();

    {
        m.put('R', new int[]{0, 1});
        m.put('L', new int[]{0, -1});
        m.put('U', new int[]{-1, 0});
        m.put('D', new int[]{1, 0});
    }

    public int[] executeInstructions(int n, int[] startPos, String s) {
        int[] res = new int[s.length()];
        for (int i = 0; i < s.length(); ++i) {
            //    System.out.println(i);
            int r = startPos[0];
            int c = startPos[1];
            int j = i;
            for (; j < s.length(); ++j) {

                int[] nv = m.get(s.charAt(j));
                r += nv[0];
                c += nv[1];
                if (r < 0 || r >= n || c < 0 || c >= n) {
                    break;
                }
            }
            res[i] = j - i;
        }
        return res;
    }
}
