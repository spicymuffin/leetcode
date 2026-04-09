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
    List<Integer> ans;

    public void dfs(TreeNode root, int depth) {
        if (root == null) {
            return;
        }

        dfs(root.right, depth + 1); // visit right first, populate

        while (ans.size() <= depth) {
            ans.add(-999);
        }

        if (ans.get(depth) == -999) {
            ans.set(depth, root.val);
        }

        dfs(root.left, depth + 1);
    }

    public List<Integer> rightSideView(TreeNode root) {
        ans = new ArrayList<>();
        // ans.add(-999);
        // if (root != null) {
        //     ans[0] = root;
        // }

        dfs(root, 0);

        return ans;
    }
}
