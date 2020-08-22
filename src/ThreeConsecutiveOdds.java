public class ThreeConsecutiveOdds {
    public boolean threeConsecutiveOdds(int[] a) {
        for (int i = 0; i + 2 < a.length; i++) {
            if (a[i] % 2 == 1 && a[i + 1] % 2 == 1 && a[i + 2] % 2 == 1) {
                return true;
            }
        }
        return false;
    }
}
