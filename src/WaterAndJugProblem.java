/*
LC#365
You are given two jugs with capacities x and y litres. There is an infinite amount of water supply available. You need to determine whether it is possible to measure exactly z litres using these two jugs.

If z liters of water is measurable, you must have z liters of water contained within one or both buckets by the end.

Operations allowed:

Fill any of the jugs completely with water.
Empty any of the jugs.
Pour water from one jug into another till the other jug is completely full or the first jug itself is empty.
Example 1: (From the famous "Die Hard" example)

Input: x = 3, y = 5, z = 4
Output: True
Example 2:

Input: x = 2, y = 6, z = 5
Output: False
 */
public class WaterAndJugProblem {
    // whether ax+by = z has integer solution. Bezeut theorem. : gcd(a,b) | z
    // similar to "check ii is good array"
    public boolean canMeasureWater(int x, int y, int z) {
        if (x == 0) {
            return z == y || z == 0;
        }
        if (y == 0) {
            return z == x || z == 0;
        }
        if (x + y < z) {
            return false;
        }
        int gcd = gcd(x, y);
        return z % gcd == 0;
    }

    int gcd(int x, int y) {
        if (x < y) {
            return gcd(y, x);
        } else {
            return y == 0 ? x : gcd(y, x % y);
        }
    }

    public static void main(String[] args) {

        System.out.println(new WaterAndJugProblem().canMeasureWater(11, 3, 13));
        System.out.println(new WaterAndJugProblem().canMeasureWater(104681, 104683, 54));
        System.out.println(new WaterAndJugProblem().canMeasureWater(104639, 104651, 234));
        System.out.println(new WaterAndJugProblem().canMeasureWater(104579, 104593, 12444));
        System.out.println(new WaterAndJugProblem().canMeasureWater(1, 2, 3));
        System.out.println(new WaterAndJugProblem().canMeasureWater(3, 5, 4));
        System.out.println(new WaterAndJugProblem().canMeasureWater(2, 6, 5));
    }
}
