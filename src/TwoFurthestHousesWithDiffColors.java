public class TwoFurthestHousesWithDiffColors {
    //Find the last house with different color of the fisrt house.
    //Find the first house with different color of the last house.
    public int maxDistance(int[] a) {
        int n = a.length, i = 0, j = n - 1;
        while (a[0] == a[j]) j--;
        while (a[n - 1] == a[i]) i++;
        return Math.max(n - 1 - i, j);
    }
}
