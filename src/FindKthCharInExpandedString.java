public class FindKthCharInExpandedString {
    public char kthCharacter(String s, long k) {
        String[] ws = s.split(" ");
        long index = 0;
        for (String w : ws) {
            int wn = w.length();
            for (int i = 0; i < wn; ++i) {
                index += i + 1;
                // System.out.println(w.charAt(i)+" got "+(i+1)+" times. index="+index);
                if (index >= k + 1) {
                    return w.charAt(i);
                }
            }
            ++index;
            if (index == k + 1) {
                return ' ';
            }
        }
        return '*';
    }
}
