import java.util.HashSet;
import java.util.Set;

public class RestoreFinishingOrder {
    public int[] recoverOrder(int[] orders, int[] friends) {
        Set<Integer> set = new HashSet<>();
        for (int fi : friends) {
            set.add(fi);
        }
        int[] res = new int[friends.length];
        int ri = 0;
        for (int i = 0; i < orders.length; ++i) {
            if (set.contains(orders[i])) {
                res[ri++] = orders[i];
            }
        }
        return res;
    }
}
