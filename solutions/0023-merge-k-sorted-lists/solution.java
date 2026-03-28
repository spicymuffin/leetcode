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
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        ListNode sorted = new ListNode();
        ListNode sortediter = sorted;

        // initial pass
        for (ListNode l : lists)
            if (l != null)
                pq.add(l);

        while (!pq.isEmpty()) {
            ListNode smallest = pq.poll();
            if (smallest.next != null) {
                pq.add(smallest.next);
            }
            sortediter.next = smallest;
            sortediter = smallest;
        }

        return sorted.next;
    }
}
