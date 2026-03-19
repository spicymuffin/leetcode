class Solution {
    public int characterReplacement(String s, int k) {
        int[] freqmap = new int[26];

        int l = 0, r = 0;
        int maxfreq = -1;
        for (r = 0; r < s.length(); r++) {
            int idx = s.charAt(r) - 'A';
            freqmap[idx]++;
            if (maxfreq < freqmap[idx]) {
                maxfreq = freqmap[idx];
            }

            if ((r - l + 1) - maxfreq > k) {
                freqmap[s.charAt(l) - 'A']--;
                l++;
            }
        }

        return r - l; // we need to undo the last iteration +1 in the for loopf
    }
}
