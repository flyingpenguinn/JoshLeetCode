import java.util.Arrays;

public class LargestSubstringBetweenTwoEqualChars {
    public int maxLengthBetweenEqualCharacters(String s) {
        int[] first = new int[26];
        Arrays.fill(first, -1);
        int max = -1;
        for (int i = 0; i < s.length(); i++) {
            int cind = s.charAt(i) - 'a';
            if (first[cind] != -1) {
                max = Math.max(max, i - first[cind] - 1);
            }
            if (first[cind] == -1) {
                first[cind] = i;
            }
        }
        return max;
    }
}
