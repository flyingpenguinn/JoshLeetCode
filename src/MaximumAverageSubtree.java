import base.TreeNode;

public class MaximumAverageSubtree {
    double max= -1.0;
    public double maximumAverageSubtree(TreeNode root) {
        dom(root);
        return max;
    }
    double[] dom(TreeNode node){
        if(node==null){ return new double[]{0.0,0,0}; }
        double[] left= dom(node.left);
        double[] right= dom(node.right);
        double csum= left[1]+right[1]+node.val;
        double ct= left[0]+right[0]+1;
        double cavg= csum/ct;
        max= Math.max(max,cavg);
        return new double[]{ct,csum};
    }
}
