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

    class Solution {
        public int guessMajority(ArrayReader reader) {
            int n = reader.length();
            int first4 = reader.query(0, 1, 2, 3);
            int v0234 = reader.query(0, 2, 3, 4);
            int v1234 = reader.query(1, 2, 3, 4);
            int v0134 = reader.query(0, 1, 3, 4);
            int c0 = 0;
            int cn0 = 0;
            int indexn0 = -1;
            if (first4 == 4) {
                c0 = 4;
                for (int i = 4; i < n; i++) {
                    int cur = reader.query(0, 1, 2, i);
                    if (cur == 4) {
                        c0++;
                    } else {
                        if (indexn0 == -1) {
                            indexn0 = i;
                        }
                        cn0++;
                    }
                }
            } else if (first4 == 2) {
                if (v0234 == v1234) {
                    // 0==1, check 2
                    int same0 = -1;
                    if (v0134 == v1234) {
                        // 0==1==2
                        indexn0 = 3;
                        same0 = 2;
                    } else {
                        // or 0==1==3
                        indexn0 = 2;
                        same0 = 3;
                    }
                    c0 = 3;
                    cn0 = 1;
                    // 0,1
                    for (int i = 4; i < n; i++) {
                        int cur = reader.query(0, 1, same0, i);
                        if (cur == 4) {
                            c0++;
                        } else {
                            cn0++;
                        }
                    }
                } else {
                    if (v1234 == v0134) {
                        // 0==2==3, 1 is the outlier
                        c0 = 3;
                        cn0 = 1;
                        indexn0 = 1;
                        // 0,1
                        for (int i = 4; i < n; i++) {
                            int cur = reader.query(0, 2, 3, i);
                            if (cur == 4) {
                                c0++;
                            } else {
                                cn0++;
                            }
                        }
                    } else {
                        // 0 is the outlier.1==2==3
                        c0 = 1;
                        indexn0 = 1;
                        cn0 = 3;
                        // 0,1
                        for (int i = 4; i < n; i++) {
                            int cur = reader.query(1, 2, 3, i);
                            if (cur == 4) {
                                cn0++;
                            } else {
                                c0++;
                            }
                        }
                    }
                }
            } else {
                // 2 vs 2
                if (v0234 == v1234) {
                    // 0==1, 2==3
                    c0 = 2;
                    cn0 = 2;
                    indexn0 = 2;
                    // 0,1
                    for (int i = 4; i < n; i++) {
                        int cur = reader.query(0, 1, 2, i);
                        if (cur == 2) {
                            c0++;
                        } else {
                            // would be ==0
                            cn0++;
                        }
                    }
                } else {
                    if (v0134 == v1234) {
                        // 0==2, 1==3
                        c0 = 2;
                        cn0 = 2;
                        indexn0 = 1;
                        // 0,1
                        for (int i = 4; i < n; i++) {
                            int cur = reader.query(0, 1, 2, i);
                            if (cur == 2) {
                                c0++;
                            } else {
                                // would be ==0
                                cn0++;
                            }
                        }
                    } else {
                        // 0==3, 1==2
                        c0 = 2;
                        cn0 = 2;
                        indexn0 = 1;
                        // 0,3
                        for (int i = 4; i < n; i++) {
                            int cur = reader.query(0, 2, 3, i);
                            if (cur == 2) {
                                c0++;
                            } else {
                                // would be ==0
                                cn0++;
                            }
                        }
                    }
                }
            }
            if (c0 > cn0) {
                return 0;
            } else if (c0 < cn0) {
                return indexn0;
            } else {
                return -1;
            }
        }
    }
}
