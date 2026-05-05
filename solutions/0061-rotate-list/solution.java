/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0)
            return head;

        ListNode ptr = head;

        int count = 0;
        while (ptr.next != null) {
            ptr = ptr.next;
            count++;
        }

        if (count < k) {
            k = k % (count + 1);
        }

        if (k == 0)
            return head;

        ptr.next = head;
        ptr = head;

        for (int i = 0; i < count - k; i++) {
            ptr = ptr.next;
        }

        ListNode ret = ptr.next;
        ptr.next = null;
        return ret;
    }
}
