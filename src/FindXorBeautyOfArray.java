public class FindXorBeautyOfArray {
    public int xorBeauty(int[] a) {
        int xor = 0;
        for (int ai : a) {
            xor ^= ai;
        }
        return xor;
    }
}
