class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqmap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            freqmap.put(nums[i], freqmap.getOrDefault(nums[i], 0) + 1);
        }

        List<Integer>[] freq = new List[nums.length + 1];

        for (int i = 0; i < freq.length; i++) {
            freq[i] = new ArrayList<>();
        }

        for (Map.Entry<Integer, Integer> entry : freqmap.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            freq[value].add(key);
        }

        int[] ans = new int[k];

        int ansptr = 0;

        for (int i = nums.length; i >= 0; i--) {
            for(int j = 0; j < freq[i].size(); j++){
                ans[ansptr++] = freq[i].get(j);
                if (ansptr == k) return ans;
            }
        }

        return ans;
    }
}
