public class CheckIfBitwiseOrHaveTrailingZeros {
    public boolean hasTrailingZeros(int[] a) {
        int n = a.length;
        int oc = 0;
        for (int ai : a) {
            if (ai % 2 == 0) {
                ++oc;
                if (oc >= 2) {
                    return true;
                }
            }
        }
        return false;
    }
}
