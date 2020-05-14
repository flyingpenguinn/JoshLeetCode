import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
LC#751
Given a start IP address ip and a number of ips we need to cover n, return a representation of the range as a list (of smallest possible length) of CIDR blocks.

A CIDR block is a string consisting of an IP, followed by a slash, and then the prefix length. For example: "123.45.67.89/20". That prefix length "20" represents the number of common prefix bits in the specified range.

Example 1:
Input: ip = "255.0.0.7", n = 10
Output: ["255.0.0.7/32","255.0.0.8/29","255.0.0.16/32"]
Explanation:
The initial ip address, when converted to binary, looks like this (spaces added for clarity):
255.0.0.7 -> 11111111 00000000 00000000 00000111
The address "255.0.0.7/32" specifies all addresses with a common prefix of 32 bits to the given address,
ie. just this one address.

The address "255.0.0.8/29" specifies all addresses with a common prefix of 29 bits to the given address:
255.0.0.8 -> 11111111 00000000 00000000 00001000
Addresses with common prefix of 29 bits are:
11111111 00000000 00000000 00001000
11111111 00000000 00000000 00001001
11111111 00000000 00000000 00001010
11111111 00000000 00000000 00001011
11111111 00000000 00000000 00001100
11111111 00000000 00000000 00001101
11111111 00000000 00000000 00001110
11111111 00000000 00000000 00001111

The address "255.0.0.16/32" specifies all addresses with a common prefix of 32 bits to the given address,
ie. just 11111111 00000000 00000000 00010000.

In total, the answer specifies the range of 10 ips starting with the address 255.0.0.7 .

There were other representations, such as:
["255.0.0.7/32","255.0.0.8/30", "255.0.0.12/30", "255.0.0.16/32"],
but our answer was the shortest possible.

Also note that a representation beginning with say, "255.0.0.7/30" would be incorrect,
because it includes addresses like 255.0.0.4 = 11111111 00000000 00000000 00000100
that are outside the specified range.
Note:
ip will be a valid IPv4 address.
Every implied address ip + x (for x < n) will be a valid IPv4 address.
n will be an integer in the range [1, 1000].
 */
public class IpToCidr {

    public List<String> ipToCIDR(String ip, int n) {
        String binary = tobinary(ip);
        List<String> r = doi(binary, n);
        Collections.reverse(r);
        return r;
    }

    private List<String> doi(String binary, int n) {
        //  System.out.println(binary+" "+n);
        if (n == 0) {
            return new ArrayList<>();
        }
        int min = 32;
        for (int i = 31; i >= 0; i--) {
            // we freely change digits after i
            int allow = (1 << (31 - i));
            if (n < allow) {
                min = i + 1; // we overshot, so i+1
                break;
            }
            // or if it's a 1 we will have to change after it. everything after i is 0
            if (binary.charAt(i) == '1') {
                min = i;
                break;
            }
        }
        n -= (1 << (31 - min));
        String cur = tocidr(binary, min + 1);
        // min is index
        // exhausted at index min. either it's 0 so we shift to 1, or it's already 1 so we add 1
        String next = addone(binary, min);
        List<String> r = doi(next, n);
        r.add(cur);
        return r;
    }

    private String tocidr(String binary, int block) {
        int n = 0;
        StringBuilder sb = new StringBuilder();
        while (n < 32) {
            long intv = tolong(binary, n, n + 7);
            sb.append(intv);
            sb.append(".");
            n += 8;
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("/");
        sb.append(block);
        return sb.toString();
    }

    private String tobinary(String ip) {
        String[] ips = ip.split("\\.");
        StringBuilder sb = new StringBuilder();
        for (String i : ips) {
            String str = Integer.toBinaryString(Integer.valueOf(i));
            while (str.length() < 8) {
                str = "0" + str;
            }
            sb.append(str);
        }
        return sb.toString();
    }

    private long tolong(String s, int start, int end) {
        long r = 0;
        for (int i = end; i >= start; i--) {
            if (s.charAt(i) == '1') {
                r += (1 << end - i);
            }
        }
        return r;
    }

    private String addone(String ip, int i) {
        StringBuilder sb = new StringBuilder(ip);
        for (int j = i; j >= 0; j--) {
            if (sb.charAt(j) == '1') {
                sb.setCharAt(j, '0');
            } else {
                sb.setCharAt(j, '1');
                break;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new IpToCidr().ipToCIDR("255.0.0.7", 10));
    }
}
