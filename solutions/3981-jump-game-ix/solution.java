class Solution {
    public int[] maxValue(int[] nums) {
        int n = nums.length;

        int[] ans = new int[n];
        int[] prefix = new int[n];

        prefix[0] = nums[0];

        for (int i = 1; i < n; i++) {
            prefix[i] = Math.max(prefix[i - 1], nums[i]);
        }

        int suffix = Integer.MAX_VALUE;

        for (int i = n - 1; i >= 0; i--) {

            if (prefix[i] > suffix)
                ans[i] = ans[i + 1];
            else
                ans[i] = prefix[i];

            suffix = Math.min(suffix, nums[i]);
        }

        return ans;
    }
}
