class Trie {
    class TrieNode {
        boolean endpoint;
        TrieNode[] children = new TrieNode[26];

        public TrieNode() {
            endpoint = false;
        }
    }

    TrieNode root;
    int searchdepth;

    public Trie() {
        root = new TrieNode();
    }

    public TrieNode searchPrefix(String prefix) {
        TrieNode ptr = root;
        for (searchdepth = 0; searchdepth < prefix.length(); searchdepth++) {
            TrieNode next = ptr.children[prefix.charAt(searchdepth) - 'a'];
            if (next == null) {
                return ptr;
            }
            ptr = next;
        }
        return ptr;
    }

    public void insert(String word) {
        TrieNode prefix_last = searchPrefix(word);
        // System.out.printf("insert word=%s searchdepth=%d\n", word, searchdepth);

        if (searchdepth >= word.length()) {
            prefix_last.endpoint = true;
            return;
        }

        for (int i = searchdepth; i < word.length(); i++) {
            prefix_last.children[word.charAt(i) - 'a'] = new TrieNode();
            prefix_last = prefix_last.children[word.charAt(i) - 'a'];
        }

        prefix_last.endpoint = true;
    }

    public boolean search(String word) {
        TrieNode prefix_last = searchPrefix(word);
        // System.out.printf("search word=%s searchdepth=%d\n", word, searchdepth);
        return searchdepth >= word.length() && prefix_last.endpoint;
    }

    public boolean startsWith(String prefix) {
        TrieNode prefix_last = searchPrefix(prefix);
        // System.out.printf("startsWith word=%s searchdepth=%d\n", prefix, searchdepth);
        return searchdepth >= prefix.length();
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
