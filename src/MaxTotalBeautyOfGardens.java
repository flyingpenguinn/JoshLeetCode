import base.ArrayUtils;

import java.io.*;
import java.util.Arrays;

public class MaxTotalBeautyOfGardens {
    // iterate from n-1 to 0 to see if we make i...n-1 all = target, and 0...i-1 all to a given value
    public long maximumBeauty(int[] a, long newFlowers, int target, int full, int partial) {
        Arrays.sort(a);
        int n = a.length;
        long[] sum = new long[n];
        sum[0] = a[0];
        int biggerThanTarget = -1;
        for (int i = 0; i < n; ++i) {
            sum[i] = (i == 0 ? 0 : sum[i - 1]) + a[i];
            if (biggerThanTarget == -1 && a[i] >= target) {
                biggerThanTarget = i;
            }
        }

        long res = 0;
        if (biggerThanTarget > 0) {
            res = (0L + n - biggerThanTarget) * full + a[0] * partial;
        } else if (biggerThanTarget == 0) {
            res = (0L + n) * full;
            return res;
        } else {
            res = (0L + a[0]) * partial;
        }
        long delta0 = 0;
        for (int i = n; i >= 0; --i) { // start from n because we can make 0..n-1 all <target
            // 0..i-1 will be made into mid. i...n-1 will all = target
            if (i < n) {
                delta0 += (a[i] < target ? target - a[i] : 0);
            }
            long k = newFlowers;
            if (k < delta0) {
                break;
            }
            k -= delta0;
            long l = 1;
            long u = target - 1;
            while (l <= u) {
                long mid = l + (u - l) / 2;
                int lastSmaller = findLastSmaller(a, 0, i - 1, mid);
                // 0.. lastSmaller needs added
                long realSmalllerSum = lastSmaller == -1 ? 0 : sum[lastSmaller];
                long wantedSum = (lastSmaller + 1) * mid;
                long delta1 = wantedSum - realSmalllerSum;
                if (k >= delta1) {
                    l = mid + 1;
                } else {
                    u = mid - 1;
                }
            }
            if (i == 0) {
                // no smaller than target
                long cur = n * full;
                res = Math.max(cur, res);
            } else {
                long cur = u * partial + (0L + n - i) * full;
                //       System.out.println(cur + " " + i);
                res = Math.max(cur, res);
            }
        }
        return res;
    }

    private int findLastSmaller(int[] a, int p, int q, long target) {
        while (p <= q) {
            int m3 = p + (q - p) / 2;
            if (a[m3] >= target) {
                q = m3 - 1;
            } else {
                p = m3 + 1;
            }
        }
        return q;
    }


    public static void main(String[] args) throws IOException {
        String file = "E:\\dev\\project\\JoshLeet\\tests\\MaxTotalBeauty.txt";
        BufferedReader reader = new BufferedReader(new FileReader(new File(file)));
        String seq = reader.readLine();
        String[] ss = seq.split(",");
        int[] input = new int[ss.length];
        int ii = 0;
        for (String s : ss) {
            input[ii++] = Integer.valueOf(s);
        }


        System.out.println(new MaxTotalBeautyOfGardens().maximumBeauty(input, 2641611413L, 100000, 70933, 15142));
      /* System.out.println(new MaxTotalBeautyOfGardens().maximumBeauty(ArrayUtils.read1d("[89287,5538,37141,72522,84502,44451,24432,2324,72779,57060,99178,6,29440,53664,76197,46742,17384,22072,33067,66274,19323,72943,12914,91475,96826,84847,28100,89590,34977,74052,4813,24563,97491,71687,8533,49262,2265,10553,63902,19647,27006,64548,89892,64046,72766,34623,17297,21417,70630,93469,83379,19483,93842,65968,28401,1889,24441,99401,37907,13794,3640,95432,36875,10200,95360,10829,96763,15900,8490,68972,52537,72458,95269]"), 42, 4534, 32415, 11040));
        System.out.println(new MaxTotalBeautyOfGardens().maximumBeauty(ArrayUtils.read1d("[13]"), 18, 15, 9, 2));

        System.out.println(new MaxTotalBeautyOfGardens().maximumBeauty(ArrayUtils.read1d("[20,1,15,17,10,2,4,16,15,11]"), 2, 20, 10, 2));

        System.out.println(new MaxTotalBeautyOfGardens().maximumBeauty(ArrayUtils.read1d(" [18,16,10,10,5]"), 10, 3, 15, 4));
        System.out.println(new MaxTotalBeautyOfGardens().maximumBeauty(ArrayUtils.read1d("[1,1]"), 2, 2, 1, 2));
        System.out.println(new MaxTotalBeautyOfGardens().maximumBeauty(ArrayUtils.read1d("[1,3,1,1]"), 7, 6, 12, 1));
        System.out.println(new MaxTotalBeautyOfGardens().maximumBeauty(ArrayUtils.read1d("[2,4,5,3]"), 10, 5, 2, 6));
*/

    }
}
