class Solution {
    static int ndigit = 5;

    int[][][] dp = new int[7][2][10];
    int altering = (1 << 2) | (1 << 5) | (1 << 6) | (1 << 9);
    int invalid = (1 << 3) | (1 << 4) | (1 << 7);

    private int count(int i, int tight, int hasaltering, char[] digits) {
        if (i == ndigit)
            return hasaltering > 0 ? 1 : 0;
        if (dp[i][tight][hasaltering] != -1)
            return dp[i][tight][hasaltering];
        int limit = tight > 0 ? (int) (digits[i] - '0') : 9;

        int cnt = 0;
        for (int c = 0; c <= limit; c++) {
            if (((1 << c) & invalid) != 0)
                continue;
            int nxtistight = tight > 0 && c == limit ? 1 : 0;
            if (((1 << c) & altering) != 0)
                cnt += count(i + 1, nxtistight, c, digits);
            else
                cnt += count(i + 1, nxtistight, hasaltering, digits);
        }

        dp[i][tight][hasaltering] = cnt;
        return cnt;
    }

    public int rotatedDigits(int n) {
        char[] digits = new char[ndigit];

        for (int i = 0; i < ndigit; i++) {
            digits[ndigit - 1 - i] = (char) ('0' + n % 10);
            n /= 10;
        }

        for (int i = 0; i < ndigit + 1; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 10; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        return count(0, 1, 0, digits);
    }
}
