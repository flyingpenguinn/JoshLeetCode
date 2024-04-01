public class WaterBottlesII {
    public int maxBottlesDrunk(int a, int b) {
        int res = 0;
        int full = a;
        int empty = 0;
        while (full > 0) {
            // System.out.println(full+" "+empty);
            res += full;
            empty += full;
            full = 0;
            while (empty >= b) {
                empty -= b;
                full += 1;
                ++b;
            }
        }
        return res;
    }
}
