class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        int[] next = new int[n];
        int[] prev = new int[n];

        long[] prefix_fwd = new long[n];
        long[] prefix_bck = new long[n];

        int[] cnt_fwd = new int[n];
        int[] cnt_bck = new int[n];

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            Integer val = map.getOrDefault(nums[i], null);
            prev[i] = -1;
            next[i] = -1;
            map.put(nums[i], i);
            if (val != null) {
                prev[i] = val;
                next[val] = i;
            }
        }

        map = null;

        for (int i = 0; i < n; i++) {
            if (prev[i] != -1) {
                prefix_fwd[i] = prefix_fwd[prev[i]] + i;
                cnt_fwd[i] = cnt_fwd[prev[i]] + 1;
            } else {
                prefix_fwd[i] = i;
                cnt_fwd[i] = 1;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            if (next[i] != -1) {
                prefix_bck[i] = prefix_bck[next[i]] + i;
                cnt_bck[i] = cnt_bck[next[i]] + 1;
            } else {
                prefix_bck[i] = i;
                cnt_bck[i] = 1;
            }
        }

        // System.out.println(Arrays.toString(prev));
        // System.out.println(Arrays.toString(next));
        // System.out.println(Arrays.toString(prefix_fwd));
        // System.out.println(Arrays.toString(prefix_bck));
        long[] ans = new long[n];
        for (int i = 0; i < n; i++) {
            long left = 0;
            long right = 0;

            if (prev[i] != -1) {
                left = (long) cnt_fwd[prev[i]] * i - prefix_fwd[prev[i]];
            }

            if (next[i] != -1) {
                right = prefix_bck[next[i]] - (long) cnt_bck[next[i]] * i;
            }

            ans[i] = left + right;
        }
        return ans;
    }
}
