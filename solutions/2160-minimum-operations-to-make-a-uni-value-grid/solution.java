class Solution {
    public int minOperations(int[][] grid, int x) {
        int height = grid.length;
        int width = grid[0].length;

        int r = grid[0][0] % x;
        int[] counts = new int[10001];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j] % x != r) {
                    return -1;
                }

                counts[grid[i][j]]++;
            }
        }

        int n = width * height;
        int middle = (n - 1) / 2;
        int idx = 0;
        int median = 0;

        for (int i = 0; i < counts.length; i++) {
            idx += counts[i];
            if (idx > middle) {
                median = i;
                break;
            }
        }

        // System.out.printf("h=%d, w=%d\n", height, width);
        // System.out.printf("middle=%d, median=%d\n", middle, median);

        int ans = 0;
        for (int i = 0; i < counts.length; i++) {
            // if (counts[i] == 0)
            //     continue;
            ans += Math.abs(median - i) * counts[i];
        }

        return ans / x;
    }
}
