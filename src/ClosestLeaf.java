import base.TreeNode;
import base.Trees;

import java.util.*;

public class ClosestLeaf {

    int Max=10000000;

    int[] min={-1,Max};
    int td=-1;
    public int findClosestLeaf(TreeNode root, int k) {
        dfs(root,k,0);
        return min[0];
    }

    void dfs(TreeNode root,int v,int d){
        if(root==null){
            return;
        }
        if(root.val==v){
            td=d;
            min=dfsleaf(root,0);
            return;
        }
        dfs(root.left,v,d+1);
        if(td!=-1){
            int delta=td-d;
            if(delta>=min[1]){
                return;
            }
            int [] rd=dfsleaf(root.right,delta);
            if(rd[1]<min[1]){
                min=rd;
            }
            return;
        }
        dfs(root.right,v,d+1);
        if(td!=-1){
            int delta=td-d;
            if(delta>=min[1]){
                return;
            }
            int [] ld=dfsleaf(root.left,delta);
            if(ld[1]<min[1]){
                min=ld;
            }
        }
    }
    // depth of nearest leaf
    int[] dfsleaf(TreeNode n,int d){
        if(n==null){
            return new int[]{-1,Max};
        }
        if(n.left==null && n.right==null){
            return new int[]{n.val,d};
        }
        int[] ld=dfsleaf(n.left,d+1);
        int[] rd=dfsleaf(n.right,d+1);
        if(ld[1]<rd[1]){
            return ld;
        }
        return rd;
    }

}


