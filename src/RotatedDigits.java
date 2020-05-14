
public class RotatedDigits {
    int[] goodchars = {2, 5, 6, 9};
    int[] nonrotates = {3, 4, 7};

    // divide the numesr to two parts and each part is a problem we solved before...
    public int rotatedDigits(int n) {
        int count = 0;
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int num = i;
            if (num < 10) {
                dp[i] = 1;
                for (int k = 0; k < nonrotates.length; k++) {
                    if (num == nonrotates[k]) {
                        dp[i] = 0;
                        break;
                    }
                }
                for (int k = 0; k < goodchars.length; k++) {
                    if (num == goodchars[k]) {
                        dp[i] = 2;
                        //      System.out.println(num);
                        count++;
                    }
                }
            } else {
                int last = dp[i % 10];
                int prev = dp[i / 10];
                if (last == 0 || prev == 0) {
                    dp[i] = 0;
                } else if (last == 1 && prev == 1) {
                    dp[i] = 1;
                } else if (last == 2 || prev == 2) {
                    dp[i] = 2;
                    //       System.out.println(num);
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new RotatedDigits().rotatedDigits(100));
    }
}

class RotatedDigitsBruteForce {
    int[] goodchars = {2, 5, 6, 9};
    int[] nonrotates = {3, 4, 7};

    public int rotatedDigits(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            int num = i;
            boolean good = false;
            boolean bad = false;
            while (num != 0) {
                int ld = num % 10;
                for (int k = 0; k < nonrotates.length; k++) {
                    if (ld == nonrotates[k]) {
                        bad = true;
                        break;
                    }
                }
                if (!bad) {
                    for (int k = 0; k < goodchars.length; k++) {
                        if (ld == goodchars[k]) {
                            good = true;
                        }
                    }
                }
                num /= 10;
            }
            if (!bad && good) {
                count++;
            }
        }
        return count;
    }
}
