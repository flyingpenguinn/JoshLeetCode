import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FindCountOfGoodIntegers {
    private long res = 0;
    private Set<String> set = new HashSet<>();

    public long countGoodIntegers(int n, int k) {
        res = 0;
        gen(0, n - 1, 0, 0, 0, k);
        return res;
    }

    private void gen(int i, int j, long n1, long n2, long base, int k) {

        long pow = (long) Math.pow(10, base);
        if (i > j) {


            long cur = n1 * pow + n2;
            if (cur != 0 && cur % k == 0) {
                String sn = ssort(cur);
                if (set.contains(sn)) {
                    return;
                }
                set.add(sn);
                long cnt = calculateRearrangements(cur);
                res += cnt;
                //               System.out.println(cur + " " + cnt);
            }
            return;
        }
        if (i == j) {

            for (int m = 0; m <= 9; ++m) {
                long cur = n1 * pow * 10 + m * pow + n2;
                if (cur != 0 && cur % k == 0) {
                    String sn = ssort(cur);
                    if (set.contains(sn)) {
                        return;
                    }
                    set.add(sn);
                    long cnt = calculateRearrangements(cur);
                    res += cnt;
                    //          System.out.println(cur + " " + cnt);
                }
            }
            return;
        }
        int start = 0;
        if (i == 0) {
            start = 1;
        }
        for (int p = start; p <= 9; ++p) {
            long nn1 = n1 * 10 + p;
            long nn2 = p * pow + n2;
            gen(i + 1, j - 1, nn1, nn2, base + 1, k);
        }
    }

    private String ssort(long n1) {
        char[] cn1 = String.valueOf(n1).toCharArray();
        Arrays.sort(cn1);
        return new String(cn1);
    }

    private long factorial(long n) {
        long result = 1;
        for (long i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public long calculateRearrangements(long number) {
        int[] digitCount = new int[10];
        int n = 0;

        while (number > 0) {
            int digit = (int) (number % 10);
            digitCount[digit]++;
            number /= 10;
            n++;
        }

        long totalPermutations = factorial(n);
        for (int count : digitCount) {
            if (count > 1) {
                totalPermutations /= factorial(count);
            }
        }

        if (digitCount[0] > 0) {
            long total2 = 0;
            long totalIndividual = 1;
            for (int i = 1; i <= 9; ++i) {
                if (digitCount[i] == 0) {
                    continue;
                }
                total2 += digitCount[i];
                totalIndividual *= factorial(digitCount[i]);
            }
            total2 += digitCount[0] - 1;
            long totalfact = factorial(total2);

            totalIndividual *= factorial(digitCount[0] - 1);
            long deduct = totalfact / totalIndividual;
            totalPermutations -= deduct;
        }
        return totalPermutations;
    }
}
