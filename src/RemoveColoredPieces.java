public class RemoveColoredPieces {
    public boolean winnerOfGame(String input) {
        char[] s = input.toCharArray();
        int n = s.length;
        int i = 0;
        int[] count = new int[2];
        while(i<n){
            int ci = 0;
            if(s[i]=='B'){
                ci = 1;
            }
            int j = i;
            while(j<n && s[j]==s[i]){
                ++j;
            }
            count[ci] += Math.max(j-i-2, 0);
            i=j;
        }
        return count[0]>count[1];
    }
}
