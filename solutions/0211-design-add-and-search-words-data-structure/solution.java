class WordDictionary {

    class Node {
        boolean endpoint;
        Node[] children = new Node[26];
    }

    Node root;

    public WordDictionary() {
        root = new Node();
    }

    public void addWord(String word) {
        Node ptr = root;
        int search_depth;
        for (search_depth = 0; search_depth < word.length(); search_depth++) {
            if (ptr.children[word.charAt(search_depth) - 'a'] == null) {
                ptr.children[word.charAt(search_depth) - 'a'] = new Node();
            }

            ptr = ptr.children[word.charAt(search_depth) - 'a'];
        }

        ptr.endpoint = true;
    }

    private boolean search_internal(String word, int idx, Node n) {
        Node ptr = n;
        int i;
        for (i = idx; i < word.length(); i++) {
            // System.out.printf("scanning %c\n", word.charAt(i));
            if (word.charAt(i) == '.') {
                for (int j = 0; j < 26; j++) {
                    if (ptr.children[j] == null) {
                        continue;
                    }
                    // System.out.printf("searching: i=%d\n", i + 1);
                    boolean search_result = search_internal(word, i + 1, ptr.children[j]);

                    if (search_result) {
                        return true;
                    }
                }
                return false;
            } else {
                if (ptr.children[word.charAt(i) - 'a'] == null) {
                    return false;
                }
                ptr = ptr.children[word.charAt(i) - 'a'];
            }
        }

        return i >= word.length() && ptr.endpoint;
    }

    public boolean search(String word) {
        return search_internal(word, 0, root);
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
