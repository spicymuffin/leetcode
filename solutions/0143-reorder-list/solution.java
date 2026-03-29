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
    public void reorderList(ListNode head) {
        // find middle pass
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        if (fast.next != null) {
            fast = fast.next;
        }

        fast = slow;

        ListNode next = fast.next;
        ListNode temp;
        while (next != null) {
            temp = fast;
            fast = next;
            next = fast.next;
            fast.next = temp;
        }

        slow = head;

        while (fast != slow) {
            next = slow.next;
            temp = fast.next; // is the one that fast needs to jump to
            slow.next = fast;
            fast.next = next;
            slow = next;
            fast = temp;
        }

        fast.next = null;
    }
}
