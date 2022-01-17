public class DivideStringIntoGroupsOfSizeK {
    public String[] divideString(String s, int k, char fill) {

        int n = s.length();
        String[] res = new String[(int) Math.ceil(n * 1.0 / k)];
        int ri = 0;
        int i = 0;
        for (; i + k - 1 < n; i += k) {
            res[ri++] = s.substring(i, i + k);
        }
        if (i < n) {
            StringBuilder sb = new StringBuilder();
            sb.append(s.substring(i, n));
            while (sb.length() < k) {
                sb.append(fill);
            }
            res[ri++] = sb.toString();
        }
        return res;
    }
}
