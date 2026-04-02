/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    boolean balanced = true;

    public int dfs(TreeNode n) {
        if (n == null) {
            return 0;
        }

        int l = dfs(n.left);
        int r = dfs(n.right);

        // System.out.printf("%d %d\n", l, r);

        if (Math.abs(l - r) > 1) {
            balanced = false;
        }

        return 1 + Math.max(l, r);
    }

    public boolean isBalanced(TreeNode root) {
        dfs(root);
        return balanced;
    }
}
