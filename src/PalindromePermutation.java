import java.util.HashSet;
import java.util.Set;

public class PalindromePermutation {
    // can only have one odd char
    public boolean canPermutePalindrome(String s) {
        int[] count = new int[255];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)]++;
        }
        boolean seen = false;
        for (int i = 0; i < 255; i++) {
            if (count[i] % 2 == 1) {
                if (seen) {
                    return false;
                } else {
                    seen = true;
                }
            }
        }
        return true;
    }
}
