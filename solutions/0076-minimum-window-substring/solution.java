class Solution {
    int[] tfreq;
    int[] substrfreq;
    int[] idxmap;

    private boolean valid() {
        for (int i = 0; i < 128 && idxmap[i] != -1; i++) {
            if (substrfreq[idxmap[i]] < tfreq[idxmap[i]]) {
                return false;
            }
        }
        return true;
    }

    public String minWindow(String s, String t) {
        tfreq = new int[128];
        substrfreq = new int[128];
        idxmap = new int[128];

        if (t.length() > s.length()) {
            return "";
        }

        int idxmapptr = 0;
        for (int i = 0; i < t.length(); i++) {
            if (tfreq[t.charAt(i) - 'A'] == 0) {
                idxmap[idxmapptr] = t.charAt(i) - 'A';
                idxmapptr++;
            }
            tfreq[t.charAt(i) - 'A']++;
        }

        idxmap[idxmapptr] = -1;

        // System.out.println(Arrays.toString(tfreq));

        int l = 0, r = 0;
        int ansl = -1, ansr = -1;
        int minlen = Integer.MAX_VALUE;
        for (; r < s.length(); r++) {
            substrfreq[s.charAt(r) - 'A']++;
            while (valid()) {
                if (r - l + 1 < minlen) {
                    ansl = l;
                    ansr = r;
                    minlen = r - l + 1;
                }
                substrfreq[s.charAt(l) - 'A']--;
                // System.out.printf("[pre] advancing l, =%d\n", l);
                l++;
            }
        }

        r--;

        while (valid()) {
            if (r - l + 1 < minlen) {
                ansl = l;
                ansr = r;
                minlen = r - l + 1;
            }
            substrfreq[s.charAt(l) - 'A']--;
            // System.out.printf("[post] advancing l, =%d\n", l);
            l++;
        }

        return ansl == -1 ? "" : s.substring(ansl, ansr + 1);
    }
}

