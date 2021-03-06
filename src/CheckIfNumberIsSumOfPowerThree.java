import java.util.ArrayList;
import java.util.List;

public class CheckIfNumberIsSumOfPowerThree {
    // iterating over subset...
    private List<Integer> threes = new ArrayList<>();

    public boolean checkPowersOfThree(int n) {
        for (int stub = 1; stub <= n; stub *= 3) {
            threes.add(stub);
        }
        int k = threes.size();
        for (int st = 1; st < (1 << k); st++) {
            int sum = 0;
            for (int i = 0; i < k; i++) {
                if (((st >> i) & 1) == 1) {
                    sum += threes.get(i);
                }
            }
            if (sum == n) {
                return true;
            }
        }
        return false;
    }
}

class CheckIfNumberIsSumOfPower3Faster {
    // logn
    public boolean checkPowersOfThree(int n) {
        boolean allow1 = true;
        while (n > 1) {
            if (n % 3 == 0) {
                n /= 3;
                allow1 = true;
            } else if (allow1) {
                n--;
                allow1 = false;
            } else {
                return false;
            }
        }
        return n == 1 && allow1;
    }
}
