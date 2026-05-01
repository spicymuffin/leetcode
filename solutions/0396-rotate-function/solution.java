class Solution {
    public int maxRotateFunction(int[] nums) {
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        int prev_f = 0;
        for (int i = 0; i < nums.length; i++) {
            prev_f += nums[i] * i;
        }
        int max_f = prev_f;

        for (int i = 1; i < nums.length; i++) {
            int new_f = prev_f + sum - nums.length * (nums[nums.length - i]);
            prev_f = new_f;
            if (new_f > max_f) {
                max_f = new_f;
            }
        }

        return max_f;
    }
}
