public class ValidPalindromeII {
    public boolean validPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        int hacked = 0; // ==0, never hacked. ===1, hacked once, may be able to go back and hack the other way round. ==2 hacked the two choices already
        int gobacki = -1;
        int gobackj = -1;
        while (i < j) {
            char ci = s.charAt(i);
            char cj = s.charAt(j);
            if (ci != cj) {
                if (hacked == 1) {
                    if (gobacki != -1) {
                        hacked = 2;
                        i = gobacki;
                        j = gobackj;
                        continue;
                    } else {
                        return false;
                    }
                } else if (hacked == 2) {
                    return false;
                } else if (s.charAt(i + 1) == cj && s.charAt(j - 1) != ci) {
                    hacked = 1;
                    i++;
                } else if (s.charAt(j - 1) == ci && s.charAt(i + 1) != cj) {
                    hacked = 1;
                    j--;
                } else if (s.charAt(j - 1) == ci && s.charAt(i + 1) == cj) {
                    hacked = 1;
                    if (gobacki == -1) {
                        // always i+1 first, then j-1 later. two choices here
                        gobacki = i;
                        gobackj = j - 1;
                        i++;
                    } else {
                        j--;
                    }
                } else {
                    return false;
                }
            } else {
                i++;
                j--;
            }

        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new ValidPalindromeII().validPalindrome("cupuuuupucu"));
        ;
        ;
    }
}
