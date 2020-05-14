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
    public int[] distributeCandies(int c, int n) {
        int st = (int) Math.sqrt(2 * c); // find the n*(n+1)<=2c. in this way st >=n <=n+1
        if (st * (st + 1) > 2 * c) { // in case st == n+1 we minus here
            st--;
        }
        int t = (st / n);
        int j = (st) % n; // fully done at st so at st+1 we will do t-1 turns. but st+1 is not the index so we -1

        int[] r = new int[n];
        int rem = c - st * (st + 1) / 2;
        for (int i = 0; i < n; i++) {
            if (i < j) {
                r[i] = getCandy(n, t, i);

            } else {
                r[i] = getCandy(n, t - 1, i);
                if (i == j) {
                    r[i] += rem;
                }
            }

        }
        return r;
    }

    private int getCandy(int n, int t, int i) {
        //for i==0 1, 1,1 ... t+1 1s
        // 0n, 1n 2n...t*n, so t+1 ns
        int r = (t + 1) * (i + 1) + (t + 1) * t * n / 2;
        return r;
    }

    public static void main(String[] args) {

        System.out.println(Arrays.toString(new DistributeCoinsToPeople().distributeCandies(100, 5)));

        System.out.println(Arrays.toString(new DistributeCoinsToPeople().distributeCandies(10, 3)));
        System.out.println(Arrays.toString(new DistributeCoinsToPeople().distributeCandies(7, 3)));
        System.out.println(Arrays.toString(new DistributeCoinsToPeople().distributeCandies(7, 4)));

    }
}

class DistributeCandiesSimulation {
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
