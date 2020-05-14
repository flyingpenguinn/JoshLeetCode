import base.ArrayUtils;

import java.util.*;

public class OnlineMajorityElement {
    public static void main(String[] args) {

    }

}

// would have random picked, but for some reason it always fails due to poor random number generator...
class MajorityChecker {
    int[] a;

    public MajorityChecker(int[] arr) {
        this.a = arr;
    }

    public int query(int left, int right, int threshold) {
        int nt = 1;
        int ma = a[left];
        for (int i = left + 1; i <= right; i++) {
            if (a[i] == ma) {
                nt++;
            } else {
                nt--;
                if (nt == 0) {
                    ma = a[i];
                    nt = 1;
                }
            }
        }
        int count = 0;
        for (int i = left; i <= right; i++) {
            if (a[i] == ma) {
                count++;
            }
        }
        if (count >= threshold) {
            return ma;
        } else {
            return -1;
        }
    }
}