class Solution {
    public int[] productExceptSelf(int[] nums) {
        if (nums.length == 0)
            return new int[] {};

        if (nums.length == 1)
            return new int[] { nums[0] };

        if (nums.length == 2)
            return new int[] { nums[1], nums[0] };

        int[] forward_partial = new int[nums.length];
        int[] backward_partial = new int[nums.length];

        forward_partial[0] = nums[0];
        backward_partial[nums.length - 1] = nums[nums.length - 1];

        for (int i = 1; i < nums.length; i++) {
            forward_partial[i] = forward_partial[i - 1] * nums[i];
        }

        for (int i = nums.length - 2; i >= 0; i--) {
            backward_partial[i] = backward_partial[i + 1] * nums[i];
        }

        int[] ans = new int[nums.length];

        ans[0] = backward_partial[1];
        ans[nums.length - 1] = forward_partial[nums.length - 2];

        for (int i = 1; i < nums.length - 1; i++) {
            ans[i] = forward_partial[i - 1] * backward_partial[i + 1];
        }

        return ans;
    }
}
