import base.TreeNode;
import base.Trees;

import java.util.HashMap;
import java.util.Map;

public class MinFlipsInBinaryTree {
    private Map<TreeNode, Boolean> vm = new HashMap<>();
    private Map<TreeNode, Map<Boolean, Integer>> dp = new HashMap<>();

    public int minimumFlips(TreeNode root, boolean result) {
        dfs(root);
        return solve(root, result);
    }

    private Boolean dfs(TreeNode n){
        Boolean res = null;
        if(n==null){
            return null;
        }
        else if(n.val <= 1){
            res = n.val == 1;
        }else{
            Boolean lv = dfs(n.left);
            Boolean rv = dfs(n.right);
            if(n.val == 2){
                res= lv || rv;
            }else if(n.val == 3){
                res= lv && rv;
            }else if(n.val == 4){
                res= lv ^ rv;
            }else{
                res= lv!= null ? !lv: !rv;
            }
        }
        vm.put(n, res);
        return res;
    }

    private int solve(TreeNode n, boolean r){

        int res = 0;
        if(vm.get(n) == r){
            return 0;
        }
        Map<Boolean, Integer> dm = dp.getOrDefault(n, new HashMap<>());
        if(dm.containsKey(r)){
            return dm.get(r);
        }
        if(n.val<=1){
            res = 1;
        }else if(n.val == 2){
            if(r == true){
                res = Math.min(solve(n.left, true) ,solve(n.right, true));
            }else{
                res = solve(n.left, false) + solve(n.right, false);
            }
        }else if(n.val == 3){
            if(r == false){
                res = Math.min(solve(n.left, false) ,solve(n.right, false));
            }else{
                res = solve(n.left, true) + solve(n.right, true);
            }
        }else if(n.val == 4){
            res = Math.min(solve(n.left, !vm.get(n.left)) ,solve(n.right, !vm.get(n.right)));
        }else{
            if(n.left != null){
                res = solve(n.left, !vm.get(n.left));
            }else{
                res = solve(n.right, !vm.get(n.right));
            }
        }
        dm.put(r, res);
        dp.put(n, dm);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new MinFlipsInBinaryTree().minimumFlips(Trees.fromString("3,5,4,2,null,1,1,1,0"), true));
    }
}
