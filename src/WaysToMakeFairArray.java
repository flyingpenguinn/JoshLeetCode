public class WaysToMakeFairArray {
    public int waysToMakeFair(int[] a) {
        int n = a.length;
        int oddsum = 0;
        int evensum = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                evensum += a[i];
            } else {
                oddsum += a[i];
            }
        }
        int res = 0;
        int oddsofar = 0;
        int evensofar = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                evensofar += a[i];
            } else {
                oddsofar += a[i];
            }
            int laterodd = oddsum - oddsofar;
            int latereven = evensum - evensofar;
            int newodd = oddsofar + latereven;
            int neweven = evensofar + laterodd;
            if (i % 2 == 0) {
                neweven -= a[i];
            } else {
                newodd -= a[i];
            }
            if (newodd == neweven) {
                res++;
            }
        }
        return res;
    }
}
