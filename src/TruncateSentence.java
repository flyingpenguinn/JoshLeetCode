public class TruncateSentence {
    public String truncateSentence(String s, int k) {
        String[] ss = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            if (i > 0) {
                sb.append(" ");
            }
            sb.append(ss[i]);
        }
        return sb.toString();
    }
}
