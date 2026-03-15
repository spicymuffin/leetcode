class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums2.length < nums1.length) {
            int[] tmp = nums1;
            nums1 = nums2;
            nums2 = tmp;
        }

        // System.out.println(Arrays.toString(nums1));
        // System.out.println(Arrays.toString(nums2));

        int half = (nums1.length + nums2.length) / 2;
        // int half_idx = (nums1.length + nums2.length - 1) / 2;
        boolean even = (nums1.length + nums2.length) % 2 == 0 ? true : false;

        int l = 0;
        int r = nums1.length - 1;

        int part1, part2;

        int lpart1;
        int rpart1;
        int lpart2;
        int rpart2;

        while (true) {
            part1 = Math.floorDiv(l + r, 2);
            // System.out.printf("l=%d, r=%d\n", l, r);
            part2 = half - (part1 + 1) - 1;

            lpart1 = part1 >= 0 ? nums1[part1] : Integer.MIN_VALUE;
            rpart1 = part1 + 1 < nums1.length ? nums1[part1+1] : Integer.MAX_VALUE; 
            lpart2 = part2 >= 0 ? nums2[part2] : Integer.MIN_VALUE;
            rpart2 = part2 + 1 < nums2.length ? nums2[part2+1] : Integer.MAX_VALUE;

            if (lpart1 <= rpart2 && lpart2 <= rpart1) {
                if (!even) {
                    return (double)Integer.min(rpart1, rpart2);
                }
                else {
                    return ((double)Integer.max(lpart1, lpart2) + (double)Integer.min(rpart1, rpart2)) / 2;
                }
            }
            else if (lpart1 > rpart2) {
                r = part1 - 1;
            }
            else {
                l = part1 + 1;
            }

            // if (lpart1 > rpart2) {
            //     l = part1 + 1;
            // }
            // else if (lpart2 > rpart1){
            //     r = part1 - 1;
            // }
            // else {
            //     break;
            // }
        }

        // if (even) {
        //     return ((double)Integer.max(lpart1, lpart2) + (double)Integer.min(rpart1, rpart2)) / 2.0f;
        // }
        // else {
        //     System.out.printf("lpart1=%d\nrpart1=%d\nlpart2=%d\nrpart2=%d\n", lpart1, rpart1, lpart2, rpart2);
        //     return (double)Integer.min(rpart1, rpart2);
        // }
    }
}
