public class RichestCustomerWealth {
    public int maximumWealth(int[][] a) {
        int max = -1;
        int maxi = -1;
        for (int i = 0; i < a.length; i++) {
            int sum = 0;
            for (int j = 0; j < a[0].length; j++) {
                sum += a[i][j];
            }
            if (sum > max) {
                max = sum;
                maxi = i;
            }
        }
        return max;
    }
}
