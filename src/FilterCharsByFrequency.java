public class FilterCharsByFrequency {
    public String filterCharacters(String s, int k) {
        int n = s.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            int cind = s.charAt(i) - 'a';
            if (cnt[cind] < k) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
