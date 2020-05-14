import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CountSubstringOnlyOneDistinctChar {
    public int countLetters(String s) {
        int low = 0;
        int high = -1;
        int r = 0;
        Map<Character, Integer> chars = new HashMap<>();
        while (true) {
            if (chars.size() <= 1) {
                r += high - low + 1;
                high++;
                if (high == s.length()) {
                    break;
                }
                char highchar = s.charAt(high);
                chars.put(highchar, chars.getOrDefault(highchar, 0) + 1);
            } else {
                char lowchar = s.charAt(low);
                int newlow = chars.getOrDefault(lowchar, 0) - 1;
                if (newlow == 0) {
                    chars.remove(lowchar);
                } else {
                    chars.put(lowchar, newlow);
                }
                low++;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(new CountSubstringOnlyOneDistinctChar().countLetters("aaaaa"));
    }
}
