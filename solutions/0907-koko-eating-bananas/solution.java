class Solution {

    private int determineHoursRemaining(int[] arr, int h, int k) {
        int h_acc = 0;
        for (int i = 0; i < arr.length; i++) {
            h_acc += Math.ceilDiv(arr[i], k);
        }
        return h - h_acc;
    }

    public int minEatingSpeed(int[] piles, int h) {
        int max = -1;
        for (int i = 0; i < piles.length; i++) {
            if (piles[i] > max) {
                max = piles[i];
            }
        }

        if (piles.length == h) {
            return max;
        }

        int l = 1;
        int r = max;
        int ans = -1;

        while (l <= r) {
            int mid = l + (r - l) / 2;  
            int delta = determineHoursRemaining(piles, h, mid);
            // System.out.printf("mid=%d, delta=%d, l=%d, r=%d\n", mid, delta, l, r);
            if (delta > 0) {
                ans = mid;
                r = mid - 1;
            }
            else if (delta < 0){
                l = mid + 1;
            }
            else {
                ans = mid;
                r = mid - 1;
            }
        }

        return ans;
    }
}
