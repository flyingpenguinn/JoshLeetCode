public class BulbSwitcherIV {
    public int minFlips(String target) {
        int flips = 0;
        for (int i = 0; i < target.length(); i++) {
            int t = target.charAt(i) - '0';
            if (t == 0) {
                if (flips % 2 == 1) {
                    flips++;
                }
            } else {
                if (flips % 2 == 0) {
                    flips++;
                }
            }
        }
        return flips;
    }
}
