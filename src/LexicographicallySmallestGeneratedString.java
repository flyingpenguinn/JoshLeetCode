import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LexicographicallySmallestGeneratedString {
    public String generateString(String s1, String s2) {
        int s1n = s1.length();
        int s2n = s2.length();
        char[] res = new char[s1n + s2n - 1];
        Arrays.fill(res, '*');
        Set<Integer> fixed = new HashSet<>();
        for (int i = 0; i < s1n; ++i) {
            if (s1.charAt(i) == 'T') {
                for (int j = 0; j < s2n; ++j) {
                    char exp = s2.charAt(j);
                    if (res[i + j] != '*' && res[i + j] != exp) {
                        return "";
                    }
                    res[i + j] = exp;
                    fixed.add(i + j);
                }
            }
        }
        for (int i = 0; i < res.length; ++i) {
            if (res[i] == '*') {
                res[i] = 'a';
            }
        }
        for (int i = 0; i < s1n; ++i) {
            if (s1.charAt(i) == 'T') {
                continue;
            }
            boolean alleq = true;
            for (int j = 0; j < s2n; ++j) {
                if (res[i + j] != s2.charAt(j)) {
                    alleq = false;
                    break;
                }
            }
            if (alleq) {
                boolean changed = false;
                // must count reversely! in contest didnt make this...
                for (int j = s2n-1; j >=0; --j) {
                    if (!fixed.contains(i + j) ) {
                        changed = true;
                        res[i+j] = 'b';
                        break;
                    }
                }
                if(!changed){
                    return "";
                }
            }
        }
        return new String(res);
    }

    public static void main(String[] args) {
        System.out.println(new LexicographicallySmallestGeneratedString().generateString("TFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFT", "baaaaaaaaaaaaaaaaaab"));
    }
}
