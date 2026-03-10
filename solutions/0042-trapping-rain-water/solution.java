class Solution {
    public int trap(int[] h) {
        int l = 0;
        int r = h.length - 1;
        int lmax = h[l];
        int rmax = h[r];

        int water_accumulator = 0;

        while (l < r) {
            if (h[l] <= h[r]) {
                l++;
                if (h[l] >= lmax)
                    lmax = h[l];
                water_accumulator += lmax - h[l];
            } else {
                r--;
                if (h[r] >= rmax)
                    rmax = h[r];
                water_accumulator += rmax - h[r];
            }
        }

        return water_accumulator;
    }
}
