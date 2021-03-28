import java.util.HashSet;
import java.util.Set;

public class NumberOfDifferentIntegersInString {
    public int numDifferentIntegers(String s) {
        int n = s.length();
        Set<String> set = new HashSet<>();
        int i = 0;
        while (i < n) {
            while (i < n && !Character.isDigit(s.charAt(i))) {
                i++;
            }
            if (i == n) {
                break;
            }
            // i is start of num;
            int j = i;
            while (j < n && Character.isDigit(s.charAt(j))) {
                j++;
            }
            // j-1 is the end
            int k = i;
            while (k < j && s.charAt(k) == '0') {
                k++;
            }
            // k is the new start
            if (k == j) {
                set.add("0");
            } else {
                set.add(s.substring(k, j));
            }
            i = j;
        }
        return set.size();
    }
}
