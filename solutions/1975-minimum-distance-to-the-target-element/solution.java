class Solution {
    public int getMinDistance(int[] nums, int target, int start) {
        int l = Integer.MAX_VALUE, r = Integer.MAX_VALUE;
        for (int i = start; i >= 0; i--) {
            if (nums[i] == target) {
                l = i;
                break;
            }
        }

        for (int i = start; i < nums.length; i++) {
            if (nums[i] == target) {
                r = i;
                break;
            }
        }

        // System.out.printf("%d, %d\n", l, r);

        return Math.min(Math.abs(start - l), Math.abs(start - r));
    }
}
