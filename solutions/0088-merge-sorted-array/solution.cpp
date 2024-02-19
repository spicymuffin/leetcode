class Solution {
public:
    void merge(vector<int>& nums1, int m, vector<int>& nums2, int n) {
        int mc = m - 1;
        int nc = n - 1;
        int i = m + n - 1;

        if (n == 0) {
            return;
        }

        if (m == 0) {
            nums1.swap(nums2);
            return;
        }

        while (i >= 0 && mc >= 0 && nc >= 0) {
            if (nums1[mc] > nums2[nc]) {
                swap(nums1[i], nums1[mc]);
                mc--;
            } else {
                swap(nums1[i], nums2[nc]);
                nc--;
            }
            i--;
        }

        // If there are remaining elements in nums2
        while (nc >= 0) {
            nums1[i--] = nums2[nc--];
        }
    }
};
