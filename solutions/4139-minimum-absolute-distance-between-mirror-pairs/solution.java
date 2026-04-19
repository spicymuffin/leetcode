class Solution {
    private int reverse(int n) {
        int acc = 0;
        while (n > 0) {
            acc *= 10;
            acc += n % 10;
            n /= 10;
        }
        return acc;
    }

    public int minMirrorPairDistance(int[] nums) {
        HashMap<Integer, Integer> encountered = new HashMap<>();
        
        int mindst = Integer.MAX_VALUE;
        
        for (int i = 0; i < nums.length; i++) {
            Integer idx = encountered.getOrDefault(nums[i], null);
            if (idx != null) {
                int dst = Math.abs(idx - i);
                // System.out.printf("idx=%d, i=%d, dst=%d\n", idx, i, dst);
                if (dst < mindst && idx < i) {
                    mindst = dst;
                    // System.out.printf("mindst=%d\n", mindst);
                }
            }
            encountered.put(reverse(nums[i]), i);
            // System.out.printf("%d -> %d\n", reverse(nums[i]), i);
        }

        return mindst == Integer.MAX_VALUE ? -1 : mindst;
    }
}
