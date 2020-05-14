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
    static class Sea {
        public boolean hasShips(int[] topRight, int[] bottomLeft) {
            if (bottomLeft[0] > topRight[0] || bottomLeft[1] > topRight[1]) {
                return false;
            }
            return true;
        }
    }


    int ships = 0;

    public int countShips(Sea sea, int[] tr, int[] bl) {
        find(sea, tr, bl);
        return ships;
    }

    private void find(Sea sea, int[] tr, int[] bl) {
        if (ships >= 10) {
            return;
        }
        if (bl[0] > tr[0] || bl[1] > tr[1]) {
            return;
        }
        if (bl[0] == tr[0] && bl[1] == tr[1]) {
            if (sea.hasShips(tr, bl)) {
                ships++;
            }
            return;
        }
        boolean has = sea.hasShips(tr, bl);
        if (!has) {
            return;
        }
        int mx = (tr[0] + bl[0]) / 2;
        int my = (tr[1] + bl[1]) / 2;
        find(sea, new int[]{mx, my}, bl);
        find(sea, new int[]{mx, tr[1]}, new int[]{bl[0], my + 1});
        find(sea, tr, new int[]{mx + 1, my + 1});
        find(sea, new int[]{tr[0], my}, new int[]{mx + 1, bl[1]});
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new NumberOfShipsInRectangle().countShips(new Sea(), read1d("1,1"), read1d("0,0")));

    }

}
