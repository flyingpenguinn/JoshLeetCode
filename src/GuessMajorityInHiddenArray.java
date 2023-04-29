public class GuessMajorityInHiddenArray {
    interface ArrayReader {

        // Compares 4 different elements in the array
        // return 4 if the values of the 4 elements are the same (0 or 1).
        // return 2 if three elements have a value equal to 0 and one element ha
        // return 0 : if two element have a value equal to 0 and two elements ha
        public int query(int a, int b, int c, int d);

        // Returns the length of the array
        public int length();
    }

    // figure out the layout of the first 4 then use that to deduce the later ones
    class Solution {
        public int guessMajority(ArrayReader reader) {
            int n = reader.length();
            int v3 = reader.query(0, 1, 2, 3);
            int v4 = reader.query(0, 1, 2, 4);
            //    System.out.println(v3+" "+v4);
            int count3 = (v3 == v4) ? 2 : 1;
            int i3 = 3;
            int count4 = (v3 == v4) ? 0 : 1;
            int i4 = 4; // if 3 and 4 are different, this will be changed later anyway
            for (int i = 5; i < n; ++i) {
                int cv = reader.query(0, 1, 2, i);
                if (cv == v3) {
                    //      System.out.println(i +" goes with 3");
                    ++count3;
                    i3 = i;
                } else {
                    //       System.out.println(i +" goes with 4");
                    ++count4;
                    i4 = i;
                }
            }
            //   System.out.println(count3+" "+count4);
            int target = reader.query(0, 1, 2, 4);
            int[][] rem = new int[][]{{1, 2, 3, 4}, {0, 2, 3, 4}, {0, 1, 3, 4}};
            for (int i = 0; i < rem.length; ++i) {
                int[] r = rem[i];
                if (reader.query(r[0], r[1], r[2], r[3]) == target) {
                    //         System.out.println(i +" goes with 3");
                    ++count3;
                    i3 = i;
                } else {
                    ++count4;
                    //        System.out.println(i +" goes with 4");
                    i4 = i;
                }
            }
            if (count3 > count4) {
                return i3;
            } else if (count3 < count4) {
                return i4;
            } else {
                return -1;
            }
        }
    }
}
