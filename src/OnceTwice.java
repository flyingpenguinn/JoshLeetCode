import base.ArrayUtils;

import java.util.Arrays;

public class OnceTwice {
    public int[] onceTwice(int[] a) {
        int n = a.length;
        int[] count = new int[32];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < 32; ++j) {
                if (((a[i] >> j) & 1) == 1) {
                    ++count[j];
                }
            }
        }

        int diff = 0;
        for (int j = 0; j < 32; ++j) {
            if (count[j] % 3 != 0) {
                // 1 and 2 differ here
                diff |= (1 << j);
            }
        }
        int onebitdiff = diff & (-diff);
        int[] ncount = new int[32];
        int nocc = 0;
        for (int i = 0; i < n; ++i) {
            if( (a[i] & onebitdiff) == 0){
                continue;
            }
            ++nocc;
            for (int j = 0; j < 32; ++j) {
                if (((a[i] >> j) & 1) == 1) {
                    ++ncount[j];
                }
            }
        }
        int v1 = 0;
        for (int j = 0; j < 32; ++j) {
            if (ncount[j] % 3 == nocc % 3) {
                v1 |= (1 << j);
            }
        }
        int v2 = diff ^ v1;
        if (nocc %3 == 2) {
            int tmp = v1;
            v1 = v2;
            v2 = tmp;
        }
        return new int[]{v1, v2};
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(new OnceTwice().onceTwice(ArrayUtils.read1d("2,2,3,2,5,5,5,7,7"))));
        System.out.println(Arrays.toString(new OnceTwice().onceTwice(ArrayUtils.read1d("4,4,6,4,9,9,9,6,8"))));
    }
}
