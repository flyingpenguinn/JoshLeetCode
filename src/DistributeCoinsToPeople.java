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
    public int[] distributeCandies(int n, int k) {
        int rounds = (int)(Math.sqrt(1.0 + 8.0 * n) - 1) / 2;
        // these are full rounds where everyone will get their candies in i+1, i+1+n... manner
        int rem = n - (rounds * (rounds + 1) / 2); // candy the last person will get
        int cutoff = rounds % k; // if mod = 3, it's i=3 that we start to lose the last round of candy
        int times = rounds / k; // this is the number of full rounds where all k people are covered
        int[] res = new int[k];
        for (int i = 0; i < k; i++)
        {
            int curtime = times + (i<cutoff? 1: 0);
            res[i] = sum(i + 1, curtime, k);
        }
        res[cutoff] += rem;
        return res;
    }

    // arithmetic sequence, given a1, n, and d
    private int sum(int a1, int n, int d)    {
        int an = a1 + (n - 1) * d;
        return (a1 + an) * n / 2;
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
