class Solution {
    private void swap(int idx1, int idx2, int[] arr, int[] aux) {
        int tmp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tmp;
        tmp = aux[idx1];
        aux[idx1] = aux[idx2];
        aux[idx2] = tmp;
    }

    private int choosepivot(int l, int r, int[] arr, int[] aux) {
        int pivotidx = ThreadLocalRandom.current().nextInt(l, r + 1);
        swap(l, pivotidx, arr, aux);
        return arr[l];
    }

    private void quicksort(int l, int r, int[] arr, int[] aux) {
        if (l >= r)
            return;

        int pivot = choosepivot(l, r, arr, aux);
        int bigptr = l;
        for (int scanptr = l + 1; scanptr <= r; scanptr++) {
            if (arr[scanptr] < pivot) {
                bigptr++;
                swap(scanptr, bigptr, arr, aux);
            }
        }

        swap(l, bigptr, arr, aux);
        quicksort(l, bigptr - 1, arr, aux);
        quicksort(bigptr + 1, r, arr, aux);
    }

    public int carFleet(int target, int[] position, int[] speed) {

        if (position.length == 1)
            return 1;

        quicksort(0, position.length - 1, position, speed);

        // System.out.println(Arrays.toString(position));
        // System.out.println(Arrays.toString(speed));

        float[] times = new float[position.length];
        for (int i = 0; i < position.length; i++) {
            times[i] = (float) (target - position[i]) / (float) speed[i];
        }

        // System.out.println(Arrays.toString(times));

        Stack<Integer> fleetstack = new Stack<>();
        fleetstack.push(0);

        for (int i = 1; i < position.length; i++) {
            if (times[i] < times[fleetstack.peek()]) {
                fleetstack.push(i);
            } else {
                while (!fleetstack.empty() && times[i] >= times[fleetstack.peek()]) {
                    fleetstack.pop();
                }
                fleetstack.push(i);
            }
        }

        return fleetstack.size();
    }
}
