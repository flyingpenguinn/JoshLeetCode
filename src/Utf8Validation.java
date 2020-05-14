import base.ArrayUtils;

/*
LC#393
A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:

For 1-byte character, the first bit is a 0, followed by its unicode code.
For n-bytes character, the first n-bits are all one's, the n+1 bit is 0, followed by n-1 bytes with most significant 2 bits being 10.
This is how the UTF-8 encoding would work:

   Char. number range  |        UTF-8 octet sequence
      (hexadecimal)    |              (binary)
   --------------------+---------------------------------------------
   0000 0000-0000 007F | 0xxxxxxx
   0000 0080-0000 07FF | 110xxxxx 10xxxxxx
   0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
   0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
Given an array of integers representing the data, return whether it is a valid utf-8 encoding.

Note:
The input is an array of integers. Only the least significant 8 bits of each integer is used to store the data. This means each integer represents only 1 byte of data.

Example 1:

data = [197, 130, 1], which represents the octet sequence: 11000101 10000010 00000001.

Return true.
It is a valid utf-8 encoding for a 2-bytes character followed by a 1-byte character.
Example 2:

data = [235, 140, 4], which represented the octet sequence: 11101011 10001100 00000100.

Return false.
The first 3 bits are all one's and the 4th bit is 0 means it is a 3-bytes character.
The next byte is a continuation byte which starts with 10 and that's correct.
But the second continuation byte does not start with 10, so it is invalid.
 */
public class Utf8Validation {
    public boolean validUtf8(int[] a) {
        int n = a.length;
        int k = 0;
        while (k < n) {
            int b1 = a[k];
            int c = 0;
            // from 7: least 8 digits!
            for (int i = 7; i >= 0; i--) {
                if (((b1 >> i) & 1) == 0) {
                    break;
                } else {
                    c++;
                    if (c > 4) {
                        return false;
                    }
                }
            }
            if (c == 1) {
                // can't be 10
                return false;
            }
            if (c == 0) {
                // 0 represents 1
                c = 1;
            }
            if (k + c - 1 >= n) {
                // can't be over this array
                return false;
            }
            for (int i = k + 1; i <= k + c - 1; i++) {
                // later ones must start with 10
                if (((a[i] >> 7 & 1) != 1) || ((a[i] >> 6) & 1) != 0) {
                    return false;
                }
            }
            k += c;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Utf8Validation().validUtf8(ArrayUtils.read1d("197, 130, 1")));
        System.out.println(new Utf8Validation().validUtf8(ArrayUtils.read1d("234, 140, 4")));
    }
}
