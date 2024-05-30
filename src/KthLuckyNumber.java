public class KthLuckyNumber {
    private int n = 30; //  30 digit at most

    public String kthLuckyNumber(int k) {
        char[] s = new char[n];
        int i = 0;
        while ((1 << (n - i)) - 2 >= k) {
            ++i;  // current digit can be anything. 2+4+8... = 2^n -2
        }
        k -= (1 << (n - i)) - 2;
        for (int j = i; j < n; ++j) {
            long n7 = 1 << (n - 1 - j); // current digit fixed as 4
            if (n7 >= k) {
                s[j] = '4'; // cannot - anything yet as 4 is not guranteed to be bigger than anyone
            } else {
                s[j] = '7';
                k -= n7;
            }
        }
        StringBuilder res = new StringBuilder();
        while (i < n) {
            res.append(s[i++]);
        }
        return res.toString();
    }
}
