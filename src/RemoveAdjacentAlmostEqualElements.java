public class RemoveAdjacentAlmostEqualElements {
    // another way: if we changed i, we can skip i+1 too
    public int removeAlmostEqualCharacters(String word) {
        char[] s = word.toCharArray();
        int n = s.length;
        int res = 0;
        int i = 0;
        while (i < n) {
            int j = i + 1;
            while (j < n && Math.abs(s[j] - s[j - 1]) <= 1) {
                ++j;
            }
            int len = j - i;
            int cur = len / 2;
            res += cur;
            i = j;
        }
        return res;
    }
}
