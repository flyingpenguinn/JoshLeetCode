import java.util.ArrayList;
import java.util.List;

public class ComputeDecimalRepresentation {
    public int[] decimalRepresentation(int n) {
        String sn = String.valueOf(n);
        int len = sn.length();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < len; ++i) {
            int dig = sn.charAt(i) - '0';
            if (dig == 0) {
                continue;
            }
            int rem = len - 1 - i;
            int multi = (int) (Math.pow(10, rem));
            //  System.out.println(multi+" "+dig);
            int cur = dig * multi;
            res.add(cur);
        }
        int[] rr = new int[res.size()];
        for (int i = 0; i < res.size(); ++i) {
            rr[i] = res.get(i);
        }
        return rr;
    }
}
