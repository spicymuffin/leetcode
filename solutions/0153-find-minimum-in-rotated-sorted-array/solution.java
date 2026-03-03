class Solution {
    public int findMin(int[] nums) {
        int r = nums.length - 1;
        int l = 0;

        if (nums[l] < nums[r]){
            return nums[l];
        }

        while (l + 1 < r) {
            int mid = (l + r) / 2;
            if (Math.abs(nums[l] - nums[mid]) > Math.abs(nums[r] - nums[mid])) {
                r = mid;
            } else {
                l = mid;
            }
        }

        return nums[l] < nums[r] ? nums[l] : nums[r];
    }
}
