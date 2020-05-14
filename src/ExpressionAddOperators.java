import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
LC#282
Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

Example 1:

Input: num = "123", target = 6
Output: ["1+2+3", "1*2*3"]
Example 2:

Input: num = "232", target = 8
Output: ["2*3+2", "2+3*2"]
Example 3:

Input: num = "105", target = 5
Output: ["1*0+5","10-5"]
Example 4:

Input: num = "00", target = 0
Output: ["0+0", "0-0", "0*0"]
Example 5:

Input: num = "3456237490", target = 9191
Output: []
 */
public class ExpressionAddOperators {
    // trick 1: get a multi cache for better enumeration
    // trick2: how to treat single elements: judge at n-1
    // trick3: reverse for -: done with a flip flag
    // trick4: handle numbers starting with 0
    // do * first then +/-
    // flatten - aggressively so that we only need to revert it
    public List<String> addOperators(String s, int t) {
        int[] a = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            a[i] = s.charAt(i) - '0';
        }
        return doa(0, a, t, null, false);
    }

    private List<String> doa(int i, int[] a, long t, Long multicache, boolean flip) {
        List<String> r = new ArrayList<>();
        int n = a.length;
        if (i == n) {
            if ((multicache == null && t == 0) || (multicache != null && t == multicache)) {
                r.add("");
            }
            // otherwise bad dont return string
            return r;
        }
        long cur = 0;
        for (int j = i; j < n; j++) {
            if (a[i] == 0 && j > i) {
                break;
            }
            cur = cur * 10 + a[j];
            if (cur > Integer.MAX_VALUE) {
                break;
            }
            long nextmulti = multicache == null ? cur : cur * multicache;
            if (nextmulti > Integer.MAX_VALUE) {
                continue;
            }
            List<String> multi = doa(j + 1, a, t, nextmulti, flip);
            for (String l : multi) {
                r.add(l.isEmpty() ? String.valueOf(cur) : cur + "*" + l);
            }
            if (j + 1 >= n) {
                // if this is the last element we dont need to process empty again: 2*3 == 2*3+0 == 2*3-0
                continue;
            }
            List<String> add = doa(j + 1, a, t - nextmulti, null, flip);
            for (String l : add) {
                r.add(cur + (flip ? "-" : "+") + l);
            }
            List<String> minus = doa(j + 1, a, nextmulti - t, null, flip ? false : true);
            for (String l : minus) {
                r.add(cur + (flip ? "+" : "-") + l);
            }
        }
        return r;
    }

    public static void main(String[] args) {
        //   System.out.println(new ExpressionAddOperators().addOperators("123", 6));
        //  System.out.println(new ExpressionAddOperators().addOperators("92345", 1));
        System.out.println(new ExpressionAddOperators().addOperators("105", 5));
    }
}
