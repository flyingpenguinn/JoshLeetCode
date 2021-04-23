/*
LC#906
Let's say a positive integer is a superpalindrome if it is a palindrome, and it is also the square of a palindrome.

Now, given two positive integers L and R (represented as strings), return the number of superpalindromes in the inclusive range [L, R].



Example 1:

Input: L = "4", R = "1000"
Output: 4
Explanation: 4, 9, 121, and 484 are superpalindromes.
Note that 676 is not a superpalindrome: 26 * 26 = 676, but 26 is not a palindrome.


Note:

1 <= len(L) <= 18
1 <= len(R) <= 18
L and R are strings representing integers in the range [1, 10^18).
int(L) <= int(R)
 */
public class SuperPalindromes {

    public int superpalindromesInRange(String left, String right) {
        long lr = Long.valueOf(right);
        long ll = Long.valueOf(left);
        long sqrt = (int) Math.sqrt(lr)+1;
        long half = half(sqrt);
        int res = 0;
        for(int i = 1; i<=half; i++){
            String h1 = String.valueOf(i);
            String h2 = reverse(h1);
            String palin1 = h1+h2;
            if(good(palin1, ll, lr)){
                res++;
            }
            String palin2 = h1+h2.substring(1);
            if(good(palin2, ll, lr)){

                res++;
            }
        }
        return res;
    }

    private boolean good(String str, long left, long right){
        long num = Long.valueOf(str);
        long sq = num*num;
        boolean rt= ispalin(sq) && sq>=left && sq<=right;
        return rt;
    }

    private boolean ispalin(long num){

        String str = String.valueOf(num);
        int i = 0;
        int j = str.length()-1;
        while(i<j){
            if(str.charAt(i++) != str.charAt(j--)){
                return false;
            }
        }
        return true;
    }

    private long half(long num){
        String snum = String.valueOf(num);
        return Long.valueOf(snum.substring(0, (snum.length())/2+1));
    }

    private String reverse(String s){
        return new StringBuilder(s).reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new SuperPalindromes().superpalindromesInRange("1", "999999999999999999"));
    }
}

class SuperPalindromeRecursion {

    // enumerate our choices each time for the middle one and the two sides. note all operations are int/long
    public int superpalindromesInRange(String l, String r) {
        solve(Long.valueOf(l), Long.valueOf(r));
        return res;
    }

    private int res = 0;

    private void solve(long l, long r) {
        int limit = String.valueOf((int) Math.sqrt(r)).length() / 2 + 1;
        //  System.out.println(limit);
        dfs(l, r, limit, 0, 0, 0, 1);
    }

    // -1 means we stuff an empty in the middle
    private int[] choices = {-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    private void dfs(long l, long r, int limit, int i, long p1, long p2, long base) {
        if (i >= limit) {
            return;
        }
        for (int c : choices) {
            long cur = c == -1 ? p1 * base + p2 : (p1 * 10 + c) * base + p2;
            // System.out.println(cur+" "+p1+" "+p2+" "+base);
            long csq = cur * cur;
            if (csq >= l && csq <= r && isPalin(csq)) {
                //    System.out.println(csq);
                res++;
            }
        }

        int start = i == 0 ? 1 : 0;
        for (int j = start; j <= 9; j++) {
            dfs(l, r, limit, i + 1, p1 * 10 + j, j * base + p2, base * 10);
        }

    }

    private boolean isPalin(long n) {
        // quick way without doing string!
        long rem = 0;
        while (n > rem) {
            if (n / 10 == rem) {
                // 13 vs 1
                return true;
            }
            long d = n % 10;
            rem = rem * 10 + d;
            n = n / 10;
        }
        return n == rem;
    }
}
