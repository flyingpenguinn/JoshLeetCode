public class CountTheNumberOfConsistentStrings {
    public int countConsistentStrings(String allowed, String[] words) {
        int res = 0;
        for (String w : words) {
            boolean bad = false;
            for (int i = 0; i < w.length(); i++) {
                if (allowed.indexOf(w.charAt(i)) == -1) {
                    bad = true;
                    break;
                }
            }
            if (!bad) {
                res++;
            }
        }
        return res;
    }
}
