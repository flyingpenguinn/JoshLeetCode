public class CircularSentence {
    public boolean isCircularSentence(String sentence) {
        String[] ss = sentence.split(" ");
        for (int i = 0; i < ss.length; ++i) {
            if (i < ss.length - 1) {
                if (ss[i].charAt(ss[i].length() - 1) != ss[i + 1].charAt(0)) {
                    return false;
                }
            } else {
                if (ss[i].charAt(ss[i].length() - 1) != ss[0].charAt(0)) {
                    return false;
                }
            }
        }
        return true;
    }
}
