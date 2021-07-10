public class SumGame {
    // 1. if odd ? diffs, Alice does the last, can always win as she can sabotage whatever
    // 2. if even, then if there are ?? left, the diff must be 9 because if it's 8, alice can do 9 and bob will suffer. so only when diff is 9 and ?? left can Bob win
    // 3. if ????m then need the diff to be 18
    public boolean sumGame(String s) {
        int n = s.length();
        int s1 = 0;
        int s1c = 0;
        for (int i = 0; i < n / 2; i++) {
            char c = s.charAt(i);
            if (c == '?') {
                s1c++;
            } else {
                int cind = c - '0';
                s1 += cind;
            }

        }
        int s2c = 0;
        int s2 = 0;
        for (int i = n / 2; i < n; i++) {
            char c = s.charAt(i);
            if (c == '?') {
                s2c++;
            } else {
                int cind = c - '0';
                s2 += cind;
            }
        }
        int diff = Math.abs(s1 - s2);
        int diffc = Math.abs(s1c - s2c);
        if (diffc % 2 == 1) {
            return true;
        }
        return diff != diffc / 2 * 9;
    }
}
