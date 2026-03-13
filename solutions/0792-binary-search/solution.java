class Solution {
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (target > nums[mid]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        System.gc();

        return nums[l] == target ? l : -1;
    }
}
