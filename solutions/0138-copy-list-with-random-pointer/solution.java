/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Node iter = head;

        // weave pass
        while (iter != null) {
            Node next = iter.next;
            Node nn = new Node(iter.val);
            if (next == null) {
                iter.next = nn;
                // nn.next is null
                break;
            }

            iter.next = nn;
            nn.next = next;

            iter = next;
        }

        iter = head;

        // random pass
        while (iter != null) {
            Node copy = iter.next;

            // Node random_target = iter.random;
            if (iter.random == null) {
                copy.random = null;
            } else {
                // Node random_target_copy = iter.random.next;
                copy.random = iter.random.next; // safe bc the next for iter always exists unless null
            }

            iter = iter.next.next;
        }

        iter = head;

        Node ans = iter.next;

        // separate pass
        while (iter.next.next != null) {
            // Node orig = iter;
            Node copy = iter.next;
            // Node orig_next = copy.next;
            // Node copy_next = copy.next.next;
            iter.next = copy.next;
            copy.next = copy.next.next;
            iter = iter.next;
        }

        // iter.next.next = null;
        iter.next = null;

        return ans;
    }
}
