/*
LC#468
Write a function to check whether an input string is a valid IPv4 address or IPv6 address or neither.

IPv4 addresses are canonically represented in dot-decimal notation, which consists of four decimal numbers, each ranging from 0 to 255, separated by dots ("."), e.g.,172.16.254.1;

Besides, leading zeros in the IPv4 is invalid. For example, the address 172.16.254.01 is invalid.

IPv6 addresses are represented as eight groups of four hexadecimal digits, each group representing 16 bits. The groups are separated by colons (":"). For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a valid one. Also, we could omit some leading zeros among four hexadecimal digits and some low-case characters in the address to upper-case ones, so 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6 address(Omit leading zeros and using upper cases).

However, we don't replace a consecutive group of zero value with a single empty group using two consecutive colons (::) to pursue simplicity. For example, 2001:0db8:85a3::8A2E:0370:7334 is an invalid IPv6 address.

Besides, extra leading zeros in the IPv6 is also invalid. For example, the address 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is invalid.

Note: You may assume there is no extra space or special characters in the input string.

Example 1:
Input: "172.16.254.1"

Output: "IPv4"

Explanation: This is a valid IPv4 address, return "IPv4".
Example 2:
Input: "2001:0db8:85a3:0:0:8A2E:0370:7334"

Output: "IPv6"

Explanation: This is a valid IPv6 address, return "IPv6".
Example 3:
Input: "256.256.256.256"

Output: "Neither"

Explanation: This is neither a IPv4 address nor a IPv6 address.
 */
public class ValidateIPAddress {
    // lookouts
    // 1. nee to use \\. and -1 to split
    // 2. make sure after split, all strings are non empty, and of correct length
    // 3. deal with leading zeros and characters
    public String validIPAddress(String s) {
        String[] sp = s.split("\\.", -1);
        if (sp.length == 4) {
            return validv4(sp) ? "IPv4" : "Neither";
        }
        String[] sp2 = s.split(":", -1);
        if (sp2.length == 8) {
            return validv6(sp2) ? "IPv6" : "Neither";
        }
        return "Neither";
    }

    private boolean validv6(String[] ss) {
        for (String s : ss) {
            if (s.length() > 4 || s.isEmpty()) {
                // leading zeros
                return false;
            }
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (!Character.isDigit(c) && !(Character.isLowerCase(c) && c <= 'f') && !(Character.isUpperCase(c) && c <= 'F')) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean validv4(String[] ss) {
        for (String s : ss) {
            if (s.length() > 3 || s.isEmpty()) {
                return false;
            }
            if (s.startsWith("0") && s.length() > 1) {
                // leading zeros
                return false;
            }
            int cur = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (Character.isDigit(c)) {
                    cur = cur * 10 + (c - '0');
                } else {
                    return false;
                }
            }
            if (cur > 255) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new ValidateIPAddress().validIPAddress("20EE:FGb8:85a3:0:0:8A2E:0370:7334"));
    }
}
