class Solution {
    static int[] cache = new int[46];

    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            if (cache[n] == 0) {
                cache[n] = climbStairs(n - 1) + climbStairs(n - 2);
            }
            return cache[n];
        }
    }
}
