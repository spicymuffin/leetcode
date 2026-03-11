class Solution {

    private int searchRow(int[] row, int target) {
        int l = 0;
        int r = row.length - 1;
        int ans = -1;

        while (l <= r) {
            int mid = l + (r - l) / 2; // r - 1 trick to prevent int overflow

            if (row[mid] == target) { // == form for readability; if we can find more on the left keep looking
                ans = mid;
                r = mid - 1;
            } else if (row[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return ans;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (target < matrix[0][0]) {
            return false;
        }

        int l = 0;
        int r = matrix.length - 1;

        while (l < r) {
            int mid = l + (r - l + 1) / 2; // bias to the right

            if (matrix[mid][0] <= target) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        return searchRow(matrix[l], target) != -1;
    }
}
