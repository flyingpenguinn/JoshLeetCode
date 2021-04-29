public class NextPalindromeUsingSameDigits {

    public String nextPalindrome(String s) {
        int n = s.length();
        int[] sd = new int[n / 2];
        for (int i = 0; i < n / 2; i++) {
            sd[i] = s.charAt(i) - '0';
        }
        boolean found = nextPermutation(sd);
        if(!found){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sd.length; i++) {
            sb.append(sd[i]);
        }
        String half = sb.toString();
        String rev = new StringBuilder(half).reverse().toString();
        if (n % 2 == 1) {
            half += s.charAt((n + 1) / 2);
        }
        return half + rev;
    }

    public boolean nextPermutation(int[] a) {
        // validate input, null-> throw or return
        int n = a.length;
        int swap1 = -1; //index
        for (int i = n - 1; i >= 1; i--) {
            if (a[i] > a[i - 1]) {
                swap1 = i - 1;
                break;
            }
        }
        if (swap1 == -1) {
            return false;
        }
        int swap2 = -1; //index
        for (int i = n - 1; i > swap1; i--) {
            if (a[i] > a[swap1]) {
                swap2 = i;
                break;
            }
        }
        swap(a, swap1, swap2);
        reverse(a, swap1 + 1, n - 1);
        return true;
    }

    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private void reverse(int[] a, int i, int j) {
        while (i < j) {
            swap(a, i++, j--);
        }
    }

}
