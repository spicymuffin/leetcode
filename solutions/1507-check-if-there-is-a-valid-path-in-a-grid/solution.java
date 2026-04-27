class Solution {
    int[][] grid;
    int height;
    int width;

    boolean path_compatible(int y, int x, int dy, int dx) {
        int src = grid[y][x];
        int dst = grid[y + dy][x + dx];

        switch (src) {
            case 1:
                if (dx == 1 && (dst == 1 || dst == 3 || dst == 5)) {
                    return true;
                } else if (dx == -1 && (dst == 1 || dst == 4 || dst == 6)) {
                    return true;
                }
                return false;
            case 2:
                if (dy == 1 && (dst == 2 || dst == 5 || dst == 6)) {
                    return true;
                } else if (dy == -1 && (dst == 2 || dst == 3 || dst == 4)) {
                    return true;
                }
                return false;
            case 3:
                if (dy == 1 && (dst == 2 || dst == 5 || dst == 6)) {
                    return true;
                } else if (dx == -1 && (dst == 1 || dst == 4 || dst == 6)) {
                    return true;
                }
                return false;
            case 4:
                if (dy == 1 && (dst == 2 || dst == 5 || dst == 6)) {
                    return true;
                } else if (dx == 1 && (dst == 1 || dst == 3 || dst == 5)) {
                    return true;
                }
                return false;
            case 5:
                if (dy == -1 && (dst == 2 || dst == 3 || dst == 4)) {
                    return true;
                } else if (dx == -1 && (dst == 1 || dst == 4 || dst == 6)) {
                    return true;
                }
                return false;
            case 6:
                if (dy == -1 && (dst == 2 || dst == 3 || dst == 4)) {
                    return true;
                } else if (dx == 1 && (dst == 1 || dst == 3 || dst == 5)) {
                    return true;
                }
                return false;
        }

        return false;
    }

    boolean inside(int y, int x) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return false;
        }
        return true;
    }

    boolean dfs(int y, int x, int prev_y, int prev_x) {
        if (y == height - 1 && x == width - 1) {
            return true;
        }

        int[][] moves = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        for (int i = 0; i < 4; i++) {
            int dy = moves[i][0];
            int dx = moves[i][1];
            int dst_y = y + dy;
            int dst_x = x + dx;

            if (inside(dst_y, dst_x)
                    && (!(dst_y == prev_y && dst_x == prev_x))
                    && path_compatible(y, x, dy, dx)) {
                if (dst_y == 0 && dst_x == 0) {
                    return false;
                }
                return dfs(y + dy, x + dx, y, x);
            }
        }
        return false;
    }

    public boolean hasValidPath(int[][] _grid) {
        grid = _grid;
        height = grid.length;
        width = grid[0].length;

        int start_tile = grid[0][0];
        switch (start_tile) {
            case 1:
            case 2:
            case 3:
            case 6:
                return dfs(0, 0, -1, -1);
            case 4:
                return dfs(0, 0, 0, 1) || dfs(0, 0, 1, 0);
            case 5:
                return false;
        }

        return false;
    }
}
