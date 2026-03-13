class Solution {
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] <= nums[r]) {
                // right side is sorted
                // if target is in the sorted interval
                if (nums[mid] < target && target <= nums[r]) {
                    l = mid + 1;
                }
                // else target is not in the sorted interval
                else {
                    r = mid - 1;
                }
            } else {
                // left side is sorted
                // if target is in the sorted interval
                if (nums[l] <= target && target < nums[mid]) {
                    r = mid - 1;
                }
                // else target is not in the sorted interval
                else {
                    l = mid + 1;
                }
            }
        }

        return nums[l] == target ? l : -1;
    }
}
