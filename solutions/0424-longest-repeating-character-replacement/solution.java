class Solution {
    public int characterReplacement(String s, int k) {
        HashSet<Character> charset = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            charset.add(s.charAt(i));
        }

        int l = 0;
        int r = 0;

        int k_cpy = k;

        int max = -1;

        for (char c : charset) {
            int curr = 0;
            // System.out.printf("currchar=%c\n", c);
            while (true) {
                if (r < s.length()) {
                    if (c == s.charAt(r)) {
                        // System.out.printf("scan, r=%d\n", r);
                        curr++;
                        max = Math.max(curr, max);
                        r++;
                    } else {
                        k--;
                        curr++;
                        r++;
                        while (k < 0 && l < r) {
                            // System.out.printf("replenish, l=%d, k=%d\n", r, k);
                            if (l < s.length() && c != s.charAt(l)) {
                                k++;
                            }
                            l++;
                            curr--;
                        }
                        max = Math.max(curr, max);
                    }
                } else {
                    break;
                }
            }

            k = k_cpy;
            l = 0;
            r = 0;
        }

        return max;
    }
}
