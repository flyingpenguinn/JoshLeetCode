/*
LC#458
There are 1000 buckets, one and only one of them is poisonous, while the rest are filled with water. They all look identical. If a pig drinks the poison it will die within 15 minutes. What is the minimum amount of pigs you need to figure out which bucket is poisonous within one hour?

Answer this question, and write an algorithm for the general case.



General case:

If there are n buckets and a pig drinking poison will die within m minutes, how many pigs (x) you need to figure out the poisonous bucket within p minutes? There is exactly one bucket with poison.



Note:

A pig can be allowed to drink simultaneously on as many buckets as one would like, and the feeding takes no time.
After a pig has instantly finished drinking buckets, there has to be a cool down time of m minutes. During this time, only observation is allowed and no feedings at all.
Any given bucket can be sampled an infinite number of times (by an unlimited number of pigs).
 */
public class PoorPigs {

    /*
    if we have only one chance, then we convert a to binary, then make it an n bit binary number
    1st pig drinks those binary == 0 at the 1st digit
    2nd pig drinks those binary == 0 at the 2nd digit...
    ...
    nth pig drinks those binary == 0 at the nth digit
    if any of them die we know that digit is a 0. otherwise it's a 1.
    we will then know the number because we know every digit of the binary form

    so, if we have more than one try = floor(c/b), we use a try+1 base rather than base of 2
    in the 1st round, pigs drink binary == 0 at their digits
    in the 2nd round, pigs drink binary == 1 at their digits
    ...
    in the try th round, pigs drink try-1 at their digits
    so if any of them die, we know which number it is on that digit
    if none of them die, we know the digit == try
   */
    public int poorPigs(int a, int b, int c) {
        int base = c / b + 1;
        int cur = 1;
        int res = 0;
        // cur-1: res digits, using base, we can get to this number
        // a-1: we are numbering buckets from 0 to a-1
        while (cur - 1 < a - 1) {
            cur *= base;
            res++;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new PoorPigs().poorPigs(1000, 15, 60));
    }
}
