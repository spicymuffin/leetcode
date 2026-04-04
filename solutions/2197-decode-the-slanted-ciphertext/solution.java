class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        int nrow = rows;
        int ncol = encodedText.length() / nrow;

        // System.out.printf("nrow=%d, ncol=%d\n", nrow, ncol);

        int rowptr = 0;
        int colptr = 0;

        int diagonal = 0;

        StringBuilder sb = new StringBuilder();
        for (diagonal = 0; diagonal < ncol;) {
            while (rowptr < nrow && colptr < ncol) {
                // System.out.printf("rowptr=%d, colptr=%d\n", rowptr, colptr);
                sb.append(encodedText.charAt(rowptr * ncol + colptr));
                rowptr++;
                colptr++;
            }
            diagonal++;
            rowptr = 0;
            colptr = diagonal;
        }

        return sb.toString().stripTrailing();
    }
}
