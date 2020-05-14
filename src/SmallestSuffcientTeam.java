import base.ArrayUtils;

import java.util.*;
/*
LC#1125
In a project, you have a list of required skills req_skills, and a list of people.  The i-th person people[i] contains a list of skills that person has.

Consider a sufficient team: a set of people such that for every required skill in req_skills, there is at least one person in the team who has that skill.  We can represent these teams by the index of each person: for example, team = [0, 1, 3] represents the people with skills people[0], people[1], and people[3].

Return any sufficient team of the smallest possible size, represented by the index of each person.

You may return the answer in any order.  It is guaranteed an answer exists.



Example 1:

Input: req_skills = ["java","nodejs","reactjs"], people = [["java"],["nodejs"],["nodejs","reactjs"]]
Output: [0,2]
Example 2:

Input: req_skills = ["algorithms","math","java","reactjs","csharp","aws"], people = [["algorithms","math","java"],["algorithms","math","reactjs"],["java","csharp","aws"],["reactjs","csharp"],["csharp","math"],["aws","java"]]
Output: [1,2]


Constraints:

1 <= req_skills.length <= 16
1 <= people.length <= 60
1 <= people[i].length, req_skills[i].length, people[i][j].length <= 16
Elements of req_skills and people[i] are (respectively) distinct.
req_skills[i][j], people[i][j][k] are lowercase English letters.
Every skill in people[i] is a skill in req_skills.
It is guaranteed a sufficient team exists.
 */
public class SmallestSuffcientTeam {
    // graph vertex cover problem... O(n*2^n)
    int[][] dp;
    int[][] choice;
    Map<String, Integer> sm = new HashMap<>();
    List<Integer> r = new ArrayList<>();

    public int[] smallestSufficientTeam(String[] s, List<List<String>> p) {

        for (int i = 0; i < s.length; i++) {
            sm.put(s[i], i);
        }
        int pn = p.size();
        int sn = s.length;
        dp = new int[pn][1 << sn];
        choice = new int[pn][1 << sn];
        for (int i = 0; i < pn; i++) {
            Arrays.fill(dp[i], -1);
        }
        int minpick = dos(0, 0, p, s);
        sol(0, 0, p.size(), s.length);
        int[] rarray = new int[r.size()];
        for (int i = 0; i < r.size(); i++) {
            rarray[i] = r.get(i);
        }
        return rarray;
    }

    private void sol(int i, int st, int pn, int sn) {
        if (i == pn) {
            return;
        }
        if (choice[i][st] != 0) {
            r.add(i);
            sol(i + 1, choice[i][st], pn, sn);
        } else {
            sol(i + 1, st, pn, sn);
        }
    }

    int Max = 1000000;

    private int dos(int i, int st, List<List<String>> p, String[] s) {
        int pn = p.size();
        int sn = s.length;
        if (i == pn) {
            return st == ((1 << sn) - 1) ? 0 : Max;
        }
        if (dp[i][st] != -1) {
            return dp[i][st];
        }
        int nopick = dos(i + 1, st, p, s);
        List<String> ps = p.get(i);
        int nst = st;
        for (String psk : ps) {
            nst |= (1 << sm.get(psk));
        }
        int pick = 1 + dos(i + 1, nst, p, s);
        int min = Math.min(pick, nopick);
        if (nopick < pick) {
            choice[i][st] = 0;
        } else {
            choice[i][st] = nst;
        }
        dp[i][st] = min;
        return min;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new SmallestSuffcientTeam().smallestSufficientTeam(new String[]{"java", "nodejs", "reactjs"}, ArrayUtils.readAsListUnevenString("[[java],[nodejs],[nodejs,reactjs]]"))));
        System.out.println(Arrays.toString(new SmallestSuffcientTeam().smallestSufficientTeam(new String[]{"algorithms", "math", "java", "reactjs", "csharp", "aws"}, ArrayUtils.readAsListUnevenString("[[algorithms,math,java],[algorithms,math,reactjs],[java,csharp,aws],[reactjs,csharp],[csharp,math],[aws,java]]"))));
    }
}
