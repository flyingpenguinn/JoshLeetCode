import java.util.Arrays;
import java.util.PriorityQueue;

public class MinCostPathWithAlternatingDirectionsI {
    // only 1,1   1,2   2,1
    public int minCost(int m, int n) {
        if (m == 1 && n == 1) {
            return 1;
        }
        if ((m == 1 && n == 2)
                || (m == 2 && n == 1)) {
            return 3;
        }
        return -1;
    }

}
