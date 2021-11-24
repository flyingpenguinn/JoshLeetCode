public class WateringPlants {
    public int wateringPlants(int[] a, int capacity) {
        int n = a.length;
        int pos = -1;
        int cap = capacity;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            res += i - pos;
            pos = i;
            cap -= a[i];
            if (i + 1 == n) {
                break;
            } else {
                if (cap < a[i + 1]) {
                    res += i + 1;
                    cap = capacity;
                    pos = -1;
                }
            }
        }
        return res;
    }
}
