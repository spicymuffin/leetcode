/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    // old, new
    HashMap<Node, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) return null; 

        // if (visited.containsKey(node)) return visited.get(node);

        ArrayList<Node> copy_neighbors = new ArrayList<>();
        Node copy = new Node(node.val, copy_neighbors);

        visited.put(node, copy);

        for (var n : node.neighbors) {
            if (visited.get(n) == null) {
                copy_neighbors.add(cloneGraph(n));
            } else {
                copy_neighbors.add(visited.get(n));
            }
        }

        return copy;
    }
}
