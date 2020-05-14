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
    int i=0;
    int j=-1;
    int[][] a;
    boolean cached=false;

    public Vector2D(int[][] v) {
        this.a=v;
    }

    public int next() {
        int n=a.length;
        if(cached){
            cached=false;
            return i==n?-1:a[i][j];
        }
        if(hasNext()){
            cached=false;
            return a[i][j];
        }else{
            return -1;
        }
    }

    // has next is like peek
    public boolean hasNext() {
        int n=a.length;
        if(cached){
            return i!=n;
        }
        j++;
        while(i<n && j>=a[i].length){
            i++;
            j=0;
        }
        if(i==n){
            return false;
        }
        cached=true;
        return true;
    }
}