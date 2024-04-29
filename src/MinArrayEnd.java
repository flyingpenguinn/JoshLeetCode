import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinArrayEnd {
    // all the 0 parts fill with n: think of those 1 positions as not changable
    /* can actually be as simple as this:
        long a = x;
        while (--n > 0)
            a = (a + 1) | x;
        return a;

     */
    private int base = 64;

    public long minEnd(int n, int x) {
        int zeros = 0;
        String sx = Integer.toBinaryString(x);
        while (sx.length() < base) {
            sx = "0" + sx;
        }
        char[] rstr = sx.toCharArray();
        List<Integer> zp = new ArrayList<>();
        for (int i = 0; i < base; ++i) {
            if (sx.charAt(i) == '0') {
                zp.add(base - 1 - i);
            }
        }
        // System.out.println(zp);
        Collections.sort(zp);
        String bs = Integer.toBinaryString(n - 1);
        int bn = bs.length();
        int zi = 0;
        for (int i = bn - 1; i >= 0; --i) {
            char bc = bs.charAt(i);
            int zpos = base - 1 - zp.get(zi++);
            rstr[zpos] = bc;
        }
        String res = new String(rstr);
        long lres = Long.parseLong(res, 2);
        return lres;
    }
}
