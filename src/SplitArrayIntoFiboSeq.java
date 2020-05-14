import java.util.ArrayList;
import java.util.List;

public class SplitArrayIntoFiboSeq {

    // two checks: in int, and not starting with 0
    int intmax = Integer.MAX_VALUE;

    public List<Integer> splitIntoFibonacci(String s) {
        long n1 = 0;
        int sn = s.length();
        List<Integer> r = new ArrayList<>();
        for (int i = 0; i < sn; i++) {
            r.clear();
            char c1 = s.charAt(i);
            n1 = n1 * 10 + (c1 - '0');
            //System.out.println("n1="+n1);
            r.add((int) n1);
            if (n1 > intmax) {
                break;
            }
            if (s.charAt(0) == '0' && n1 != 0) {
                break;
            }
            int n2 = 0;
            for (int j = i + 1; j < sn; j++) {
                char c2 = s.charAt(j);
                n2 = n2 * 10 + (c2 - '0');
                //System.out.println("n2="+n2);

                if (n2 > intmax) {
                    break;
                }
                if (n1 + n2 > intmax) {
                    break;
                }
                if (s.charAt(i + 1) == '0' && n2 != 0) {
                    break;
                }
                r.add((int) n2);
                boolean gd = doc(j + 1, s, r, n1, n2);
                if (gd && r.size() >= 3) {
                    return r;
                }
                r.remove(r.size() - 1);
            }
        }
        return new ArrayList<>();
    }

    boolean doc(int j, String s, List<Integer> r, long n1, long n2) {

        if (j == s.length()) {
            return true;
        }
        int oj = j;

        long ns = 0;
        // must make sure it moves!
        while ((j == oj) || (j < s.length() && ns < n1 + n2)) {
            int sc = s.charAt(j) - '0';

            ns = 10 * ns + sc;


            j++;
        }
        //  System.out.println("n1= "+n1+" n2= "+n2+" ns= "+ns);
        if (ns > intmax) {
            return false;
        }
        if (ns > n1 + n2) {
            return false;
        }
        if (ns < n1 + n2) {
            return false;
        }
        if (s.charAt(oj) == '0' && ns != 0) {
            return false;
        }
        r.add((int) ns);

        boolean rt = doc(j, s, r, n2, ns);
        if (rt) {
            return true;
        } else {
            r.remove(r.size() - 1);
            return false;
        }
    }
}
