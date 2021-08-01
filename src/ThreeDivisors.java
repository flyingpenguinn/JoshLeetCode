public class ThreeDivisors {
    public boolean isThree(int n) {
        int res = 0;
        for (int i = 1; i * i <= n && res<=3; i++) {
            if (n % i == 0) {
                int other = n / i;
                if (i == other) {
                    res++;
                } else {
                    res += 2;
                }
            }
        }
        return res == 3;
    }
}
