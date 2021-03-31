import java.util.HashMap;
import java.util.Map;

/*
LC#1467
Given 2n balls of k distinct colors. You will be given an integer array balls of size k where balls[i] is the number of balls of color i.

All the balls will be shuffled uniformly at random, then we will distribute the first n balls to the first box and the remaining n balls to the other box (Please read the explanation of the second example carefully).

Please note that the two boxes are considered different. For example, if we have two balls of colors a and b, and two boxes [] and (), then the distribution [a] (b) is considered different than the distribution [b] (a) (Please read the explanation of the first example carefully).

We want to calculate the probability that the two boxes have the same number of distinct balls.



Example 1:

Input: balls = [1,1]
Output: 1.00000
Explanation: Only 2 ways to divide the balls equally:
- A ball of color 1 to box 1 and a ball of color 2 to box 2
- A ball of color 2 to box 1 and a ball of color 1 to box 2
In both ways, the number of distinct colors in each box is equal. The probability is 2/2 = 1
Example 2:

Input: balls = [2,1,1]
Output: 0.66667
Explanation: We have the set of balls [1, 1, 2, 3]
This set of balls will be shuffled randomly and we may have one of the 12 distinct shuffles with equale probability (i.e. 1/12):
[1,1 / 2,3], [1,1 / 3,2], [1,2 / 1,3], [1,2 / 3,1], [1,3 / 1,2], [1,3 / 2,1], [2,1 / 1,3], [2,1 / 3,1], [2,3 / 1,1], [3,1 / 1,2], [3,1 / 2,1], [3,2 / 1,1]
After that we add the first two balls to the first box and the second two balls to the second box.
We can see that 8 of these 12 possible random distributions have the same number of distinct colors of balls in each box.
Probability is 8/12 = 0.66667
Example 3:

Input: balls = [1,2,1,2]
Output: 0.60000
Explanation: The set of balls is [1, 2, 2, 3, 4, 4]. It is hard to display all the 180 possible random shuffles of this set but it is easy to check that 108 of them will have the same number of distinct colors in each box.
Probability = 108 / 180 = 0.6
Example 4:

Input: balls = [3,2,1]
Output: 0.30000
Explanation: The set of balls is [1, 1, 1, 2, 2, 3]. It is hard to display all the 60 possible random shuffles of this set but it is easy to check that 18 of them will have the same number of distinct colors in each box.
Probability = 18 / 60 = 0.3
Example 5:

Input: balls = [6,6,6,6,6,6]
Output: 0.90327


Constraints:

1 <= balls.length <= 8
1 <= balls[i] <= 6
sum(balls) is even.
Answers within 10^-5 of the actual value will be accepted as correct.
 */
public class ProbabilityOfTwoBoxesHavingSameDistinct {
    // not dp, but a backtrack question!
    // the key is to use combination to deduce the number of possible permutations! we don't need to enumerate all the perms
    // enumerate on each balls[i] to see how many balls we should put in this catetory to box1
    // can use double to calc the permutations!
    private double all = 0;
    private double good = 0;

    public double getProbability(int[] a) {
        int n = a.length;
        int sum = 0;
        for (int ai : a) {
            sum += ai;
        }
        int[] set1 = new int[n];
        int[] set2 = new int[n];
        dfs(a, 0, set1, set2, 0, 0, 0, 0, sum / 2);
        return good * 1.0 / all;
    }

    private void dfs(int[] a, int i, int[] set1, int[] set2, int c1, int c2, int n1, int n2, int limit) {
        if (n1 > limit || n2 > limit) {
            return;
        }
        int n = a.length;
        if (i == n) {
            if (n1 == limit && n2 == limit) {
                double perms = perm(set2, n2) * perm(set1, n1);
                all += perms;
                if (c1 == c2) {
                    good += perms;
                }
            }
            return;
        }
        // pick 0 to a[i] to set1n
        for (int j = 0; j <= a[i]; j++) {
            if (n1 + j <= limit && n2 + a[i] - j <= limit) {
                int c1d = j == 0 ? 0 : 1;
                int c2d = j == a[i] ? 0 : 1;
                set1[i] = j;
                set2[i] = (a[i] - j);
                dfs(a, i + 1, set1, set2, c1 + c1d, c2 + c2d, n1 + j, n2 + a[i] - j, limit);
            }
        }
    }

    private double[] dp = new double[50];

    private double perm(int[] a, int n) {
        double res = factor(n);
        for (int i = 0; i < a.length; i++) {
            res /= factor(a[i]);
        }
        return res;
    }

    private double factor(int n) {
        if (dp[n] != 0) {
            return dp[n];
        }
        dp[n] = n == 0 ? 1.0 : n * factor(n - 1);
        return dp[n];
    }
}
