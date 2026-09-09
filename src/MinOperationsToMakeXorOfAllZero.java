import base.ArrayUtils;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class MinOperationsToMakeXorOfAllZero {
    public int minOperations(int[] a) {
        int n = a.length;
        int xor = 0;
        Set<Integer> set = new HashSet<>();
        Set<Integer> seen = new HashSet<>();
        for (int ai : a) {
            xor ^= ai;
            set.add(ai);
        }

        if (xor == 0) {
            return 0;
        }
        if(set.size()==1){
            return -1;
        }
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[]{0, 0});
        seen.add(0);
        int mincnt = -1;
        while (!dq.isEmpty()) {
            int[] top = dq.pollFirst();
            int num = top[0];
            int cnt = top[1];
            if (num == xor) {
                mincnt = cnt;
                break;
            }
            int ncnt = cnt + 1;
            for (int si : set) {
                int cxor = num ^ si;
                if (seen.contains(cxor)) {
                    continue;
                }
                seen.add(cxor);
                dq.offerLast(new int[]{cxor, ncnt});
            }
        }
        if (mincnt == n) {
            return -1;
        }
        return mincnt;
    }

    static void main() {
        System.out.println(new MinOperationsToMakeXorOfAllZero().minOperations(ArrayUtils.read1d("6,15,15,15")));
        System.out.println(new MinOperationsToMakeXorOfAllZero().minOperations(ArrayUtils.read1d("8,1,4,8,2")));
    }
}
