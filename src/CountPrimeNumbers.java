public class CountPrimeNumbers {
    public boolean completePrime(int num) {
        String str = String.valueOf(num);
        int sn = str.length();
        int pref = 0;
        for (int i = 0; i < sn; ++i) {
            int cind = str.charAt(i) - '0';
            pref = pref * 10 + cind;
            //   System.out.println(pref);
            if (!isprime(pref)) {
                return false;
            }
        }
        for (int i = sn - 1; i >= 0; --i) {
            int suff = Integer.valueOf(str.substring(i));
            //    System.out.println(suff);
            if (!isprime(suff)) {
                return false;
            }
        }
        return true;
    }

    private boolean isprime(int num) {
        if (num == 1) {
            return false;
        }
        for (int i = 2; i * i <= num; ++i) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
