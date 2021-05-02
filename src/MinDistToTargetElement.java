public class MinDistToTargetElement {
    public int getMinDistance(int[] a, int target, int start) {
        int n = a.length;
        int mindiff = 10000000;
        for (int i = 0; i < n; i++) {
            if (a[i] == target) {
                if (Math.abs(i - start) < mindiff) {
                    mindiff = Math.abs(i - start);
                }
            }
        }
        return mindiff;
    }
}
