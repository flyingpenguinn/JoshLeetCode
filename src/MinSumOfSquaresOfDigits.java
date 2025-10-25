public class MinSumOfSquaresOfDigits {
    public String maxSumOfSquares(int num, int sum) {
        if (sum > num * 9) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        while (sum > 0) {
            int dig = Math.min(sum, 9);
            sb.append(dig);
            sum -= dig;
        }
        while (sb.length() < num) {
            sb.append(0);
        }
        return sb.toString();
    }
}
