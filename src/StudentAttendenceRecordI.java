/*
LC#551
You are given a string representing an attendance record for a student. The record only contains the following three characters:
'A' : Absent.
'L' : Late.
'P' : Present.
A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).

You need to return whether the student could be rewarded according to his attendance record.

Example 1:
Input: "PPALLP"
Output: True
Example 2:
Input: "PPALLL"
Output: False
 */
public class StudentAttendenceRecordI {
    public boolean checkRecord(String s) {
        int ac = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'A') {
                ac++;
                if (ac >= 2) {
                    return false;
                }
            }
            if (i >= 2 && s.charAt(i) == s.charAt(i - 1) && s.charAt(i - 1) == s.charAt(i - 2) && s.charAt(i) == 'L') {
                return false;
            }
        }
        return true;
    }
}
