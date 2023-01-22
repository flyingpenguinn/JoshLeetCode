import java.util.HashSet;
import java.util.Set;

public class ApplyBitwiseOperationsToMakeStringsEqual {
    // if there is a matching 1 we can always borrow that 1 to fix s0 vs t1 or s1 vs t0
    // if there is no matching 1 but there is an s0 vs t1, and there is another non matching 1 in s, we can still borrow that 1 to fix this s0 vs t1 to become a matching 1, then it's all good.
    // so actually as long as s has 1,  t also has 1, or they both do not have 1, we are good
    // return s.contains("1") == t.contains("1");
    public boolean makeStringsEqual(String s, String t) {

        int n = s.length();
        Set<Integer> sset = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            int sind = s.charAt(i) - '0';
            sset.add(sind);
            int tind = t.charAt(i) - '0';
            if (sind == 1 && tind == 1) {
                return true;
            }
        }
        for (int i = 0; i < n; ++i) {
            int sind = s.charAt(i) - '0';
            int tind = t.charAt(i) - '0';
            if (sind == 0 && tind == 1) {
                if (sset.contains(1)) {
                    return true;
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            int sind = s.charAt(i) - '0';
            int tind = t.charAt(i) - '0';
            if (sind != tind) {
                return false;
            }
        }
        return true;
    }
}
