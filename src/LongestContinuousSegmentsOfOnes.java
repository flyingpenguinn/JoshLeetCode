public class LongestContinuousSegmentsOfOnes {
    public boolean checkZeroOnes(String s) {
        int i = 0;
        int[] len = new int[2];
        while(i<s.length()){
            int j = i;
            while(j<s.length() && s.charAt(i) == s.charAt(j)){
                j++;
            }
            int cind = s.charAt(i) - '0';
            len[cind] = Math.max(len[cind], j-i);
            i = j;
        }
        return len[1]>len[0];
    }
}
