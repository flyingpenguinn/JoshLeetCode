public class ConvertToHexadecimal {
    public String toHex(int n) {
        if (n == 0) {
            return "0";
        } else if (n > 0) {
            return hex(n);
        } else {
            // for neg value it's 1<<31+n then make sign (the h 1 instead of 0
            long base = (long) (1L << 32);
            long comp = (long) (base + n);
            return hex(comp);
        }
    }

    String hex(long n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int mod = (int) (n % 16);
            sb.append(hexchar(mod));
            n /= 16;
        }
        return sb.reverse().toString();
    }

    char hexchar(int n) {
        return n < 10 ? (char) ('0' + n) : (char) ('a' + n - 10);
    }

    public static void main(String[] args) {
        System.out.println(new ConvertToHexadecimal().toHex(-1));
    }

}

// &15 takes the last 4 digits of the int, just like & 1 takes the last digit
class ConvertToHexadecimalFromBinary {

    char[] map = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public String toHex(int num) {
        if (num == 0) return "0";
        String result = "";
        while (num != 0) {
            result = map[(num & 15)] + result;
            num = (num >>> 4);  // must be unsigned shift
        }
        return result;
    }
}
