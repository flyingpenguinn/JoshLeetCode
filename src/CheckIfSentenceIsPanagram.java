public class CheckIfSentenceIsPanagram {
    public boolean checkIfPangram(String s) {
        int[] occ = new int[26];
        for (int i = 0; i < s.length(); i++) {
            occ[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (occ[i] == 0) {
                return false;
            }
        }
        return true;
    }
}
