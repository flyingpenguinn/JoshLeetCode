public class NextGreaterElementIII {
    public int nextGreaterElement(int num) {
        String str = String.valueOf(num);
        int n = str.length();
        int maxi = -1;
        // maxi  will be the last i that is < i+1
        for (int i = n - 1; i >= 1; i--) {
            int cur = str.charAt(i) - '0';
            int prev = str.charAt(i - 1) - '0';
            if (cur > prev) {
                maxi = i - 1;
                break;
            }
        }
        if (maxi == -1) {
            return -1;
        }

        int cur = str.charAt(maxi) - '0';
        int minmaxj = maxi + 1;
        int minmax = str.charAt(minmaxj) - '0';
        for (int j = minmaxj + 1; j < n; j++) {
            int nj = str.charAt(j) - '0';
            if (nj <= minmax && nj > cur) {
                // if there are js with the  same value we want the right most becaues i+1 to n-1 are reverse sorted
                minmax = nj;
                minmaxj = j;
            }
        }
        long r = Long.valueOf(swapAndReverse(str, maxi, minmaxj));
        if (r > Integer.MAX_VALUE) {
            return -1;
        } else {
            return (int) r;
        }
    }

    // reverse from i+1 say 26543 we swap to 36542 then reverse 6542 to be 2456
    private String swapAndReverse(String str, int i, int j) {
        char[] sb = str.toCharArray();
        swap(i, j, sb);
        int k = i + 1;
        int l = sb.length - 1;
        while (k < l) {
            swap(k++, l--, sb);
        }
        return new String(sb);
    }

    private void swap(int i, int j, char[] sb) {
        char tmp = sb[i];
        sb[i] = sb[j];
        sb[j] = tmp;
    }

    public static void main(String[] args) {
        System.out.println(new NextGreaterElementIII().nextGreaterElement(12222333));

    }
}
