class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0)
            return 0;

        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        int largest_seq_len = 1;

        HashSet<Integer> inspected_set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            int inspected = nums[i] + 1;
            int inspected_seq_len = 1;
            inspected_set.add(nums[i]);
            while (!inspected_set.contains(inspected) && set.contains(inspected)) {
                inspected_set.add(inspected);
                inspected++;
                inspected_seq_len++;
                if (inspected_seq_len > largest_seq_len) {
                    largest_seq_len = inspected_seq_len;
                }
            }
            inspected = nums[i] - 1;
            while (!inspected_set.contains(inspected) && set.contains(inspected)) {
                inspected_set.add(inspected);
                inspected--;
                inspected_seq_len++;
                if (inspected_seq_len > largest_seq_len) {
                    largest_seq_len = inspected_seq_len;
                }
            }
        }

        return largest_seq_len;
    }
}
