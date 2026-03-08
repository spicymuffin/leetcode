class Solution {

    public static String toBinaryStringWithLeadingZeros(int val, int len) {
        return Integer.toBinaryString((1 << len) | val).substring(1);
    }

    public String findDifferentBinaryString(String[] nums) {
        int total_digits = nums[0].length();
        boolean[] bitmap = new boolean[(int) Math.pow(2, total_digits)];

        for (int i = 0; i < nums.length; i++) {
            bitmap[Integer.parseInt(nums[i], 2)] = true;
        }

        int ans = 0;
        for (int i = 0; i < bitmap.length; i++) {
            if (!bitmap[i]) {
                ans = i;
                break;
            }
        }

        return toBinaryStringWithLeadingZeros(ans, total_digits);
    }
}
