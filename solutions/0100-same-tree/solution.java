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

    public boolean dfs(TreeNode a, TreeNode b) {
        if (a == null || b == null) {
            if (a != b) {
                return false;
            }
            else {
                return true;
            }
        }

        if (a.val != b.val) {
            return false;
        }

        boolean l = dfs(a.left, b.left);
        boolean r = dfs(a.right, b.right);

        if (!(l && r)) {
            return false;
        }

        return true;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        return dfs(p, q);
    }
}
