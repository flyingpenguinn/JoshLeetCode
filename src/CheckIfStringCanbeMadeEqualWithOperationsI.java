import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckIfStringCanbeMadeEqualWithOperationsI {
    public boolean canBeEqual(String s1, String s2) {
        List<Character> l1 = new ArrayList<>();
        List<Character> l2 = new ArrayList<>();
        int n = s1.length();
        for (int i = 0; i < n; i += 2) {
            l1.add(s1.charAt(i));
            l2.add(s2.charAt(i));
        }
        Collections.sort(l1);
        Collections.sort(l2);
        if (!l1.equals(l2)) {
            return false;
        }
        l1.clear();
        l2.clear();
        for (int i = 1; i < n; i += 2) {
            l1.add(s1.charAt(i));
            l2.add(s2.charAt(i));
        }

        Collections.sort(l1);
        Collections.sort(l2);
        if (!l1.equals(l2)) {
            return false;
        }
        return true;
    }
}
