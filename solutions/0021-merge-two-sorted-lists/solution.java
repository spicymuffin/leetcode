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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        if (list1 == null && list2 == null) {
            return list1;
        } else if (list1 == null && list2 != null) {
            return list2;
        } else if (list1 != null && list2 == null) {
            return list1;
        }

        ListNode temp;
        if (list1.val > list2.val) {
            temp = list2;
            list2 = list1;
            list1 = temp;
        }

        ListNode ans = list1;

        while (true) {
            if (list1.next == null) {
                list1.next = list2;
                return ans;
            } else if (list2 == null) {
                return ans;
            }

            if (list1.next.val < list2.val) {
                list1 = list1.next;
            } else {
                temp = list1.next;
                list1.next = list2;
                list2 = list2.next;
                list1.next.next = temp;
            }
        }
    }
}
