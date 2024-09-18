import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class ConvertDoubleLinkedListToArrayII {
    class Node {
        public int val;
        public Node prev;
        public Node next;
    };

    public int[] toArray(Node n) {
        List<Integer> res = new ArrayList<>();
        Node  p = n;
        while (p != null) {
            res.add(p.val);
            p = p.prev;
        }
        Collections.reverse(res);
        p = n.next;
        while (p != null) {
            res.add(p.val);
            p = p.next;
        }
        int[] rr = new int[res.size()];
        for(int i=0; i<rr.length; ++i){
            rr[i] = res.get(i);
        }
        return rr;
    }
}
