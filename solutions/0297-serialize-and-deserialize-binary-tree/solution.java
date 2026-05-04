/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
    void ser_dfs(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("x,");
            return;
        }
        sb.append(root.val);
        sb.append(',');
        ser_dfs(root.left, sb);
        ser_dfs(root.right, sb);
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        ser_dfs(root, sb);
        // System.out.println(sb.toString());
        return sb.toString();
    }

    int idx;

    TreeNode des_dfs(String data) {
        if (data.charAt(idx) == 'x') {
            idx += 2;
            return null;
        }

        int value = 0;
        int sign = 1;
        while (data.charAt(idx) != ',') {
            if (data.charAt(idx) == '-') {
                sign = -1;
            } else {
                value *= 10;
                value += (int) (data.charAt(idx) - '0');
            }
            idx++;
        }
        idx++;

        value *= sign;

        TreeNode cur = new TreeNode(value);
        cur.left = des_dfs(data);
        cur.right = des_dfs(data);

        return cur;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        return des_dfs(data);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
