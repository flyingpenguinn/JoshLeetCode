public class TotalDistanceTypingUsingOneFinger {
    private String[] kb = {"qwertyuiop", "asdfghjkl", "zxcvbnm"};

    public int totalDistance(String s) {
        int[][] m = new int[26][2];
        for (int i = 0; i < kb.length; ++i) {
            for (int j = 0; j < kb[i].length(); ++j) {
                int cind = kb[i].charAt(j) - 'a';
                m[cind][0] = i;
                m[cind][1] = j;
            }
        }
        int[] cur = m[0];
        int res = 0;
        for (int i = 0; i < s.length(); ++i) {
            int cind = s.charAt(i) - 'a';
            int[] next = m[cind];
            int cd = Math.abs(next[1] - cur[1]) + Math.abs(next[0] - cur[0]);
            res += cd;
            cur = next;
        }
        return res;
    }
}
