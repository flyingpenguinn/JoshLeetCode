import base.ArrayUtils;

import java.io.*;

public class LongestTurbulantSubarray {

    int[][] dp;

    public int maxTurbulenceSize(int[] a) {
        int max = 0;
        dp = new int[a.length][2];
        for (int i = 0; i < a.length; i++) {
            int small = doMax(a, i, 1);
            int big = doMax(a, i, 0);
            int curmax = Math.max(small, big);
            max = Math.max(max, curmax);
        }
        return max;
    }


    private int doMax(int[] a, int i, int issmaller) {

        if (i == a.length - 1) {
            return 1;
        }
        if (dp[i][issmaller] != 0) {
            return dp[i][issmaller];
        }
        int rt = 0;
        // want a subarray starting with samller
        if (issmaller == 1) {
            if (a[i] < a[i + 1]) {
                rt = 1 + doMax(a, i + 1, 0);
            } else {
                rt = 1;
            }
        }
        // want a >
        else {
            if (a[i] > a[i + 1]) {
                rt = 1 + doMax(a, i + 1, 1);
            } else {
                rt = 1;
            }
        }
        dp[i][issmaller] = rt;
        return rt;
    }

    public static void main(String[] args) throws IOException {
        String file = "E:\\dev\\project\\JoshLeet\\tests\\longestturbulantsubarray.txt";
        BufferedReader reader = new BufferedReader(new FileReader(new File(file)));
        String s = reader.readLine();
        System.out.println(new LongestTurbulantSubarrayBottomUp().maxTurbulenceSize(ArrayUtils.read1d("[9,4,2,10,7,8,8,1,9]")));
    }
}

class LongestTurbulantSubarrayBottomUp {
  // dpi only depends on dpi+1, dont even need the array
    public int maxTurbulenceSize(int[] a) {
        int n = a.length;
        int max = 1;
        int smaller = 1;
        int bigger = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (a[i] > a[i + 1]) {
                bigger = 1 + smaller;
                smaller = 1;
            } else if (a[i] < a[i + 1]) {
                smaller = 1 + bigger;
                bigger = 1;
            } else {
                smaller = bigger = 1;
            }
            max = Math.max(max, Math.max(smaller, bigger));
        }
        return max;
    }
}
