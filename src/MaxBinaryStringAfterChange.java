public class MaxBinaryStringAfterChange {
    // first go through all the 111s, meet our first 0
    // then whenever we add a 0 we are adding 1 at the front. the newly added 0 helps the earlier 0. note 01110-> 00111->10111. we can effectively "push" 1s to the end and give way to an extra 1 at the front, hence this is correct
    // whenever we add 1 we are adding 1 at the end becaus we can't do better than 1011->10111
    public String maximumBinaryString(String s) {
        int n = s.length();
        int ones1 = 0;
        int i = 0;
        for (; i < n && s.charAt(i) == '1'; i++) {
            ones1++;
        }
        if (i == n) {
            return s; // all 1s
        }
        int ones2 = 0;
        i++;
        for (; i < n; i++) {
            if (s.charAt(i) == '0') {
                ones1++;
            } else {
                ones2++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < ones1; j++) {
            sb.append(1);
        }
        sb.append(0);
        for (int j = 0; j < ones2; j++) {
            sb.append(1);
        }
        return sb.toString();
    }
}
