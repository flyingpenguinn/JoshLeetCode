public class CapacityToShipCargoWithinDays {
    public int shipWithinDays(int[] weights, int d) {
        int sum = 0;
        int max = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i];
            max = Math.max(max, weights[i]);
        }
        int l = max;
        int u = sum;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (canShip(weights, d, mid)) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean canShip(int[] weights, int d, int mid) {
        int i = 0;
        int cur = mid;
        while (true) {
            if (cur >= weights[i]) {
                cur -= weights[i];
                i++;
                if (i == weights.length) {
                    return true;
                }
            } else {
                d--;
                if (d == 0) {
                    return false;
                }
                cur = mid;
            }
        }
    }

    public static void main(String[] args) {
        int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(new CapacityToShipCargoWithinDays().shipWithinDays(weights, 5));
    }
}
