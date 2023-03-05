import java.util.Arrays;

public class SplitWithMinSum {
    public int splitNum(int num) {
        String sn = String.valueOf(num);
        char[] ca = sn.toCharArray();
        Arrays.sort(ca);
        int num1 = 0;
        int num2 = 0;
        for (int i = 0; i < ca.length; ++i) {
            int cd = ca[i] - '0';
            if (i % 2 == 0) {
                num1 = num1 * 10 + cd;
            } else {
                num2 = num2 * 10 + cd;
            }
        }
        return num1 + num2;
    }
}
