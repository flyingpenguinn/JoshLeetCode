public class PrimePalindrome {


    public int primePalindrome(long n) {
        if (n == 1) {
            return 2;
        }
        if (n >= 8 && n <= 11) {
            return 11;
        }
        // we just know 10003:)
        long limit = Math.min(n * n, 10003);
        for (int num = 1; num <= limit; num++) {
            String cur = String.valueOf(num);
            String reverse = new StringBuilder(cur).reverse().toString();
            String palin1 = cur + reverse.substring(1);
            Integer p1 = Integer.valueOf(palin1);
            if (isPrime(p1) && p1 >= n) {
                // we can directly return because we generate numbers only in odd len by their value
                return p1;
            }
            // not doing palin2 because except for 11, even len palindrome numbers are dividable by 11
        }
        return -1;
    }

    public Boolean isPrime(int x) {
        // among 0,1,2,4,6,8... only 2 is prime
        if (x < 2 || x % 2 == 0) {
            return x == 2;
        }
        // just need to check 3,5,7...
        for (int i = 3; i * i <= x; i += 2) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new PrimePalindrome().primePalindrome(9989900));

    }
}
