import base.ArrayUtils;

import java.util.Arrays;

public class GrumpyBookstore {
    public int maxSatisfied(int[] customers, int[] grumpy, int x) {
        int n = customers.length;
        int accuraw = 0;
        int accucure = 0;
        int maxdiff = 0;
        int allraw = 0;
        for (int i = 0; i < n; i++) {
            int cur = customers[i] * (grumpy[i] == 1 ? 0 : 1);
            accuraw += cur;
            allraw += cur;
            accucure += customers[i];
            maxdiff = Math.max(maxdiff, accucure - accuraw);
            int head = i - x + 1;
            if (head >= 0) {
                accuraw -= customers[head] * (grumpy[head] == 1 ? 0 : 1);
                accucure -= customers[head];
            }
        }
        return allraw + maxdiff;
    }

    public static void main(String[] args) {
        System.out.println(new GrumpyBookstore().maxSatisfied(ArrayUtils.read1d("10,1,7"), ArrayUtils.read1d("0,0,0"), 2));
    }
}
