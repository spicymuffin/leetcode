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
        List<ListNode> ls = new ArrayList<>();
        ListNode it = head;
        while (it != null) {
            ls.add(it);
            it = it.next;
        }

        int l = 0;
        int r = ls.size() - 1;
        while (l < r) {
            ls.get(l).next = ls.get(r);
            // System.out.printf("linking %d to %d\n", ls.get(l).val, ls.get(r).val);
            ls.get(r).next = ++l >= r ? null : ls.get(l);
            // System.out.printf("linking %d to %d\n", ls.get(r).val, ls.get(l).val);
            r--;
        }

        if (ls.size() % 2 != 0) {
            ls.get(l).next = null;
        }
    }
}
