public class MaxNumOfBalloons {
    public int maxNumberOfBalloons(String text) {
        int[] chars = new int[26];
        for (int i = 0; i < text.length(); i++) {
            chars[text.charAt(i) - 'a']++;
        }
        String ballon = "ballon";
        int[] bchars = new int[26];
        int c = text.length();
        for (int i = 0; i < ballon.length(); i++) {
            bchars[ballon.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (bchars[i] > 0) {
                c = Math.min(chars[i] / bchars[i], c);
            }
        }
        return c;

    }
}
