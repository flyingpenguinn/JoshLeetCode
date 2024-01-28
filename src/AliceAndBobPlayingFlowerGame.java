public class AliceAndBobPlayingFlowerGame {
    public long flowerGame(long n, long m) {
        long res = 0;
        for (int i = 1; i <= n; ++i) {
            if (i % 2 == 0) {
                if (m % 2 == 0) {
                    res += m / 2;
                } else {
                    res += (m + 1) / 2;
                }
            } else {
                if (m % 2 == 0) {
                    res += m / 2;
                } else {
                    res += m / 2;
                }
            }
        }
        return res;
    }
}
