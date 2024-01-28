public class NumberOfChangingKeys {
    public int countKeyChanges(String s) {
        char[] c = s.toLowerCase().toCharArray();
        int last = c[0] - 'a';
        int res = 0;
        for (int i = 1; i < s.length(); ++i) {
            int cind = c[i] - 'a';
            //System.out.println(cind + " "+last);
            if (cind != last) {
                ++res;
            }
            last = cind;
        }
        return res;
    }
}
