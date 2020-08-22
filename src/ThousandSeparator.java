public class ThousandSeparator {
    public String thousandSeparator(int n) {
        if (n == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (n > 0) {
            if (count > 0 && count % 3 == 0) {
                // avoid . when count==0
                sb.append(".");
            }
            sb.append(n % 10);
            n /= 10;
            count++;
        }
        return sb.reverse().toString();
    }
}
