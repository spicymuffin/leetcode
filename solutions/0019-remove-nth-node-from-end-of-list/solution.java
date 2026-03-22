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
    public ListNode removeNthFromEnd(ListNode head, int n) {

        if (head.next == null) {
            return null;
        }

        ListNode scan = head;
        ListNode follow = head;
        int i;
        for (i = 0; scan.next != null && i < n; i++) {
            scan = scan.next;
        }

        while (scan.next != null) {
            follow = follow.next;
            scan = scan.next;
        }

        if (i < n) {
            return follow.next;
        }

        ListNode next = follow.next.next;
        follow.next = next;

        return head;
    }
}
