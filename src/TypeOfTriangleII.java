public class TypeOfTriangleII {
    // check none first!
    public String triangleType(int[] a) {
        int n = a.length;
        if (!(a[0] + a[1] > a[2] && a[1] + a[2] > a[0] && a[0] + a[2] > a[1])) {
            return "none";
        }
        if (a[0] == a[1] && a[1] == a[2]) {
            return "equilateral";
        } else if (a[0] == a[1] || a[1] == a[2] || a[2] == a[0]) {
            return "isosceles";
        } else {
            return "scalene";
        }
    }
}
