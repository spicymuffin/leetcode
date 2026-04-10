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
    int cnt = 0;

    void dfs(TreeNode n, int max) {
        if (n == null) {
            return;
        }

        if (n.val >= max) {
            max = n.val;
            cnt++;
        }

        dfs(n.left, max);
        dfs(n.right, max);
    }

    public int goodNodes(TreeNode root) {
        dfs(root, Integer.MIN_VALUE);

        return cnt;
    }
}
