import java.util.HashMap;
import java.util.Map;

public class RabbitInForest {
    // key on rabbit count... when it's too many, delete that key and enter a new one
    public int numRabbits(int[] a) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = a.length;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            int curall = a[i] + 1;
            Integer cc = map.get(curall);
            if (cc == null) {
                sum += curall;
            }

            int ncc = (cc == null ? a[i] + 1 : cc) - 1;
            if (ncc == 0) {
                //  System.out.println("rem "+curall);
                map.remove(curall);
            } else {
                // System.out.println("add "+curall+" "+ncc);

                map.put(curall, ncc);
            }

        }

        return sum;
    }
}
