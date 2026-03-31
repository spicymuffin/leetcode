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
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) {
            return head;
        }

        ListNode gs_prev, gs_curr = null;
        ListNode ge_prev, ge_curr = null;
        ListNode iter = head;

        int i = -1;
        ListNode prev = null;

        while (iter != null) {

            gs_prev = gs_curr;
            gs_curr = iter;

            // step 1 into the group
            prev = iter;
            iter = iter.next;

            for (i = 1; i < k && iter != null; i++) {
                ListNode tmp = iter.next;
                iter.next = prev;
                prev = iter;
                iter = tmp;
                // System.out.println("alengay");
            }

            if (ge_curr == null) {
                ge_curr = prev;
                head = ge_curr;
            } else {
                ge_prev = ge_curr;
                ge_curr = prev;
            }
            // System.out.printf("ge_curr: %d -> %d\n", ge_curr.val, ge_curr.next.val);

            if (gs_prev != null && ge_curr != null) {
                // System.out.printf("connecting %d to %d\n", gs_prev.val, ge_curr.val);
                gs_prev.next = ge_curr;
            }

            // System.out.printf("head=%d\n", head.val);

            if (i < k) {
                // exit sequence
                iter = prev;
                iter = iter.next;
                gs_curr = prev;

                for (int j = 1; j < i; j++) {
                    // System.out.printf("iter=%d\n", iter.val);
                    ListNode tmp = iter.next;
                    iter.next = prev;
                    prev = iter;
                    iter = tmp;
                }

                // System.out.printf("gs_prev=%d, prev=%d\n", gs_prev.val, prev.val);
                gs_prev.next = prev;
                gs_curr.next = null;

                return head;
            }
        }

        gs_curr.next = null;

        return head;
    }
}
