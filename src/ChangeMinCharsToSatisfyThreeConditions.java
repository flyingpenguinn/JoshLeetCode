public class ChangeMinCharsToSatisfyThreeConditions {
    public int minCharacters(String a, String b) {
        int an = a.length();
        int bn = b.length();
        int res = 10000000;
        for (char c = 'a'; c <= 'z'; c++) {
            int c3 = 0;
            for (int i = 0; i < an; i++) {
                if (a.charAt(i) != c) {
                    c3++;
                }
            }
            for (int i = 0; i < bn; i++) {
                if (b.charAt(i) != c) {
                    c3++;
                }
            }
            res = Math.min(res, c3);
        }
        // min in a, b must be strictly less
        for (char c = 'b'; c <= 'z'; c++) {
            int c1 = getcount(a, b, c);
            res = Math.min(res, c1);
        }
        // min in b

        for (char c = 'b'; c <= 'z'; c++) {
            int c2 = getcount(b, a, c);
            res = Math.min(res, c2);
        }
        return res;
    }

    protected int getcount(String a, String b, char c) {
        int c1 = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) < c) {
                c1++;
            }
        }
        for (int i = 0; i < b.length(); i++) {
            if (b.charAt(i) >= c) {
                c1++;
            }
        }
        return c1;
    }
}
