/*
LC#858
There is a special square room with mirrors on each of the four walls.  Except for the southwest corner, there are receptors on each of the remaining corners, numbered 0, 1, and 2.

The square room has walls of length p, and a laser ray from the southwest corner first meets the east wall at a distance q from the 0th receptor.

Return the number of the receptor that the ray meets first.  (It is guaranteed that the ray will meet a receptor eventually.)



Example 1:

Input: p = 2, q = 1
Output: 2
Explanation: The ray meets receptor 2 the first time it gets reflected back to the left wall.



Note:

1 <= p <= 1000
0 <= q <= p
 */
public class MirrorReflection {
    // think about extending the room upward allowing the beam to bounce back and forth on the left and right walls
    public int mirrorReflection(int p, int q) {
        int lcm = lcm(p, q);
        if ((lcm / q) % 2 == 0) {
            // the number of bounces on the extended graph. if even it's on the left wall and can only be 2
            return 2;
        } else {
            return (lcm / p) % 2; // the height of the extended walls and if it's odd (like 1) it's 1 otherwise 0
        }
    }

    private int lcm(int p, int q) {
        return p * q / gcd(p, q);
    }

    private int gcd(int p, int q) {
        if (p < q) {
            return gcd(q, p);
        }
        return q == 0 ? p : gcd(q, p % q);
    }
}