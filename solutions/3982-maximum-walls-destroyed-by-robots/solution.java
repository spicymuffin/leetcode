class Solution {

    void swap(int a, int b, int[] robots, int[] distance) {
        int tmp = robots[a];
        robots[a] = robots[b];
        robots[b] = tmp;

        tmp = distance[a];
        distance[a] = distance[b];
        distance[b] = tmp;
    }

    void quicksort(int[] robots, int[] distance, int l, int r) {
        if (l >= r)
            return;

        int pivotIdx = ThreadLocalRandom.current().nextInt(l, r + 1);
        swap(l, pivotIdx, robots, distance);

        int pivot = robots[l];
        int bigptr = l;

        for (int scanptr = l + 1; scanptr <= r; scanptr++) {
            if (robots[scanptr] < pivot) {
                bigptr++;
                swap(scanptr, bigptr, robots, distance);
            }
        }

        swap(l, bigptr, robots, distance);
        quicksort(robots, distance, l, bigptr - 1);
        quicksort(robots, distance, bigptr + 1, r);
    }

    // returns first index that makes arr[i] <= t
    int lower(int[] arr, int t) {
        int l = 0;
        int r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] < t) { // only if the target is bigger move l so we can find the first mid that is <= target
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    // returns first index that makes arr[i] < t
    int upper(int[] arr, int t) {
        int l = 0;
        int r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] <= t) { // even if target is equal to what we are inspecting, move l so we can find the first mid < target
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        quicksort(robots, distance, 0, robots.length - 1);
        Arrays.sort(walls);

        int[] walls_l = new int[robots.length];
        int[] walls_r = new int[robots.length];
        int[] overlap = new int[robots.length];

        for (int i = 0; i < robots.length; i++) {
            // populate wallsL
            // this gives the wall that is right after the robot distance wise
            // (so the idx of the wall that cant be broken by robot[i] when shooting left)
            int next_wall_idx = upper(walls, robots[i]);
            // this is going to be the index of the last wall that is reachable by a bullet shot to the left
            // or the last wall that is going to be hit before the previous robot is hit
            int left_cap_idx = 0;
            if (i >= 1) {
                int left_cap = Math.max(robots[i] - distance[i], robots[i - 1] + 1);
                left_cap_idx = lower(walls, left_cap);
            } else {
                left_cap_idx = lower(walls, robots[i] - distance[i]);
            }

            // this is the number of walls between robot[i] to the previous robot OR the distance limit
            // we are subtracting an index that is a part of the interval from an index that is not part of the interval
            // so we get the number of points in the interval
            walls_l[i] = next_wall_idx - left_cap_idx;

            // populate wallsR
            // this gives the idx of the wall that is potentially in the same tile as the robot
            // (this idx can be broken by the robot[i] when shooting right)
            int wall_idx = lower(walls, robots[i]);
            // this is going to be the index of the first wall that is unreachable by a bullet shot to the right
            // or the first wall that is going to be after the one that is hit before the next robot is hit
            int right_cap_idx = 0;
            if (i < robots.length - 1) {
                int right_cap = Math.min(robots[i] + distance[i], robots[i + 1] - 1);
                right_cap_idx = upper(walls, right_cap);
            } else {
                right_cap_idx = upper(walls, robots[i] + distance[i]);
            }

            walls_r[i] = right_cap_idx - wall_idx;

            // this system takes care even of edge cases. for example if a robot doesnt have walls on the right, lower() will
            // return the index 0 because thats how the binary search evaluates if there arent any walls with a distance
            // lower than the robot's distance. but the first greater than, which is given by upper() will be index 0 too, because
            // well, all walls have distance greater than the first robot 

            // populate overlap
            if (i == 0) {
                // the first idx doesnt house an overlap because there are n - 1 overlap zones between n points
                continue;
            }

            int prev_robot_wall_idx = lower(walls, robots[i - 1]);
            overlap[i] = next_wall_idx - prev_robot_wall_idx;
        }

        int sub_l = walls_l[0];
        int sub_r = walls_r[0];
        for (int i = 1; i < robots.length; i++) {
            int current_l = Math.max(sub_l + walls_l[i],
                    sub_r - walls_r[i - 1] + Math.min(walls_l[i] + walls_r[i - 1], overlap[i]));
            int current_r = Math.max(sub_l + walls_r[i], sub_r + walls_r[i]);
            sub_l = current_l;
            sub_r = current_r;
        }

        return Math.max(sub_l, sub_r);
    }
}
