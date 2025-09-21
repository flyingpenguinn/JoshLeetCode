import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MinDiscardToBalanceInventory {
    private Map<Integer, Integer> vm = new HashMap<>();

    private void update(int k, int d) {
        int nv = vm.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            vm.remove(k);
        }
        vm.put(k, nv);
    }

    public int minArrivalsToDiscard(int[] a, int w, int m) {
        int n = a.length;
        int res = 0;
        Set<Integer> thrown = new HashSet<>();
        for (int i = 0; i < w - 1; ++i) {
            update(a[i], 1);
            if (vm.get(a[i]) > m) {
                ++res;
                update(a[i], -1);
                thrown.add(i);
            }
        }

        for (int i = w - 1; i < n; ++i) {
            update(a[i], 1);
            if (vm.get(a[i]) > m) {
                ++res;
                update(a[i], -1);
                thrown.add(i);
            }
            int head = i - w + 1;
            if (!thrown.contains(head)) {
                update(a[head], -1);
            }
        }
        return res;
    }
}
