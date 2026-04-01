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
    int max_diameter = -1;

    public int finddepth(TreeNode n) {
        if (n == null) {
            return 0;
        }

        int dl = finddepth(n.left);
        int dr = finddepth(n.right);

        if (dl + dr > max_diameter) {
            max_diameter = dl + dr;
        }

        return 1 + Math.max(dl, dr);
    }

    public int diameterOfBinaryTree(TreeNode root) {
        finddepth(root);
        return max_diameter;
    }
}
