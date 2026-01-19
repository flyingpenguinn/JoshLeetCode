import java.util.ArrayList;
import java.util.List;

public class GoodIndicesInDigitString {
    public List<Integer> goodIndices(String s) {
        int n = s.length();
        int j = 0;
        int cur = 0;
        int base = 0;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            int dig = s.charAt(i) - '0';
            cur = cur * 10 + dig;
            //    System.out.println("i="+i+" initial cur="+cur);
            while (cur > i) {
                //       System.out.println("i="+i+" working on cur="+cur);
                int head = s.charAt(j) - '0';
                int len = i - j + 1;
                cur -= pow10(len - 1) * head;
                ++j;
            }
            //    System.out.println("i="+i+" finishing cur="+cur);
            if (cur == i && i >= j) {
                //   System.out.println("found good cur"+cur+" i="+i);
                res.add(i);
            }
        }
        return res;
    }

    private int pow10(int n) {
        if (n == 0) {
            return 1;
        }
        int half = pow10(n / 2);
        int res = half * half;
        if (n % 2 == 1) {
            res *= 10;
        }
        return res;
    }
}
