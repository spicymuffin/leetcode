class Solution {
    BitSet digits;

    private boolean checkSquare(char[][] board, int _x, int _y) {
        for (int y = _y; y < _y + 3; y++) {
            for (int x = _x; x < _x + 3; x++) {
                if (!Character.isDigit(board[y][x]))
                    continue;
                if (digits.get(board[y][x] - '1'))
                    return false;
                else
                    digits.set(board[y][x] - '1');
            }
        }

        return true;
    }

    private boolean checkVertical(char[][] board, int _x) {
        for (int y = 0; y < 9; y++) {
            if (!Character.isDigit(board[y][_x]))
                continue;
            if (digits.get(board[y][_x] - '1'))
                return false;
            else
                digits.set(board[y][_x] - '1');
        }

        return true;
    }

    private boolean checkHorizontal(char[][] board, int _y) {
        for (int x = 0; x < 9; x++) {
            if (!Character.isDigit(board[_y][x]))
                continue;
            if (digits.get(board[_y][x] - '1'))
                return false;
            else
                digits.set(board[_y][x] - '1');
        }

        return true;
    }

    public boolean isValidSudoku(char[][] board) {
        digits = new BitSet(9);

        // square pass
        for (int y = 0; y < 9; y += 3) {
            for (int x = 0; x < 9; x += 3) {
                if (!checkSquare(board, x, y)) {
                    return false;
                }
                digits.clear();
            }
        }

        // horizontal pass
        for (int y = 0; y < 9; y++) {
            if (!checkHorizontal(board, y)) {
                return false;
            }
            digits.clear();
        }

        // vertical pass
        for (int x = 0; x < 9; x++) {
            if (!checkVertical(board, x)) {
                return false;
            }
            digits.clear();
        }

        return true;
    }
}
