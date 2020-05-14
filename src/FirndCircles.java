import java.util.Arrays;

/*
LC547
There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.

Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.

Example 1:
Input:
[[1,1,0],
 [1,1,0],
 [0,0,1]]
Output: 2
Explanation:The 0th and 1st students are direct friends, so they are in a friend circle.
The 2nd student himself is in a friend circle. So return 2.
Example 2:
Input:
[[1,1,0],
 [1,1,1],
 [0,1,1]]
Output: 1
Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends,
so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
Note:
N is in range [1,200].
M[i][i] = 1 for all students.
If M[i][j] = 1, then M[j][i] = 1.
 */
public class FirndCircles {
    // can use dfs as well because we only need to get components once
    public int findCircleNum(int[][] m) {
        int n = m.length;
        int[] p = new int[n];
        Arrays.fill(p, -1);
        int[] size = new int[n];
        int sets = n;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (m[i][j] == 0) {
                    continue;
                }
                int s1 = find(i, p);
                int s2 = find(j, p);
                if (s1 == s2) {
                    continue;
                }
                if (size[s1] < size[s2]) {
                    p[s1] = s2;
                } else {
                    p[s2] = s1;
                }
                sets--;
            }
        }
        return sets;
    }

    int find(int i, int[] p) {
        if (p[i] == -1) {
            return i;
        }
        int rt = find(p[i], p);
        p[i] = rt;
        return rt;
    }
}
