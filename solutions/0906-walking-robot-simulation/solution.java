class Solution {
    int direction = 0; // N, E, S, W

    int x = 0;
    int y = 0;

    int maxdst;

    HashSet<Long> obstacle_map;

    long pack(int x, int y) {
        return (((long) x) << 32) | (y & 0xffffffffL);
    }

    void processTurn(int cmd) {
        if (cmd == -1) {
            direction = (direction + 1) % 4;
        } else {
            direction = (direction + 3) % 4;
        }
    }

    void walk(int k) {
        int dx = 0;
        int dy = 0;
        switch (direction) {
            case 0:
                dy = 1;
                break;
            case 1:
                dx = 1;
                break;
            case 2:
                dy = -1;
                break;
            case 3:
                dx = -1;
                break;
        }
        for (int j = 0; j < k; j++) {
            int nx = x + dx;
            int ny = y + dy;

            // System.out.printf("moving to (%d, %d)\n", nx, ny);

            if (obstacle_map.contains(pack(nx, ny))) {
                return;
            }

            x = nx;
            y = ny;

            int squaredst = x * x + y * y;
            if (squaredst > maxdst) {
                maxdst = squaredst;
            }
        }
    }

    public int robotSim(int[] commands, int[][] obstacles) {
        maxdst = 0;

        obstacle_map = new HashSet<>();

        for (int i = 0; i < obstacles.length; i++) {
            obstacle_map.add(pack(obstacles[i][0], obstacles[i][1]));
            // System.out.printf("moving to (%d, %d)\n", obstacles[i][0], obstacles[i][1]);
        }

        for (int i = 0; i < commands.length; i++) {
            if (commands[i] < 0) {
                processTurn(commands[i]);
            } else {
                walk(commands[i]);
            }
        }

        return maxdst;
    }
}
