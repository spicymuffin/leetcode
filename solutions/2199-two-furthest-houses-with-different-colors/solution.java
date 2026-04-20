class Solution {
    public int maxDistance(int[] colors) {
        int n = colors.length;
        int ans = 0;

        for (int i = n - 1; i >= 0; i--) {
            if (colors[i] != colors[0]) {
                if (i > ans) {
                    ans = i;
                }
                break;
            }
        }

        for (int i = 0; i < n; i++) {
            if (colors[i] != colors[n - 1]) {
                int dst = n - 1 - i;
                if (dst > ans) {
                    ans = dst;
                }
                break;
            }
        }

        return ans;
    }
}
