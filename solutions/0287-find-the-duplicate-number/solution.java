class Solution {
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        for (;;) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (fast == slow) {
                break;
            }
        }

        fast = 0;

        for (;;) {
            fast = nums[fast];
            slow = nums[slow];
            if (slow == fast) {
                return slow;
            }
        }
    }
}
