class Solution {
    int[] preorder;
    int[] inorder;
    int n;

    HashMap<Integer, Integer> in_map = new HashMap<>();

    private TreeNode tree_constructor(int root_pre_idx, int in_l, int in_r) {
        if (root_pre_idx >= n || in_l > in_r) {
            return null;
        }

        int root_in_idx = in_map.get(preorder[root_pre_idx]);
        int nnode_l = root_in_idx - in_l;

        TreeNode node_l = tree_constructor(root_pre_idx + 1, in_l, root_in_idx - 1);
        TreeNode node_r = tree_constructor(root_pre_idx + 1 + nnode_l, root_in_idx + 1, in_r);

        return new TreeNode(preorder[root_pre_idx], node_l, node_r);
    }

    public TreeNode buildTree(int[] _preorder, int[] _inorder) {
        preorder = _preorder;
        inorder = _inorder;

        n = _inorder.length;

        for (int i = 0; i < n; i++) {
            in_map.put(_inorder[i], i);
        }

        return tree_constructor(0, 0, n - 1);
    }
}
