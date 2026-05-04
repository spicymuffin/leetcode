class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int ring = 0;
        int nring = n / 2; // nring + 1 (use '<' in for loops)

        for (; ring < nring; ring++) {
            int first = ring;
            int last = n - 1 - ring;

            for (int i = first; i < last; i++) {
                int offset = i - first;

                int top = matrix[first][i];

                // left -> top
                matrix[first][i] = matrix[last - offset][first];

                // bottom -> left
                matrix[last - offset][first] = matrix[last][last - offset];

                // right -> bottom
                matrix[last][last - offset] = matrix[i][last];

                // top -> right
                matrix[i][last] = top;
            }
        }
    }
}
