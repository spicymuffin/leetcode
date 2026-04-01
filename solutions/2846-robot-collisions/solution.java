class Solution {

    // void swap(int a, int b, int[] arr) {
    //     int tmp = arr[a];
    //     arr[a] = arr[b];
    //     arr[b] = tmp;
    // }

    // void quicksort(int[] _positions, int[] _idxmap, int l, int r) {
    //     if (l >= r)
    //         return;

    //     int pivotIdx = ThreadLocalRandom.current().nextInt(l, r + 1);
    //     swap(l, pivotIdx, _idxmap);

    //     int pivot = _positions[_idxmap[l]];
    //     int bigptr = l;
    //     for (int scanptr = l + 1; scanptr <= r; scanptr++) {
    //         if (_positions[_idxmap[scanptr]] < pivot) {
    //             bigptr++;
    //             swap(scanptr, bigptr, _idxmap);
    //         }
    //     }

    //     swap(l, bigptr, _idxmap);
    //     quicksort(_positions, _idxmap, l, bigptr - 1);
    //     quicksort(_positions, _idxmap, bigptr + 1, r);
    // }

    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        Integer[] idxmap = new Integer[positions.length];
        for (int i = 0; i < positions.length; i++) {
            idxmap[i] = i;
        }

        Arrays.sort(idxmap, (a, b) -> positions[a] - positions[b]);

        // System.out.printf("%s -> %s\n", Arrays.toString(idxmap), Arrays.toString(positions));
        // for (int i = 0; i < positions.length; i++) {
        //     System.out.printf("%d - %d ", positions[idxmap[i]], healths[idxmap[i]]);
        // }
        // System.out.printf("\n");

        Stack<Integer> rstack = new Stack<>();

        for (int i = 0; i < idxmap.length; i++) {
            if (directions.charAt(idxmap[i]) == 'R') {
                rstack.push(i);
                continue;
            } else {
                while (!rstack.isEmpty()) {
                    if (healths[idxmap[rstack.peek()]] < healths[idxmap[i]]) {
                        // System.out.printf("%d(%c) < %d(%c) -> ", healths[idxmap[rstack.peek()]],
                        //         directions.charAt(idxmap[rstack.peek()]), healths[idxmap[i]],  directions.charAt(idxmap[i]));
                        int g = rstack.pop();
                        healths[idxmap[g]] = 0;
                        healths[idxmap[i]]--;
                        // System.out.printf("%d - %d\n", healths[idxmap[g]], healths[idxmap[i]]);
                        continue;
                    } else if (healths[idxmap[rstack.peek()]] > healths[idxmap[i]]) {
                        // System.out.printf("%d(%c) > %d(%c) -> ", healths[idxmap[rstack.peek()]],
                        //         directions.charAt(idxmap[rstack.peek()]), healths[idxmap[i]],  directions.charAt(idxmap[i]));
                        healths[idxmap[rstack.peek()]]--;
                        healths[idxmap[i]] = 0;
                        // System.out.printf("%d - %d\n", healths[idxmap[rstack.peek()]], healths[idxmap[i]]);
                        break;
                    } else {
                        // System.out.printf("%d == %d -> ", healths[idxmap[rstack.peek()]], healths[idxmap[i]]);
                        int g = rstack.pop();
                        healths[idxmap[g]] = 0;
                        healths[idxmap[i]] = 0;
                        // System.out.printf("%d - %d\n", healths[idxmap[g]], healths[idxmap[i]]);
                        break;
                    }
                }
            }
        }

        List<Integer> ans = new ArrayList();

        for (int i = 0; i < idxmap.length; i++) {
            if (healths[i] != 0) {
                ans.add(healths[i]);
            }
        }

        return ans;
    }
}
