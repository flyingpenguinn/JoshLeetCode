public class HarshardNumber {
    public int sumOfTheDigitsOfHarshadNumber(int x) {
        int sum = 0;
        int oldx = x;
        while (x > 0) {
            sum += x % 10;
            x /= 10;
        }
        if (oldx % sum == 0) {
            return sum;
        } else {
            return -1;
        }
    }
}
