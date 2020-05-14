import base.TreeNode;

public class ConstructBinaryTreeFromPreAndPostOrder {

    //when one of left or right tree is empty, we can't really tell who's who
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        return doConstruct(pre, 0, pre.length - 1, post, 0, post.length - 1);
    }

    private TreeNode doConstruct(int[] pre, int prel, int preu, int[] post, int postl, int postu) {
        if (preu - prel != postu - postl) {
            throw new IllegalArgumentException();
        }
        if (prel > preu) {
            return null;
        }
        if (prel == preu) {
            return new TreeNode(pre[prel]);
        }
        int root = pre[prel];
        if (root != post[postu]) {
            throw new IllegalArgumentException();
        }
        // this could be right after all if left is empty but we favor left
        int leftRoot = pre[prel + 1];
        int leftRootIndex = findIndex(post, postl, postu, leftRoot);
        // postl is where left tree starts
        int leftLen = leftRootIndex - postl + 1;
        TreeNode rootNode = new TreeNode(root);
        TreeNode left = doConstruct(pre, prel + 1, prel + leftLen, post, postl, leftRootIndex);
        TreeNode right = doConstruct(pre, prel + leftLen + 1, preu, post, leftRootIndex + 1, postu - 1);
        rootNode.left = left;
        rootNode.right = right;
        return rootNode;
    }

    private int findIndex(int[] array, int l, int u, int candidate) {
        for (int i = l; i <= u; i++) {
            if (array[i] == candidate) {
                return i;
            }
        }
        throw new IllegalArgumentException();
    }
}
