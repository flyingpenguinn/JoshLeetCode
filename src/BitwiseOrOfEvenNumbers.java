public class BitwiseOrOfEvenNumbers {
    public int evenNumberBitwiseORs(int[] a) {
        int n = a.length;
        int or = 0;
        for (int ai : a) {
            if (ai % 2 == 0) {
                or |= ai;
            }
        }
        return or;
    }
}
