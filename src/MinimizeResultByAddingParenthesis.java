public class MinimizeResultByAddingParenthesis {
    public String minimizeResult(String expression) {
        int n = expression.length();
        int mid = -1;
        for (int i = 0; i < n; ++i) {
            if (expression.charAt(i) == '+') {
                mid = i;
                break;
            }
        }
        int res = Integer.MAX_VALUE;
        String best = "";
        for (int i = 0; i <= mid - 1; ++i) {
            for (int j = mid + 2; j <= n; ++j) {
                String v1 = expression.substring(0, i);
                String v2 = expression.substring(j, n);
                String v3 = expression.substring(i, mid);
                String v4 = expression.substring(mid + 1, j);
                String curstr = v1 + "(" + expression.substring(i, j) + ")" + v2;

                int cur = eval(v1, v3, v4, v2);
                //   System.out.println(curstr+" eval to "+cur);
                if (cur < res) {
                    res = cur;
                    best = curstr;
                }
            }
        }
        return best;
    }

    private int eval(String v1, String v2, String v3, String v4) {
        int i1 = toint(v1);
        int i2 = toint(v2);
        int i3 = toint(v3);
        int i4 = toint(v4);
        return i1 * (i2 + i3) * i4;
    }

    private int toint(String s) {
        return s.isEmpty() ? 1 : Integer.valueOf(s);
    }
}
