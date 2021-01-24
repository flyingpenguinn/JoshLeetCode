public class ChangeMinCharsToSatisfyThreeConditions {
    // enumerate the boundary
    // can use the maps to get counts quickly
    public int minCharacters(String a, String b) {
        int an = a.length();
        int bn = b.length();
        int res = 10000000;
        // cond3
        int[] am = new int[26];
        int[] bm = new int[26];
        for (int i = 0; i < an; i++) {
            am[a.charAt(i) - 'a']++;
        }
        for (int i = 0; i < bn; i++) {
            bm[b.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            res = Math.min(res, an + bn - am[i] - bm[i]);
        }
        // cond 1
        for (char c = 'b'; c <= 'z'; c++) {
            int c1 = getcount(am, bm, c);
            res = Math.min(res, c1);
        }

        // cond 2
        for (char c = 'b'; c <= 'z'; c++) {
            int c2 = getcount(bm, am, c);
            res = Math.min(res, c2);
        }
        return res;
    }

    protected int getcount(int[] am, int[] bm, char c) {
        int c1 = 0;
        for (int i = 0; i < c - 'a'; i++) {
            c1 += am[i];
        }
        for (int i = c - 'a'; i < 26; i++) {
            c1 += bm[i];
        }
        return c1;
    }
}
