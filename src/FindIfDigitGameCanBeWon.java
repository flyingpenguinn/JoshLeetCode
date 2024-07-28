public class FindIfDigitGameCanBeWon {
    public boolean canAliceWin(int[] a) {
        int n = a.length;
        int singles = 0;
        int doubles = 0;
        for (int ai : a) {
            if (ai < 10) {
                singles += ai;
            } else {
                doubles += ai;
            }
        }
        if (singles > doubles || doubles > singles) {
            return true;
        }
        return false;
    }
}
