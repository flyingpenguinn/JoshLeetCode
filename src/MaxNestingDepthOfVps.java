public class MaxNestingDepthOfVps {

    // alternate the parenthesis....level1 goes to a, level 2 goes to b, level 3 goes to a....
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] r = new int[seq.length()];
        int level = 0;
        for (int i = 0; i < n; i++) {
            if (seq.charAt(i) == '(') {
                level++;
            }
            r[i] = level % 2;
            if (seq.charAt(i) == ')') {
                level--;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(new MaxNestingDepthOfVps().maxDepthAfterSplit("()(())()"));
    }
}
