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
    // if next one is 0 and we can place here we place 1. it's always profitable to place 1 here
    public boolean canPlaceFlowers(int[] a, int t) {
        if (t == 0) {
            return true;
        }
        int res = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 1) {
                continue;
            }
            if ((i + 1 < a.length && a[i + 1] == 1) || (i > 0 && a[i - 1] == 1)) {
                continue;
            }
            a[i] = 1;
            res++;
            if (res >= t) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] f = {1, 0, 0, 0, 1, 0, 0};
        System.out.println(new CanPlaceFlowers().canPlaceFlowers(f, 2));
    }

}
