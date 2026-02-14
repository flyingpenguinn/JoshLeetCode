public class WeightedWordMapping {
    public String mapWordWeights(String[] ss, int[] ws) {
        int n = ss.length;
        StringBuilder sb = new StringBuilder();
        for(String s: ss){
            int sum = 0;
            for(char c: s.toCharArray()){
                int cind = c-'a';
                sum += ws[cind];
            }
            sum %= 26;
            int nind = 25-sum;
            char nchar = (char)('a'+nind);
            sb.append(nchar);
        }
        return sb.toString();
    }
}
