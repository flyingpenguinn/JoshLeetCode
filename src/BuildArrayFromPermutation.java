public class BuildArrayFromPermutation {
    public int[] buildArray(int[] a) {
        int[] res = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            res[i] = a[a[i]];
        }
        return res;
    }
}
