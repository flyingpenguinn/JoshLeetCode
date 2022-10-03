public class BitwiseXorOfAllDigits {
    public int xorAllNums(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int xor1 = 0;
        for (int i = 0; i < n1; ++i) {
            xor1 ^= nums1[i];
        }
        int n2 = nums2.length;
        int xor2 = 0;
        for (int i = 0; i < n2; ++i) {
            xor2 ^= nums2[i];
        }
        if (n1 % 2 == 1 && n2 % 2 == 0) {
            return xor2;
        }
        if (n1 % 2 == 1 && n2 % 2 == 1) {
            return xor1 ^ xor2;
        }
        if (n1 % 2 == 0 && n2 % 2 == 0) {
            return 0;
        } else {
            return xor1;
        }
    }
}
