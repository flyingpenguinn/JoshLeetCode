import java.util.HashSet;
import java.util.Set;

public class PasswordStrength {
    public int passwordStrength(String s) {
        int n = s.length();
        Set<Character> cset = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            cset.add(c);
        }
        int res = 0;
        String sp = "!@#$";
        for (char c : cset) {
            if (Character.isLowerCase(c)) {
                res += 1;
            } else if (Character.isUpperCase(c)) {
                res += 2;
            } else if (Character.isDigit(c)) {
                res += 3;
            } else if (sp.indexOf(c) != -1) {
                res += 5;
            }
        }
        return res;
    }
}
