import base.ArrayUtils;

/*
LC#517
You have n super washing machines on a line. Initially, each washing machine has some dresses or is empty.

For each move, you could choose any m (1 ≤ m ≤ n) washing machines, and pass one dress of each washing machine to one of its adjacent washing machines at the same time .

Given an integer array representing the number of dresses in each washing machine from left to right on the line, you should find the minimum number of moves to make all the washing machines have the same number of dresses. If it is not possible to do it, return -1.

Example1

Input: [1,0,5]

Output: 3

Explanation:
1st move:    1     0 <-- 5    =>    1     1     4
2nd move:    1 <-- 1 <-- 4    =>    2     1     3
3rd move:    2     1 <-- 3    =>    2     2     2
Example2

Input: [0,3,0]

Output: 2

Explanation:
1st move:    0 <-- 3     0    =>    1     2     0
2nd move:    1     2 --> 0    =>    1     1     1
Example3

Input: [0,2,0]

Output: -1

Explanation:
It's impossible to make all the three washing machines have the same number of dresses.
Note:
The range of n is [1, 10000].
The range of dresses number in a super washing machine is [0, 1e5].
 */
public class SuperWashingMachines {
    // similar to distribute coin in binary tree.
    // because we can move m pos instead of only one, we use max
    // also because we can't move to both neighbors at the same time, we need to max a[i]-t
    public int findMinMoves(int[] a) {
        int sum = 0;
        int n = a.length;
        for (int i = 0; i < n; i++) {
            sum += a[i];
        }
        if (sum % n != 0) {
            return -1;
        }
        int t = sum / n;
        int r = 0;
        sum = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i];
            r = Math.max(r, Math.abs(sum - (i + 1) * t));
            // how many needs to pass a[i] to/from the right
            if (a[i] > t) {
                r = Math.max(r, a[i] - t);
                // we at least needs this for a[i] itself.
                // note if this is bigger than the above max, it's because we need to move numbers leftward
            }
        }
        return r;
    }


    public static void main(String[] args) {
        System.out.println(new SuperWashingMachines().findMinMoves(ArrayUtils.read1d("[0,4,4,0]")));

        /*
        System.out.println(new SuperWashingMachines().findMinMoves(ArrayUtils.read1d("[5,0,0,11]")));
        System.out.println(new SuperWashingMachines().findMinMoves(ArrayUtils.read1d("[0,0,5,11]")));
        System.out.println(new SuperWashingMachines().findMinMoves(ArrayUtils.read1d("[5,0,11,0]")));
        System.out.println(new SuperWashingMachines().findMinMoves(ArrayUtils.read1d("[4,0,4,0]")));
        */
    }
}

class SuperWashMachineAnotherWay {

    // for given i how many must pass through because of lack of dresses left/right
    public int findMinMoves(int[] a) {
        int sum = 0;
        int n = a.length;
        for (int i = 0; i < n; i++) {
            sum += a[i];
        }
        if (sum % n != 0) {
            return -1;
        }
        int t = sum / n;
        int max = 0;
        int psum = 0;
        for (int i = 0; i < n; i++) {
            psum += a[i];
            if (a[i] > t) {
                int delta = a[i] - t;
                // left not enough, need more to pass from the right.
                // note we'd better spend all of a[i] to the left in this case because right is good
                if (psum < t * (i + 1)) {
                    delta += t * (i + 1) - psum;
                }
                max = Math.max(delta, max);
            }
        }
        psum = 0;
        for (int i = n - 1; i >= 0; i--) {
            psum += a[i];
            if (a[i] > t) {
                int delta = a[i] - t;
                // right not enough, need to pass from the left
                // note we'd better spend a[i] on the right because left is fine
                if (psum < t * (n - i)) {
                    delta += t * (n - i) - psum;
                }
                max = Math.max(delta, max);
            }
        }
        return max;
    }

}

