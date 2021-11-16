public class TimeNeededToBuyTickets {
    /*
    For i <= k, A[i] contributes min(A[k], A[i]) steps.

For i > k, A[i] contributes min(A[k] - 1, A[i]) steps.

     */
    int timeRequiredToBuy(int[] a, int k) {
        int ans = 0;
        for (int i = 0; i < a.length; ++i) {
            ans += Math.min(a[k] - (i > k ? 1 : 0), a[i]);
        }
        return ans;
    }
}
