class Solution {

    void swap_robot(List<Integer> arr, int a, int b) {
        int tmp = arr.get(a);
        arr.set(a, arr.get(b));
        arr.set(b, tmp);
    }

    void swap_factory(int[][] arr, int a, int b) {
        int[] tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    void quicksort_robot(List<Integer> arr, int l, int r) {
        if (l >= r) {
            return;
        }

        int bigptr = l;
        int pivot = arr.get(l);

        for (int scanptr = l; scanptr <= r; scanptr++) {
            if (arr.get(scanptr) < pivot) {
                bigptr++;
                swap_robot(arr, scanptr, bigptr);
            }
        }

        swap_robot(arr, l, bigptr);
        quicksort_robot(arr, l, bigptr - 1);
        quicksort_robot(arr, bigptr + 1, r);
    }

    void quicksort_factory(int[][] arr, int l, int r) {
        if (l >= r) {
            return;
        }

        int bigptr = l;
        int[] pivot = arr[l];

        for (int scanptr = l; scanptr <= r; scanptr++) {
            if (arr[scanptr][0] < pivot[0]) {
                bigptr++;
                swap_factory(arr, scanptr, bigptr);
            }
        }

        swap_factory(arr, l, bigptr);
        quicksort_factory(arr, l, bigptr - 1);
        quicksort_factory(arr, bigptr + 1, r);
    }

    private long calculateMinDistance(
            int robotIdx,
            int factoryIdx,
            List<Integer> robot,
            List<Integer> factoryPositions,
            long[][] memo) {
        // All robots assigned
        if (robotIdx == robot.size())
            return 0;
        // No factories left to assign
        if (factoryIdx == factoryPositions.size())
            return (long) 1e12;
        // Check memo
        if (memo[robotIdx][factoryIdx] != -1)
            return memo[robotIdx][factoryIdx];

        // Option 1: Assign current robot to current factory
        long assign = Math.abs(robot.get(robotIdx) - factoryPositions.get(factoryIdx)) +
                calculateMinDistance(
                        robotIdx + 1,
                        factoryIdx + 1,
                        robot,
                        factoryPositions,
                        memo);

        // Option 2: Skip current factory for the current robot
        long skip = calculateMinDistance(
                robotIdx,
                factoryIdx + 1,
                robot,
                factoryPositions,
                memo);

        // Take the minimum and store in memo
        memo[robotIdx][factoryIdx] = Math.min(assign, skip);
        return memo[robotIdx][factoryIdx];
    }

    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        quicksort_robot(robot, 0, robot.size() - 1);
        quicksort_factory(factory, 0, factory.length - 1);

        // for (int i = 0; i < robot.size(); i++) {
        //     System.out.printf("%d ", robot.get(i));
        // }

        // System.out.printf("\n");

        // for (int i = 0; i < factory.length; i++) {
        //     System.out.printf("(%d, %d) ", factory[i][0], factory[i][1]);
        // }

        List<Integer> factoryPositions = new ArrayList<>();
        for (int[] f : factory) {
            for (int i = 0; i < f[1]; i++) {
                factoryPositions.add(f[0]);
            }
        }

        int robotCount = robot.size();
        int factoryCount = factoryPositions.size();
        long[][] memo = new long[robotCount][factoryCount];
        for (long[] row : memo)
            Arrays.fill(row, -1);

        return calculateMinDistance(0, 0, robot, factoryPositions, memo);
    }
}
