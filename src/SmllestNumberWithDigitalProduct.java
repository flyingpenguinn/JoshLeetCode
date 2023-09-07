public class SmllestNumberWithDigitalProduct {
    public String smallestNumber(long n) {
        if (n < 10) {
            return String.valueOf(n);
        }
        int[] digs = new int[10];
        int twos = 0;
        while (n % 2 == 0) {
            n /= 2;
            ++twos;
        }
        int threes = 0;
        while (n % 3 == 0) {
            n /= 3;
            ++threes;
        }
        digs[9] = threes / 2;
        threes -= digs[9] * 2;
        digs[8] = twos / 3;
        twos -= digs[8] * 3;
        digs[6] = Math.min(twos, threes);
        twos -= digs[6];
        threes -= digs[6];
        digs[4] = twos / 2;
        twos -= digs[4] * 2;


        digs[2] = twos;
        digs[3] = threes;
        while (n % 5 == 0) {
            ++digs[5];
            n /= 5;
        }
        while (n % 7 == 0) {
            ++digs[7];
            n /= 7;
        }
        if (n != 1) {
            return "-1";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= 9; ++i) {
            for (int j = 0; j < digs[i]; ++j) {
                sb.append(i);
            }
        }
        return sb.toString();
    }
}
