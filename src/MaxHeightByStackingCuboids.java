import java.util.Arrays;

public class MaxHeightByStackingCuboids {
    // almost last min ac in mock contest...
    // note it's asking for a subset, not a subsequence. the diff is in subsequence we can in subset we dont need to conform to an order!

    // two observations:
    //  sort by smallest edge, this gives us the sequence we need to iterate. now it becomes max increasing subsequence
    //  height must be the longest edge. because if height is not the longest edge:
    // 1. if it's the top one, then we simply rotate. the area will be smaller anyway
    // 2. otheerwise it's in the middle: a1, a3, a2 => b1, b2, b3. b is put on a
    // a2 >=b3>=b2
    // a3 >=a2>=b3
    // hence we can rotate and make the whole thing taller

    // 2D BIT
    // 2d bit
    int[][] bit;

    int q(int y, int z) {
        int res = 0;
        int oz = z;
        while (y > 0) {
            z = oz;
            while (z > 0) {
                res = Math.max(res, bit[y][z]);
                z -= (z & -z);
            }
            y -= (y & -y);
        }
        return res;
    }

    void u(int y, int z, int v) {
        int yn = bit.length;
        int zn = bit[0].length;
        int oz = z;
        while (y < yn) {
            z = oz;
            while (z < zn) {
                bit[y][z] = Math.max(bit[y][z], v);
                z += z & -z;
            }
            y += y & -y;
        }
    }

    public int maxHeight(int[][] a) {
        int n = a.length;
        bit = new int[105][105];
        for (int i = 0; i < n; ++i) {
            Arrays.sort(a[i]);
        }
        Arrays.sort(a, (x, y) -> {
            if (x[0] != y[0]) {
                return Integer.compare(x[0], y[0]);
            } else if (x[1] != y[1]) {
                return Integer.compare(x[1], y[1]);
            } else {
                return x[2] - y[2];
            }
        });
        int res = 0;
        for (int i = 0; i < n; i++) {
            int y = a[i][1];
            int z = a[i][2];
            int mb = q(y, z);
            int cur = mb + z;
            u(y, z, cur);
            //  System.out.println(y+" "+z+" "+mb+" "+cur);
            res = Math.max(res, cur);
        }
        return res;
    }
}
