public class MinNumberOfStepsToMakeAnagramII {
    public int minSteps(String s, String t) {
        int[] sm = new int[26];
        for (char sc : s.toCharArray()) {
            ++sm[sc - 'a'];
        }
        int[] tm = new int[26];
        for (char tc : t.toCharArray()) {
            ++tm[tc - 'a'];
        }
        int res = 0;
        for (int i = 0; i < 26; ++i) {
            int abs = Math.abs(sm[i] - tm[i]);
            res += abs;
        }
        return res;
    }
}
