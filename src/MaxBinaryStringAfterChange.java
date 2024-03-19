public class MaxBinaryStringAfterChange {
    // leave all the starting 111 there
    // then for 0xxxx move all the 1s to the right ,so that it's 00001111
    // then for the k zeros convert first k-1 zeros to 1 so it becomes 11101111
    public String maximumBinaryString(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        int i = 0;
        while (i < n && s[i] == '1') {
            ++i;
        }
        if (i == n) {
            return str;
        }
        // there is a 0 here
        ++i;
        int badones = 0;
        for (; i < n; ++i) {
            badones += s[i] - '0';
            s[i] = '0';
        }
        i = n - 1;
        while (i >= 0 && badones > 0) {
            s[i] = '1';
            --i;
            --badones;
        }
        --i; // skip this 0
        while (i >= 0 && s[i] == '0') {
            s[i] = '1';
            --i;
        }
        return new String(s);
    }
}
