class Solution {
    boolean[][] visited;
    char[][] grid;
    int width, height;

    boolean inside(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    boolean loop(int x, int y, int prev_x, int prev_y, char target) {
        if (!inside(x, y)) {
            return false;
        }

        if (grid[y][x] != target) {
            return false;
        }

        if (visited[y][x]) {
            return true;
        }

        visited[y][x] = true;

        if (!(x + 1 == prev_x && y == prev_y) && loop(x + 1, y, x, y, target)) {
            return true;
        }

        if (!(x - 1 == prev_x && y == prev_y) && loop(x - 1, y, x, y, target)) {
            return true;
        }

        if (!(x == prev_x && y + 1 == prev_y) && loop(x, y + 1, x, y, target)) {
            return true;
        }

        if (!(x == prev_x && y - 1 == prev_y) && loop(x, y - 1, x, y, target)) {
            return true;
        }

        return false;
    }

    public boolean containsCycle(char[][] _grid) {
        grid = _grid;
        height = grid.length;
        width = grid[0].length;
        visited = new boolean[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (!visited[y][x]) {
                    if (loop(x, y, -1, -1, grid[y][x])) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
