class Solution {

    private int[] mapOriginal = new int[26];
    private int[] mapAnagram = new int[26];

    public boolean isAnagram(String s, String t) {
        for (char c : s.toCharArray()) {
            mapOriginal[c - 'a']++;
        }

        for (char c : t.toCharArray()) {
            mapAnagram[c - 'a']++;
        }

        for (int i = 0; i < 26; i++){
            if (mapOriginal[i] != mapAnagram[i]) return false;
        }

        return true;
    }
}
