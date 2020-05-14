import base.ArrayUtils;

public class ShiftingLetters {
    public String shiftingLetters(String s, int[] shifts) {
        int n = shifts.length;
        int cs = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = n - 1; i >= 0; i--) {
            cs += shifts[i];
            cs %= 26;// must mod to avoid overflow
            int dst = (s.charAt(i) - 'a' + cs) % 26;
            char shifted = (char) ('a' + dst);
            sb.append(shifted);
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new ShiftingLetters().shiftingLetters("mkgfzkkuxownxvfvxasy", ArrayUtils.read1d("[505870226,437526072,266740649,224336793,532917782,311122363,567754492,595798950,81520022,684110326,137742843,275267355,856903962,148291585,919054234,467541837,622939912,116899933,983296461,536563513]")));
    }
}
