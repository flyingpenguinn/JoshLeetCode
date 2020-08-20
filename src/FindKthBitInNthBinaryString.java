public class FindKthBitInNthBinaryString {
    public char findKthBit(int n, int k) {
        if (n == 1) {
            return '0';
        }
        int len = (1 << n) - 1;
        if (k == (len + 1) / 2) {
            return '1';
        } else if (k > (len + 1) / 2) {
            int th = k - (len + 1) / 2; // 011 1 001   if we are in 001, we conver tto finding bit in 011
            int nm1len = (1 << (n - 1)) - 1;
            char raw = findKthBit(n - 1, nm1len - th + 1); // k starts from 1
            return raw == '0' ? '1' : '0';
        } else {
            return findKthBit(n - 1, k);
        }

    }
}
