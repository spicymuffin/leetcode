class Solution {
    public int mirrorDistance(int n) {
        int n_cpy = n;
        int reverse = 0;

        while (n_cpy > 0) {
            reverse *= 10;
            reverse += n_cpy % 10;
            n_cpy /= 10;
        }

        return Math.abs(n - reverse);
    }
}
