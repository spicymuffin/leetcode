class Solution {
    public char[][] rotateTheBox(char[][] in_m) {
        int m = in_m.length;
        int n = in_m[0].length;
        char[][] out_m = new char[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                out_m[j][m - 1 - i] = in_m[i][j];
            }
        }

        // // for (int i = 0; i < m; i++) {
        // //     for (int j = 0; j < n; j++) {
        // //         out_m[j][i] = in_m[i][j];
        // //     }
        // // }

        // // for (int i = 0; i < n; i++) {
        // //     for (int j = 0; j < m / 2; j++) {
        // //         char tmp = out_m[i][j];
        // //         out_m[i][j] = out_m[i][m - 1 - j];
        // //         out_m[i][m - 1 - j] = tmp;
        // //     }
        // // }

        for (int j = 0; j < m; j++) {
            int empty_level = -1;
            for (int i = n - 1; i >= 0; i--) {
                if (out_m[i][j] == '.' && empty_level == -1) {
                    empty_level = i;
                } else if (out_m[i][j] == '#' && empty_level != -1) {
                    out_m[empty_level][j] = '#';
                    out_m[i][j] = '.';
                    empty_level--;
                } else if (out_m[i][j] == '*') {
                    empty_level = -1;
                }
            }
        }

        return out_m;
    }
}
