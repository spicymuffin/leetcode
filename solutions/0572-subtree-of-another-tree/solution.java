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

    public boolean isidentical(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        if (!isidentical(p.right, q.right)) {
            return false;
        }
        if (!isidentical(p.left, q.left)) {
            return false;
        }
        return true;
    }

    public boolean isSubtree(TreeNode root, TreeNode sub) {
        if (root == null)
            return false;
        if (root.val == sub.val) {
            if (isidentical(root, sub)) {
                return true;
            }
        }
        return isSubtree(root.left, sub) || isSubtree(root.right, sub);
    }
}
