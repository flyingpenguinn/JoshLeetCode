import java.util.HashSet;
import java.util.Set;

public class PalindromePermutation {
    // use set to get one pass
    public boolean canPermutePalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (set.contains(c)) {
                set.remove(c);
            } else {
                set.add(c);
            }
        }
        return set.size() <= 1;
    }
}
