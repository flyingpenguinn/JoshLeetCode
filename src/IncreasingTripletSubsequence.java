public class IncreasingTripletSubsequence {
    public boolean increasingTriplet(int[] a) {
        int n = a.length;
        if (n == 0) {
            return false;
        }
        int[] maxr = new int[n];
        maxr[n - 1] = a[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            maxr[i] = Math.max(maxr[i + 1], a[i]);
        }
        int min = a[0];
        for (int i = 1; i < n; i++) {
            if (a[i] > min && a[i] < maxr[i]) {
                return true;
            }
            min = Math.min(min, a[i]);
        }
        return false;
    }
}

class IncreasingTripletSubsequenceO1space {
    // enumerate the last element, make sure it's > the best candidate of the middle number
    // note at some point, cand is before min, but that's ok
    // can also do LIS and len = 3 so it's nlog3 == O(n)
    public boolean increasingTriplet(int[] a) {
        int n = a.length;
        if (n < 3) {
            return false;
        }
        int min = Integer.MAX_VALUE; // smallest
        int cand = Integer.MAX_VALUE; // best candidate for number 2. it has > some min
        for (int i = 0; i < n; i++) {
            if (a[i] > cand) {
                return true;
            }
            if (a[i] < min) {
                min = a[i];
            } else if (a[i] > min) {
                // a[i]<=cand
                cand = a[i];
            }
        }
        return false;
    }
}
