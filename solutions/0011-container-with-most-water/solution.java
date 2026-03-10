class Solution {

    private int calculateArea(int idx1, int idx2, int[] height) {
        return Math.min(height[idx1], height[idx2]) * (idx2 - idx1);
    }

    public int maxArea(int[] height) {
        int l = 0;
        int r = height.length - 1;

        int lmax = height[l];
        int rmax = height[r];

        int lidx = l;
        int ridx = r;

        int maxarea = calculateArea(l, r, height);

        while (l < r - 1) {
            int area = 0;
            if (lmax <= rmax) {
                l++;
                if (height[l] > lmax) {
                    lmax = height[l];
                    lidx = l;
                }
                area = calculateArea(lidx, ridx, height);
                if (area >= maxarea) {
                    maxarea = calculateArea(lidx, ridx, height);
                }
            } else {
                r--;
                if (height[r] > rmax) {
                    rmax = height[r];
                    ridx = r;
                }
                area = calculateArea(lidx, ridx, height);
                if (area >= maxarea) {
                    maxarea = calculateArea(lidx, ridx, height);
                    rmax = height[r];
                    ridx = r;
                }
            }
        }

        // System.out.printf("%d %d\n", lidx, ridx);
        return maxarea;
    }
}
