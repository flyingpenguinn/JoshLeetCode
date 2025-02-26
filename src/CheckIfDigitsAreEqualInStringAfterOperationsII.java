public class CheckIfDigitsAreEqualInStringAfterOperationsII {

    public boolean hasSameDigits(String s) {
        int n = s.length();
        // Precompute powers of 2 and 5 mod 10, up to n
        int[] P2 = new int[n + 1];
        int[] P5 = new int[n + 1];
        P2[0] = 1;
        P5[0] = 1;
        for (int i = 1; i <= n; i++) {
            P2[i] = (P2[i - 1] * 2) % 10;
            P5[i] = (P5[i - 1] * 5) % 10;
        }

        // Convert the string to digit array
        int[] digits = new int[n];
        for (int i = 0; i < n; i++) {
            digits[i] = s.charAt(i) - '0';
        }

        // Compare calc(0, n - 2) vs calc(1, n - 1)
        // If they match mod 10, final 2 digits are the same.
        return calc(0, n - 2, digits, P2, P5) == calc(1, n - 1, digits, P2, P5);
    }

    // Computes the "sum" for s[l..r] which corresponds to
    //   Σ_{m=0..(r-l)} [ binomial(r-l, m) mod 10 ] * s[l + m].
    //   factoring out powers of 2 and 5 carefully.
    private int calc(int l, int r, int[] digits, int[] P2, int[] P5) {
        int length = r - l; // number of steps
        int c = 1;   // partial product mod 10 (after factoring out 2,5)
        int two = 0; // exponent of 2 factored out
        int five = 0;// exponent of 5 factored out
        int sm = 0;  // final sum mod 10
        int j = 0;

        for (int i = l; ; i++, j++) {
            // Add current digit times the current combination factor
            //  plus the 2^two * 5^five all mod 10
            //  => digit * P2[two] * P5[five] * c (mod 10)
            sm = (sm + digits[i] * P2[two] * P5[five] * c) % 10;

            if (i == r) {
                break;
            }

            // Multiply c by (length - j)
            int mul = length - j;
            // factor out 2,5 from mul
            while (mul % 2 == 0) {
                mul /= 2;
                two++;
            }
            while (mul % 5 == 0) {
                mul /= 5;
                five++;
            }
            c = (c * mul) % 10;

            // Divide c by (j + 1) => factor out 2,5, then multiply by inverse mod 10
            int div = j + 1;
            while (div % 2 == 0) {
                div /= 2;
                two--;
            }
            while (div % 5 == 0) {
                div /= 5;
                five--;
            }

            // Now div is coprime with 10. Use extended gcd to invert it mod 10.
            int[] xy = new int[2];
            exgcd(div, 10, xy);
            // xy[0] is the modular inverse of div mod 10 (since gcd(div,10)=1).
            int inv = (xy[0] % 10 + 10) % 10;

            c = (c * inv) % 10;
        }

        return sm;
    }

    // Extended Euclidean algorithm to find x,y such that a*x + b*y = gcd(a,b).
    // Here, gcd(a,10)=1 so x is the inverse of a mod 10.
    private void exgcd(int a, int b, int[] xy) {
        // xy[0] = x, xy[1] = y
        // we want a*x + b*y = gcd(a,b).
        if (b == 0) {
            xy[0] = 1;
            xy[1] = 0;
            return;
        }
        exgcd(b, a % b, xy);
        int tmp = xy[0];
        xy[0] = xy[1];
        xy[1] = tmp - (a / b) * xy[1];
    }


    public static void main(String[] args) {
        CheckIfDigitsAreEqualInStringAfterOperationsII sol = new CheckIfDigitsAreEqualInStringAfterOperationsII();
        System.out.println(sol.hasSameDigits("059223162476909414787217368465718889720070329493800526721646241144650386182915621907231953557681242064182905239381861256480822308801745728716464165805416272563074544781706952551993233233441914762054761669477046604260289688651191958433480070003587023200105113105431268932582314103774297291977036873970402534522915576764583200175755147667814674754512504911569655037494222537756410610151191257150195557437349822009352297672631564482185262565187532385279714260044303857829021469873315780358707063556775718248201658447824815265573818347949428248619384219498719969693539037903024755075964220907437734386614616595870073534174014967983825396"));
        System.out.println(sol.hasSameDigits("321881"));


    }
}
