class Solution {

    // public void print2darr(int[][] arr) {
    //     int m = arr.length;
    //     int n = arr[0].length;

    //     for (int i = 0; i < m; i++) {
    //         for (int j = 0; j < n; j++) {
    //             System.out.printf("%d ", arr[i][j]);
    //         }
    //         System.out.printf("\n");
    //     }
    // }

    // public void print3darr(int[][][] arr) {
    //     int m = arr.length;
    //     int n = arr[0].length;
    //     int l = arr[0][0].length;

    //     for (int i = 0; i < m; i++) {
    //         for (int j = 0; j < n; j++) {
    //             System.out.printf("{");
    //             int k = 0;
    //             for (; k < l - 1; k++) {
    //                 System.out.printf("%d, ", arr[i][j][k]);
    //             }
    //             System.out.printf("%d} ", arr[i][j][k]);
    //         }
    //         System.out.printf("\n");
    //     }
    // }

    public int maximumAmount(int[][] coins) {
        int[][][] dp = new int[coins.length][coins[0].length][3];

        int m = coins.length;
        int n = coins[0].length;

        if (coins[0][0] >= 0) {
            dp[0][0][0] = coins[0][0];
            dp[0][0][1] = coins[0][0];
            dp[0][0][2] = coins[0][0];
        } else {
            dp[0][0][0] = coins[0][0];
            dp[0][0][1] = 0;
            dp[0][0][2] = 0;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                int best0 = Math.max(i > 0 ? dp[i - 1][j][0] : Integer.MIN_VALUE, j > 0 ? dp[i][j - 1][0] : Integer.MIN_VALUE);
                int best1 = Math.max(i > 0 ? dp[i - 1][j][1] : Integer.MIN_VALUE, j > 0 ? dp[i][j - 1][1] : Integer.MIN_VALUE);
                int best2 = Math.max(i > 0 ? dp[i - 1][j][2] : Integer.MIN_VALUE, j > 0 ? dp[i][j - 1][2] : Integer.MIN_VALUE);

                if (coins[i][j] >= 0) {
                    dp[i][j][0] = best0 + coins[i][j];
                    dp[i][j][1] = best1 + coins[i][j];
                    dp[i][j][2] = best2 + coins[i][j];
                } else {
                    dp[i][j][0] = best0 + coins[i][j];
                    dp[i][j][1] = Math.max(best0, best1 + coins[i][j]);
                    dp[i][j][2] = Math.max(best1, best2 + coins[i][j]);
                }
            }
        }

        return dp[m - 1][n - 1][2];
    }
}
