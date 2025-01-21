class Solution {
public:
    vector<int> findAnagrams(string s, string p) {
        unordered_map<char, int> p_map;
        vector<int> res;

        int psz = p.size();

        for (int i = 0; i < p.size(); i++) {
            p_map[p[i]]++;
        }

        int alen = p_map.size();

        for (int i = 0; i < s.size(); i++) {
            if (p_map.count(s[i])) {
                p_map[s[i]]--;
                if (p_map[s[i]] == 0) {
                    alen--;
                }
            }

            if (i - psz >= 0) {
                if (p_map.count(s[i - psz])) {
                    if (p_map[s[i - psz]] == 0) {
                        alen++;
                    }
                    p_map[s[i - psz]]++;
                }
            }

            if (alen == 0) {
                res.push_back(i - psz + 1);
            }
        }

        return res;
    }
};
