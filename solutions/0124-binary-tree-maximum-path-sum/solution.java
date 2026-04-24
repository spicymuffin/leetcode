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
    int max = -1;

    int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lmax = dfs(root.left);
        if (lmax < 0) {
            lmax = 0;
        }
        int rmax = dfs(root.right);
        if (rmax < 0) {
            rmax = 0;
        }
        int path_sum = lmax + rmax + root.val;
        int path1 = Math.max(lmax, rmax) + root.val;
        int path2 = root.val;

        if (max < path_sum) {
            max = path_sum;
        }

        return Math.max(path1, path2);
    }

    public int maxPathSum(TreeNode root) {
        max = root.val;
        dfs(root);
        return max;
    }
}
