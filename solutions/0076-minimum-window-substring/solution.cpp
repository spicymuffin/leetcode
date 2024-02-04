class Solution {
public:
    string minWindow(string s, string t) {
        unordered_map<char, int> tmap;
        unordered_map<char, int> smap;

        int minlen = INT_MAX;
        int minleft = 0;

        for (auto it : t) {
            tmap[it]++;
        }

        int right = 0;
        int left = 0;

        int required = tmap.size();
        int formed = 0;

        while (right < s.length()) {
            smap[s[right]]++;

            if (tmap.find(s[right]) != tmap.end() &&
                smap[s[right]] == tmap[s[right]]) {
                formed++;
            }

            while (left <= right && formed == required) {
                int len = right - left + 1;

                if (len < minlen) {
                    minleft = left;
                    minlen = len;
                }

                smap[s[left]]--;
                if (tmap.find(s[left]) != tmap.end() &&
                    smap[s[left]] < tmap[s[left]]) {
                    formed--;
                }
                left += 1;
            }
            right += 1;
        }

        return minlen == INT_MAX ? "" : s.substr(minleft, minlen);
    }
};
