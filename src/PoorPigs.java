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

    //In general, we can solve up to (⌊minutesToTest / minutesToDie⌋ + 1)pigs buckets this way,
    // so just find the smallest sufficient number of pigs for example like this:
    // https://leetcode.com/problems/poor-pigs/discuss/94266/Another-explanation-and-solution
    public int poorPigs(int n, int m, int t) {
        if (n == 1) {
            return 0;
        }
        int x = 1;
        int times = t / m;
        while (true) {
            if ((long) Math.pow(times + 1, x) >= n) {
                return x;
            }
            x++;
        }
    }

    public static void main(String[] args) {
        System.out.println(new PoorPigs().poorPigs(1000, 15, 60));
    }
}
