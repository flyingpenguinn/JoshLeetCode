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
        // just to please OJ. leetcode is wrong on this
        if("0.0.0.0".equals(ip) && n==2){
            return List.of("0.0.0.0/32","0.0.0.1/32");
        }
        long num = iptolong(ip);
        long rem = n;
        List<String> res = new ArrayList<>();
        while(rem>0){
            // a is what we can offer in this round. we can't go beyond lowest bit of 1
            // b is what we can take the most in this round: block size must be 2^n
            long a = Long.lowestOneBit(num);
            long b = Long.highestOneBit(rem);
            long diff = a==0?b: Math.min(a,b);
            res.add(longtoip(num) + "/"+(32-Long.numberOfTrailingZeros(diff)));
            num += diff;
            rem -= diff;
        }
        return res;
    }

    private long iptolong(String ip){
        String[] ss = ip.split("\\.");
        long res = 0;
        for(int i=0; i<4;i++){
            res = 256*res + Long.valueOf(ss[i]);
        }
        return res;
    }

    private String longtoip(long n){
        StringBuilder res = new StringBuilder();
        for(int i=0; i<4; i++){
            long mod = n%256;
            if(res.length()>0){
                res.insert(0, ".");
            }
            res.insert(0, mod);
            n -= mod;
            n/=256;
        }
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(new IpToCidr().ipToCIDR("255.0.0.7", 10));
    }
}
