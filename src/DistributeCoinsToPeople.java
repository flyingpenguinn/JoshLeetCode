import java.util.Arrays;

/*
LC#1103
We distribute some number of candies, to a row of n = num_people people in the following way:

We then give 1 candy to the first person, 2 candies to the second person, and so on until we give n candies to the last person.

Then, we go back to the start of the row, giving n + 1 candies to the first person, n + 2 candies to the second person, and so on until we give 2 * n candies to the last person.

This process repeats (with us giving one more candy each time, and moving to the start of the row after we reach the end) until we run out of candies.  The last person will receive all of our remaining candies (not necessarily one more than the previous gift).

Return an array (of length num_people and sum candies) that represents the final distribution of candies.



Example 1:

Input: candies = 7, num_people = 4
Output: [1,2,3,1]
Explanation:
On the first turn, ans[0] += 1, and the array is [1,0,0,0].
On the second turn, ans[1] += 2, and the array is [1,2,0,0].
On the third turn, ans[2] += 3, and the array is [1,2,3,0].
On the fourth turn, ans[3] += 1 (because there is only one candy left), and the final array is [1,2,3,1].
Example 2:

Input: candies = 10, num_people = 3
Output: [5,2,3]
Explanation:
On the first turn, ans[0] += 1, and the array is [1,0,0].
On the second turn, ans[1] += 2, and the array is [1,2,0].
On the third turn, ans[2] += 3, and the array is [1,2,3].
On the fourth turn, ans[0] += 4, and the final array is [5,2,3].


Constraints:

1 <= candies <= 10^9
1 <= num_people <= 1000
 */
public class DistributeCoinsToPeople {
    // O(n)
    public int[] distributeCandies(int c, int n) {
        int allrounds = (int) ((Math.sqrt(1.0 + 8.0 * c) - 1) / 2.0);
        int round = allrounds / n; // how many rounds where everyone gets a candy
        int mod = allrounds % n; // 0..mod-1 will get an extra round
        int rem = c - allrounds * (allrounds + 1) / 2; // mod will get the remaining
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int cr = i < mod ? round + 1 : round;
            if (cr > 0) {
                // for each item it's arithmatic sequence with a1 = i+1 and d = n
                res[i] = cr * (i + 1) + cr * (cr - 1) * n / 2; // n*a1+n(n-1)*d/2.
            }
        }
        res[mod] += rem;
        return res;
    }

    public static void main(String[] args) {

        System.out.println(Arrays.toString(new DistributeCoinsToPeople().distributeCandies(100, 5)));

        System.out.println(Arrays.toString(new DistributeCoinsToPeople().distributeCandies(10, 3)));
        System.out.println(Arrays.toString(new DistributeCoinsToPeople().distributeCandies(7, 3)));
        System.out.println(Arrays.toString(new DistributeCoinsToPeople().distributeCandies(7, 4)));

    }
}

class DistributeCandiesSimulation {
    // O(sqrt(c))
    public int[] distributeCandies(int c, int n) {
        int[] r = new int[n];
        int i = 0;
        int give = 1;
        while (c > 0) {
            r[i] += Math.min(give, c);
            i = (i + 1) % n;
            c -= give;
            give++;
        }
        return r;
    }
}
