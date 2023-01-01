public class CountDigitsDivideANumber {
    public int countDigits(int num) {
        int on = num;
        int res = 0;
        while (num > 0) {
            int d = num % 10;
            if (on % d == 0) {
                ++res;
            }
            num /= 10;
        }
        return res;
    }
}
