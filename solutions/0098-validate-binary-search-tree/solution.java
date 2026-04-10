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
    boolean dfs(TreeNode n, long min, long max) {
        if (n == null)
            return true;
        if (!((n.left == null ? true : (n.left.val < n.val && n.left.val > min)) &&
             (n.right == null ? true : (n.val < n.right.val && n.right.val < max)))) {
            return false;
        }
        return dfs(n.left, min, n.val) && dfs(n.right, n.val, max);
    }

    public boolean isValidBST(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
}
