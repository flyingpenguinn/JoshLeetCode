import java.util.Arrays;

public class MaxGroupsEnteringCompetition {
    // can actually be simplified to find the biggest k so that 1+2+3...+k <=n
    public int maximumGroups(int[] a) {
        int n = a.length;
        Arrays.sort(a);

        long lsum = 0;
        int lcount = 0;
        int res = 0;
        int i = 0;
        while (i < n) {
            int j = i;
            long csum = 0L;
            int ccount = 0;
            while (j < n && ccount <= lcount) {
                ++ccount;
                csum += a[j];
                ++j;
            }
            if (ccount <= lcount) {
                break;
            }
            lsum = csum;
            lcount = ccount;
            ++res;
            i = j;
        }
        return res;
    }
}
