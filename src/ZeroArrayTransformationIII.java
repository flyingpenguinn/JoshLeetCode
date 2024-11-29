import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class ZeroArrayTransformationIII {
    public int maxRemoval(int[] a, int[][] qs) {
        // greedy. use the internal that can cover a[i] and is farthest
        int n = a.length;
        Arrays.sort(qs, (x, y) -> Integer.compare(x[0], y[0]));
        PriorityQueue<Integer> using = new PriorityQueue<>();
        PriorityQueue<Integer> cand = new PriorityQueue<>(Collections.reverseOrder());

        int needed = 0;
        int qi = 0;
        for (int i = 0; i < n; ++i) {
            int v = a[i];
            //   System.out.println("checking "+i);
            while (!using.isEmpty() && using.peek() < i) {
                //     System.out.println(using.peek() +" cannot cover "+i);
                using.poll();
            }
            //   System.out.println(using.size()+" left over to cover "+i);
            v -= using.size(); // these can cover v
            while (qi < qs.length && qs[qi][0] <= i) {
                if (qs[qi][1] >= i) {
                    //    System.out.println("offering cand "+qs[qi][1]);
                    cand.offer(qs[qi][1]);
                }
                ++qi;
            }
            while (v > 0 && !cand.isEmpty()) {
                //    System.out.println("using "+cand.peek());
                if (cand.peek() >= i) {
                    using.offer(cand.poll());
                    --v;
                    ++needed;
                } else {
                    break;
                }
            }
            if (v > 0) {
                //  System.out.println("cannot cover "+v);
                return -1;
            }
        }
        //  System.out.println("needed="+needed);
        return qs.length - needed;
    }
}
