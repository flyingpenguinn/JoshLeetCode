import java.util.ArrayList;
import java.util.List;

public class ConvertDoubleListToArrayI {
    public int[] toArray(Node head) {
        List<Integer> list = new ArrayList<>();
        Node p = head;
        while (p != null) {
            list.add(p.val);
            p = p.next;
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            res[i] = list.get(i);
        }

        return res;
    }
}
