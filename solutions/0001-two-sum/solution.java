class Solution {
    HashMap<Integer, Integer> nummap = new HashMap<Integer, Integer>();

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            nummap.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            Integer idx;
            if ((idx = nummap.get(diff)) != null && i != idx) {
                return new int[] { idx, i };
            }
        }

        return new int[] { 69, 69 };
    }
}
