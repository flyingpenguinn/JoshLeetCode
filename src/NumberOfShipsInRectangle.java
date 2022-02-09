import java.io.IOException;

import java.io.IOException;

import static base.ArrayUtils.read1d;

/*
LC#1274
(This problem is an interactive problem.)

On the sea represented by a cartesian plane, each ship is located at an integer point, and each integer point may contain at most 1 ship.

You have a function Sea.hasShips(topRight, bottomLeft) which takes two points as arguments and returns true if and only if there is at least one ship in the rectangle represented by the two points, including on the boundary.

Given two points, which are the top right and bottom left corners of a rectangle, return the number of ships present in that rectangle.  It is guaranteed that there are at most 10 ships in that rectangle.

Submissions making more than 400 calls to hasShips will be judged Wrong Answer.  Also, any solutions that attempt to circumvent the judge will result in disqualification.



Example :



Input:
ships = [[1,1],[2,2],[3,3],[5,5]], topRight = [4,4], bottomLeft = [0,0]
Output: 3
Explanation: From [0,0] to [4,4] we can count 3 ships within the range.


Constraints:

On the input ships is only given to initialize the map internally. You must solve this problem "blindfolded". In other words, you must find the answer using the given hasShips API, without knowing the ships position.
0 <= bottomLeft[0] <= topRight[0] <= 1000
0 <= bottomLeft[1] <= topRight[1] <= 1000
 */
public class NumberOfShipsInRectangle {
    // cut till it's single point and decide.
    // note we always give mx/my to the point that has the old 0,0 in a four-grid matrix
    // if in doubt, just need to make 0,0 and 1,1 work
    static class Sea {
        public boolean hasShips(int[] topRight, int[] bottomLeft) {
            if (bottomLeft[0] > topRight[0] || bottomLeft[1] > topRight[1]) {
                return false;
            }
            return true;
        }
    }


    public int countShips(Sea sea, int[] topRight, int[] bottomLeft) {
        int b0 = bottomLeft[0];
        int b1 = bottomLeft[1];
        int t0 = topRight[0];
        int t1 = topRight[1];

        if (b0 > t0 || b1 > t1) {
            return 0;
        }
        if (b0 == t0 && b1 == t1) {
            return sea.hasShips(topRight, bottomLeft) ? 1 : 0;
        }

        if (!sea.hasShips(topRight, bottomLeft)) {
            return 0;
        }

        int mid0 = (b0 + t0) / 2;
        int mid1 = (b1 + t1) / 2;
        int[] tr1 = new int[]{mid0, mid1};
        int[] bt1 = bottomLeft;
        int[] tr2 = new int[]{mid0, t1};
        int[] bt2 = new int[]{b0, mid1 + 1};
        int[] tr3 = new int[]{t0, mid1};
        int[] bt3 = new int[]{mid0 + 1, b1};
        int[] tr4 = topRight;
        int[] bt4 = new int[]{mid0 + 1, mid1 + 1};
        return countShips(sea, tr1, bt1) + countShips(sea, tr2, bt2) + countShips(sea, tr3, bt3) + countShips(sea, tr4, bt4);
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new NumberOfShipsInRectangle().countShips(new Sea(), read1d("1,1"), read1d("0,0")));

    }

}
