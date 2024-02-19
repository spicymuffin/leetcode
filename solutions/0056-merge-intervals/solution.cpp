class Solution {
public:
    vector<vector<int>> merge(vector<vector<int>>& arr) {
        sort(arr.begin(), arr.end());

        vector<vector<int>> result;

        int l, r;

        l = arr[0][0];
        r = arr[0][1];

        for (int i = 0; i < arr.size() - 1; ++i) {
            int l2, r2 = 0;

            l2 = arr[i + 1][0];
            r2 = arr[i + 1][1];

            if (r >= l2 && r2 > r) {
                r = r2;
            }

            if (r < l2) {
                result.push_back(vector<int>({l, r}));
                l = l2;
                r = r2;
            }
        }

        result.push_back(vector<int>({l, r}));

        return result;
    }
};
