public class MinCensorToCoverGrid {
    public int minSensors(int n, int m, int k) {
        int len = 2 * k + 1;
        int v1 = (int) Math.ceil(n * 1.0 / len);
        int v2 = (int) Math.ceil(m * 1.0 / len);
        return v1 * v2;
    }
}
