import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
    public List<String> generateParenthesis(int n) {
        return dog(0, 0, n);
    }

    private List<String> dog(int lc, int rc, int n) {
        List<String> res = new ArrayList<>();
        // can't have more rights than left
        if (rc > lc) {
            return res;
        }
        // must be euqal in the end
        if (lc == n && rc == n) {
            res.add("");
            return res;
        }

        List<String> left = lc + 1 <= n ? dog(lc + 1, rc, n) : new ArrayList<>();
        for (String l : left) {
            res.add(("(" + l));
        }
        List<String> right = rc + 1 <= n ? dog(lc, rc + 1, n) : new ArrayList<>();
        for (String r : right) {
            res.add((")" + r));
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new GenerateParenthesis().generateParenthesis(4));
    }

}

// two ways of doing recursion. the previous one is easier to apply memoization
class GenerateParenthesisGlobalVar {
    List<String> r = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        dog(0, 0, n, "");
        return r;
    }

    private void dog(int lc, int rc, int n, String s) {
        if (lc == n && rc == n) {
            r.add(s);
            return;
        }
        if (lc + 1 <= n) {
            dog(lc + 1, rc, n, s + "(");
        }
        if (rc + 1 <= n && rc + 1 <= lc) {
            dog(lc, rc + 1, n, s + ")");
        }
    }
}
