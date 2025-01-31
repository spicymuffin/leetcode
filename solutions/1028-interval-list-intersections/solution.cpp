class Solution {
public:
    vector<vector<int>> intervalIntersection(vector<vector<int>>& firstList,
                                             vector<vector<int>>& secondList) {
        int l1_interval_ptr = 0;
        int l2_interval_ptr = 0;

        vector<vector<int>> intersectionsList;

        int l1sz = firstList.size();
        int l2sz = secondList.size();

        while (l1_interval_ptr < l1sz && l2_interval_ptr < l2sz) {
            int intersect_lower = max(firstList[l1_interval_ptr][0],
                                      secondList[l2_interval_ptr][0]);
            int intersect_upper = min(firstList[l1_interval_ptr][1],
                                      secondList[l2_interval_ptr][1]);

            if (intersect_lower <= intersect_upper) {
                intersectionsList.push_back({intersect_lower, intersect_upper});
            }

            if (firstList[l1_interval_ptr][1] <
                secondList[l2_interval_ptr][1]) {
                l1_interval_ptr++;
            } else if (firstList[l1_interval_ptr][1] >
                       secondList[l2_interval_ptr][1]) {
                l2_interval_ptr++;
            } else {
                l1_interval_ptr++;
                l2_interval_ptr++;
            }
        }

        return intersectionsList;
    }
};
