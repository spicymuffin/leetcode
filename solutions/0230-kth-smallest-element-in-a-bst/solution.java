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
    public int _kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> st = new Stack<>();
        while (root != null || !st.isEmpty()) {
            while (root != null) {
                st.push(root);
                root = root.left;
            }

            root = st.pop();
            k--;
            if (k <= 0) {
                return root.val;
            }
            root = root.right;
        }

        return 0;
    }

    int count = 0;
    int ans = 0;

    public void dfs(TreeNode root, int k) {
        if (root == null)
            return;

        dfs(root.left, k);

        count++;
        if (count == k) {
            ans = root.val;
            return;
        }

        dfs(root.right, k);
    }

    public int kthSmallest(TreeNode root, int k) {
        dfs(root, k);
        return ans;
    }
}
