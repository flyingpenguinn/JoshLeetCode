import java.util.HashMap;
import java.util.Map;

public class StringWithoutAaaBbb {
    // as long as a is more we put a. Or we put a when we have to: when we have a bb
    public String strWithout3a3b(int a, int b) {
        return doStr("", "", a, b);
    }

    private String doStr(String cur, String l2, int a, int b) {
        //  System.out.println(l2);
        //  System.out.println(cur);
        if (a == 0 && b == 0) {
            return cur;
        }

        if ((!l2.equals("aa") && a >= b) || (l2.equals("bb") && a > 0)) {
            String nl2 = (l2.isEmpty() ? "" : l2.charAt(l2.length() - 1)) + "a";
            // System.out.println(l2);
            return doStr(cur + "a", nl2, a - 1, b);

        } else if ((!l2.equals("bb") && b >= a) || (l2.equals("aa") && b > 0)) {
            String nl2 = (l2.isEmpty() ? "" : l2.charAt(l2.length() - 1)) + "b";
            //  System.out.println(l2);
            return doStr(cur + "b", nl2, a, b - 1);

        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(new StringWithoutAaaBbbDp().strWithout3a3b(2, 4));
    }
}

class StringWithoutAaaBbbDp {
    Map<String, Integer> map = new HashMap<>();

    {
        map.put("aa", 0);
        map.put("ab", 1);
        map.put("ba", 2);
        map.put("bb", 3);
        map.put("a", 4);
        map.put("b", 5);
        map.put("", 6);

    }

    int[][][] dp;

    public String strWithout3a3b(int a, int b) {
        dp = new int[7][a + 1][b + 1];
        int r = doStr("", "", a, b);
        if (r == -1) {
            return "";
        }
        return solve("", a, b);
    }

    private String solve(String l2, int a, int b) {
        if (a == 0 && b == 0) {
            return "";
        } else if (dp[map.get(l2)][a][b] == 1) {
            String nl2 = (l2.isEmpty() ? "" : l2.charAt(l2.length() - 1)) + "a";
            return "a" + solve(nl2, a - 1, b);
        } else {
            String nl2 = (l2.isEmpty() ? "" : l2.charAt(l2.length() - 1)) + "b";
            return "b" + solve(nl2, a, b - 1);
        }
    }

    private int doStr(String cur, String l2, int a, int b) {
        //  System.out.println(l2);
        //  System.out.println(cur);
        if (a == 0 && b == 0) {
            return 0;
        }
        Integer index = map.get(l2);
        if (dp[index][a][b] != 0) {
            return dp[index][a][b];
        }
        if (!l2.equals("aa") && a > 0) {
            String nl2 = (l2.isEmpty() ? "" : l2.charAt(l2.length() - 1)) + "a";
            // System.out.println(l2);
            int pa = doStr(cur + "a", nl2, a - 1, b);
            if (pa != -1) {
                dp[index][a][b] = 1;
                return 1;
            }
        }
        if (!l2.equals("bb") && b > 0) {
            String nl2 = (l2.isEmpty() ? "" : l2.charAt(l2.length() - 1)) + "b";
            //  System.out.println(l2);
            int pb = doStr(cur + "b", nl2, a, b - 1);
            if (pb != -1) {
                dp[index][a][b] = 2;
                return pb;
            }
        }
        dp[index][a][b] = -1;
        return -1;

    }
}