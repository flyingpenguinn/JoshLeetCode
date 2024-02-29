class Problem {
    int commonSetBits(int num) {
        return 1;
    }
}

public class GuessNumberUsingBitwiseOperationI extends Problem {
    public int findNumber() {
        int res = 0;
        for (int i = 0; i < 32; ++i) {
            int num = (1 << i);
            if (commonSetBits(num) == 1) {
                res |= (1 << i);
            }
        }
        return res;
    }
}
