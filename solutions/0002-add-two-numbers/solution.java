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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode it = new ListNode(0);
        ListNode ans = it;
        ListNode prev = null;

        while (l1 != null && l2 != null) {
            int p = l1.val + l2.val;
            it.val = p + carry;
            if (it.val >= 10) {
                carry = 1;
                it.val -= 10;
            } else {
                carry = 0;
            }
            it.next = new ListNode(0);
            prev = it;
            it = it.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        if (l1 == null && l2 == null) {
            if (carry == 1) {
                it.val = 1;
            } else {
                prev.next = null;
            }
        } else if (l1 == null) {
            l1 = l2;
        }

        while (l1 != null) {
            it.val = l1.val + carry;
            if (it.val >= 10) {
                it.val -= 10;
                carry = 1;
            } else {
                carry = 0;
            }
            it.next = new ListNode(0);
            prev = it;
            it = it.next;
            l1 = l1.next;
        }

        if (carry == 1) {
            it.val = 1;
        } else {
            prev.next = null;
        }

        return ans;
    }
}
