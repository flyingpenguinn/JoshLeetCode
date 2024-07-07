public class FindEncrypteString {
    public String getEncryptedString(String s, int k) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            int ni = (i + k) % n;
            char nc = s.charAt(ni);
            sb.append(nc);
        }
        return sb.toString();
    }
}
