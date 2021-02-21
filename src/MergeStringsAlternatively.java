public class MergeStringsAlternatively {
    public String mergeAlternately(String word1, String word2) {
        int i = 0;
        int j = 0;
        StringBuilder sb = new StringBuilder();
        boolean w1 = true;
        while (i < word1.length() && j < word2.length()) {
            if (w1) {
                sb.append(word1.charAt(i));
                i++;
            } else {
                sb.append(word2.charAt(j));
                j++;
            }
            w1 = !w1;
        }
        while (i < word1.length()) {
            sb.append(word1.charAt(i++));
        }
        while (j < word2.length()) {
            sb.append(word2.charAt(j++));
        }
        return sb.toString();
    }
}
