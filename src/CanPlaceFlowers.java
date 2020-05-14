/*
LC#605
Suppose you have a long flowerbed in which some of the plots are planted and some are not. However, flowers cannot be planted in adjacent plots - they would compete for water and both would die.

Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), and a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.

Example 1:
Input: flowerbed = [1,0,0,0,1], n = 1
Output: True
Example 2:
Input: flowerbed = [1,0,0,0,1], n = 2
Output: False
Note:
The input array won't violate no-adjacent-flowers rule.
The input array size is in the range of [1, 20000].
n is a non-negative integer which won't exceed the input array size.
 */
public class CanPlaceFlowers {
    // note after 1 there is no other 1
    public boolean canPlaceFlowers(int[] a, int k) {
        int n = a.length;
        int i = 0;
        while (i < n && k > 0) {
            if (a[i] == 1) {
                i += 2;
            } else if (a[i] == 0 && (i == n - 1 || a[i + 1] != 1)) {
                k--;
                i += 2;
            } else {
                i++;
            }
        }
        return k == 0;
    }

    public static void main(String[] args) {
        int[] f = {1, 0, 0, 0, 1, 0, 0};
        System.out.println(new CanPlaceFlowers().canPlaceFlowers(f, 2));
    }

}
