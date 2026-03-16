class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] freq = new int[128]; // standard ASCII
        int maxlen = 0;
        int l = 0;

        for (int r = 0; r < s.length(); r++) {
            char rc = s.charAt(r);
            freq[rc]++;

            while (freq[rc] >= 2) {
                char lc = s.charAt(l);
                freq[lc]--;
                l++;
            }

            maxlen = Math.max(maxlen, r - l + 1);
        }

        return maxlen;
    }
}
