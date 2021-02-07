public class LargestMergeOfTwoStrings {
    // if eq, choose from the biggest string
    public String largestMerge(String w1, String w2) {
        int i = 0;
        int j = 0;
        StringBuilder sb = new StringBuilder();
        while (i < w1.length() && j < w2.length()) {
            if (w1.charAt(i) > w2.charAt(j)) {
                sb.append(w1.charAt(i));
                i++;
            } else if (w1.charAt(i) < w2.charAt(j)) {
                sb.append(w2.charAt(j));
                j++;
            } else {
                if (bigger(w1, i, w2, j)) {
                    sb.append(w1.charAt(i));
                    i++;
                } else {
                    sb.append(w2.charAt(j));
                    j++;
                }
            }
        }
        if (i < w1.length()) {
            sb.append(w1.substring(i));
        }
        if (j < w2.length()) {
            sb.append(w2.substring(j));
        }
        return sb.toString();
    }

    private boolean bigger(String s1, int i, String s2, int j) {
        while (i < s1.length() && j < s2.length()) {
            if (s1.charAt(i) > s2.charAt(j)) {
                return true;
            } else if (s1.charAt(i) < s2.charAt(j)) {
                return false;
            } else {
                i++;
                j++;
            }
        }
        return i < s1.length();
    }
}
