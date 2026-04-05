class Solution {
    public boolean judgeCircle(String moves) {
        int x = 0, y = 0;
        for (final char c : moves.toCharArray()) {
            switch (c) {
                case 'R':
                    x++;
                    break;
                case 'L':
                    x--;
                    break;
                case 'U':
                    y++;
                    break;
                case 'D':
                    y--;
                    break;
            }
        }

        return x == 0 && y == 0;
    }
}
