import base.ArrayUtils;

/*
LC#251
Design and implement an iterator to flatten a 2d vector. It should support the following operations: next and hasNext.



Example:

Vector2D iterator = new Vector2D([[1,2],[3],[4]]);

iterator.next(); // return 1
iterator.next(); // return 2
iterator.next(); // return 3
iterator.hasNext(); // return true
iterator.hasNext(); // return true
iterator.next(); // return 4
iterator.hasNext(); // return false


Notes:

Please remember to RESET your class variables declared in Vector2D, as static/class variables are persisted across multiple test cases. Please see here for more details.
You may assume that next() call will always be valid, that is, there will be at least a next element in the 2d vector when next() is called.


Follow up:

As an added challenge, try to code it using only iterators in C++ or iterators in Java.
 */
public class Flatten2DVector {
    public static void main(String[] args) {
        int[][] input = ArrayUtils.readUnEven("[[1,2],[3],[4]]");
        Vector2D v2d = new Vector2D(input);
        System.out.println(v2d.next());
        System.out.println(v2d.next());
        System.out.println(v2d.next());
        System.out.println(v2d.hasNext());
        System.out.println(v2d.hasNext());
        System.out.println(v2d.next());
        System.out.println(v2d.hasNext());
    }
}

class Vector2D {
    // we can also put all iterators in a queue and pull from there
    private int row = 0;
    private int col = 0;
    private int[][] v;

    public Vector2D(int[][] v) {
        this.v = v;
        adj();
    }

    private void adj() {
        // we are about to start at row, check if fine. if not go to the next
        col = 0;
        while (row < v.length && v[row].length == 0) {
            row++;
        }
    }

    public int next() {
        if (!hasNext()) {
            return -1;
        }
        int rt = v[row][col++];
        if (col == v[row].length) {
            row++;
            adj();
        }
        return rt;
    }

    public boolean hasNext() {
        return row < v.length;
    }
}