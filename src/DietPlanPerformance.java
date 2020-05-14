public class DietPlanPerformance {
    // sliding window
    public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
        int sum = 0;
        int r = 0;
        for (int i = 0; i < calories.length; i++) {
            // in a k length window i-k+1 is the top
            sum += calories[i];
            if (i - k + 1 >= 0) {
                if (sum < lower) {
                    r--;
                } else if (sum > upper) {
                    r++;
                }
                sum -= calories[i - k + 1];
            }
        }
        return r;
    }
}
