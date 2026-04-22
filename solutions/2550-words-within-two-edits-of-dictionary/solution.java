class Solution {
    class TrieNode {
        public Character c;
        public List<TrieNode> children;

        public TrieNode(Character _c) {
            c = _c;
            children = new ArrayList<>();
        }
    }

    public void trie_insert(TrieNode root, String word) {
        TrieNode ptr = root;
        for (char c : word.toCharArray()) {
            boolean found = false;
            for (int i = 0; i < ptr.children.size(); i++) {
                if (ptr.children.get(i).c == c) {
                    ptr = ptr.children.get(i);
                    found = true;
                    break;
                }
            }
            if (!found) {
                // System.out.printf("creating new node: %c\n", c);
                TrieNode new_node = new TrieNode(c);
                ptr.children.add(new_node);
                ptr = new_node;
            }
        }
    }

    public int trie_walk(TrieNode root, String target, int idx, int diff) {
        if (diff > 2) {
            return diff;
        }
        if (idx >= target.length()) {
            return diff;
        }

        TrieNode ptr = null;
        // System.out.printf("%s's children:\n", root.c == null ? "NULL" : root.c.toString());
        for (int i = 0; i < root.children.size(); i++) {
            // System.out.printf("%c", root.children.get(i).c);
            if (root.children.get(i).c == target.charAt(idx)) {
                ptr = root.children.get(i);
                // System.out.printf(" (HIT)");
                break;
            }
            // System.out.printf(", ");
        }
        // System.out.printf("(end)\n");

        int min_diff = Integer.MAX_VALUE;
        for (int i = 0; i < root.children.size(); i++) {
            int walk_diff;
            if (ptr != null && ptr.c == root.children.get(i).c) {
                walk_diff = trie_walk(ptr, target, idx + 1, diff);
            } else {
                walk_diff = trie_walk(root.children.get(i), target, idx + 1, diff + 1);
            }
            if (walk_diff < min_diff) {
                min_diff = walk_diff;
            }
        }
        return min_diff;
    }

    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        // construct trie
        TrieNode root = new TrieNode(null);
        for (int i = 0; i < dictionary.length; i++) {
            trie_insert(root, dictionary[i]);
        }

        List<String> ans = new ArrayList<>();
        for (String s : queries) {
            // System.out.printf("walking %s\n", s);
            int walk_diff = trie_walk(root, s, 0, 0);
            if (walk_diff <= 2) {
                ans.add(s);
            }
        }

        return ans;
    }
}
