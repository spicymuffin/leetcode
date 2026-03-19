class Solution {
    public boolean checkInclusion(String s1, String s2) {

        if (s1.length() > s2.length()) {
            return false;
        }

        int[] s1freqmap = new int[26];
        int[] wfreqmap = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            s1freqmap[s1.charAt(i) - 'a']++;
        }

        int l = 0, r = 0;
        for (; r < s1.length(); r++) {
            wfreqmap[s2.charAt(r) - 'a']++;
        }

        boolean identical = true;
        for (int i = 0; i < 26; i++) {
            if (wfreqmap[i] != s1freqmap[i]) {
                identical = false;
            }
        }

        if (identical) {
            return true;
        }

        for (; r < s2.length(); r++) {
            wfreqmap[s2.charAt(r) - 'a']++;
            wfreqmap[s2.charAt(l) - 'a']--;
            l++;

            identical = true;
            for (int i = 0; i < 26; i++) {
                if (wfreqmap[i] != s1freqmap[i]) {
                    identical = false;
                }
            }

            if (identical) {
                return true;
            }
        }

        return false;
    }
}
